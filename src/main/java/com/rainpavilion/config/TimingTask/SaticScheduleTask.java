//package com.rainpavilion.config.TimingTask;
//
//import com.alibaba.fastjson.JSONArray;
//import com.rainpavilion.controller.MenuController;
//import com.rainpavilion.controller.TestLogController;
//import com.rainpavilion.entity.log.AdministratorOperationRecord;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.util.Collections;
//import java.util.Properties;
//
////1.主要用于标记配置类，兼备Component的效果。
//@Configuration
//// 2.开启定时任务
//@EnableScheduling
//public class SaticScheduleTask {
//    @Autowired
//    private TestLogController testLogController;
//
//    static Integer i = 1;
//
//    //3.添加定时任务
////    @Scheduled(cron = "0/5 * * * * ?")
////    //或直接指定时间间隔，例如：5秒
////    //@Scheduled(fixedRate=5000)
////    private void configureTasks() {
////        System.err.println("----------------执行静态定时任务时间: " + i);
////        i++;
////    }
//
//
////    //3.添加定时任务 5秒
////    @Scheduled(fixedRate=3000)
////    private void configureTasks2() {
////        testLogController.add();
////        System.err.println("==添加===================执行静态定时任务时间: " + i);
////        i++;
////    }
////
////    //3.添加定时任务 5秒
////    @Scheduled(fixedRate=6000)
////    private void configureTasks3() {
////        testLogController.del();
////        System.err.println("==删除===================执行静态定时任务时间: " + i);
////        i++;
////    }
////
////    //3.添加定时任务 5秒
////    @Scheduled(fixedRate=9000)
////    private void configureTasks4() {
////        testLogController.update();
////        System.err.println("==修改===================执行静态定时任务时间: " + i);
////        i++;
////    }
////
////    //3.添加定时任务 5秒
////    @Scheduled(fixedRate=12000)
////    private void configureTasks5() {
////        testLogController.select();
////        System.err.println("==查询===================执行静态定时任务时间: " + i);
////        i++;
////    }
//
//
//    //定义主题
//    public static String topic = "test-syn";
//
//    //3.添加定时任务 5秒
////    @Scheduled(cron = "0/5 * * * * ?")
//    private void configureTasks3() {
//        System.err.println("=====================执行静态定时任务时间: " + i);
//        i++;
//        Properties p = new Properties();
//        p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "81.69.254.8:9092");
//        p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        p.put(ConsumerConfig.GROUP_ID_CONFIG, "PEIYJ");
//        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(p);
//        // 订阅消息
//        kafkaConsumer.subscribe(Collections.singletonList(topic));
//
//        if(kafkaConsumer.poll(100) == null){
//            System.err.println("-------------当前kafka中无数据");
//            return;
//        }
//
//        int i=0;
//        while (i <= 10) {
////        while (true) {
//            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
//            for (ConsumerRecord<String, String> record : records) {
//                String value = record.value();
//                if(value == null){
//                    return;
//                }
//                System.err.println("--------------------" + value);
////                AdministratorOperationRecord administratorOperationRecord = JSONArray.parseObject(value, AdministratorOperationRecord.class);
////                System.err.println(i + "------------"+administratorOperationRecord.getUserName());
//                i++;
//            }
//        }
//    }
//
//
//
//
//    //3.添加定时任务 5秒
////    @Scheduled(cron = "*/5 * * * * ?")
//    private void configureTasks4() {
//        System.err.println("==configureTasks4===================执行静态定时任务时间: " + i);
//        i++;
//    }
//
//
//}
//
//
//
//
//
//
//
//
