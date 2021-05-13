package com.rainpavilion.config.log;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainpavilion.entity.log.AdministratorOperationRecord;
import com.rainpavilion.entity.shiro.Admin;
import com.rainpavilion.service.AdministratorOperationRecordService;
import com.rainpavilion.utils.IPKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

@Slf4j
@Configuration
@Aspect
public class LogAspect {
    public static String topic = "test-syn";
    @Autowired
    private AdministratorOperationRecordService administratorOperationRecordService;

    @Autowired
    private JmsTemplate jmsTemplate;
    /**
     * 1 获取日志数据
     *
     * 2 日志数据添加数据库
     *
     * 切方法和切注解
     *
     * 只能切注解 因为不是所有的方法都有日志注解
     *
     * 凡是添加了日志注解的方法才会被 aop
     */
    @After("@annotation(com.rainpavilion.config.log.LogAnnotation)")
    public void logAfter(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("---------------------开始执行日志增强");
        /**
         * 1 获取日志数据
         */
        AdministratorOperationRecord administratorOperationRecord = new AdministratorOperationRecord();

        HttpServletRequest request = null;
        String ip = null;
        try{
//        ip 通过请求可以获取到 Request（当前线程的Request）
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            request = requestAttributes.getRequest();
        }catch (Exception e){
            System.out.println("---获取不到ip,本地自调");
        }finally {
            if(request == null){
                ip = "本地自调";
            }else {
                //通过工具类
                ip = IPKit.getIpAddrByRequest(request);
            }
        }
        administratorOperationRecord.setLogIp(ip);

        Admin admin = null;
        try{
            //管理员的名字
            admin = (Admin) request.getSession().getAttribute("user");
        }catch (Exception e){

        }

        if (admin != null) {
            administratorOperationRecord.setUserName(admin.getAdminName());
        }else {
            administratorOperationRecord.setUserName("root");
        }

        //操作内容 和 类型
        /**
         * 执行的方法不同 获取到内容和类型不同
         *
         * 自定义注解的方式  获取目标方法注解的值  因为注解的值可以写的不一样 所以每一次拿到数据也不一样
         *
         * 通过切入点对象可以获取目标方法的所有内容 方法本身  方法的注解 方法所在的对象 等
         */
//        1.获取方法签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

//        2.获取方法对象
        Method method = signature.getMethod();

//        3.获取方法上的注解
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);

//        4.获取注解的值
        administratorOperationRecord.setLogContent(annotation.content());
        administratorOperationRecord.setLogType(annotation.type());
        /**
         * 2 日志数据添加数据库
         *
         * 替换成添加到mq里 慢慢消费
         */
//        administratorOperationRecordService.add(administratorOperationRecord);



//        ObjectMapper mapper = new ObjectMapper();
//        String cmfzlog = mapper.writeValueAsString(administratorOperationRecord);
//        jmsTemplate.send("CmfzLog", new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(cmfzlog);
//            }
//        });

        Properties p = new Properties();
        //kafka地址，多个地址用逗号分割
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "81.69.254.8:9092");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        try {
            Object obj = JSONArray.toJSON(administratorOperationRecord);
            String json = obj.toString();
            System.err.println("生产者将对象转成json------:" + json);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, json);
            kafkaProducer.send(record);
        }finally {
            kafkaProducer.close();
        }
        log.info("---------------------日志增强执行结束");

    }
}

