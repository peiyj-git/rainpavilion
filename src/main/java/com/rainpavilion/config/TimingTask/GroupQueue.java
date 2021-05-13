package com.rainpavilion.config.TimingTask;

import com.alibaba.fastjson.JSONArray;
import com.rainpavilion.entity.log.AdministratorOperationRecord;
import com.rainpavilion.service.AdministratorOperationRecordService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Properties;

@Configuration
@EnableScheduling
public class GroupQueue {
    @Autowired
    private AdministratorOperationRecordService administratorOperationRecordService;

    static boolean RUN = false;
    Integer i = 1;

    @Async
//    @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "*/5 * * * * ?")
    public void task() {
        System.err.println("===GroupQueue新==================执行静态定时任务时间: " + i);
        i++;
        if (RUN) {
            return;
        }
        RUN = true;
        KafkaConsumer consumer = GroupQueue.getKafkaConsumer("PEIYJ");
//        consumer.subscribe(Arrays.asList("GroupQueue", "FriendQueue"));
        consumer.subscribe(Collections.singletonList("test-syn"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
//            System.out.println("批次：" + UUID.randomUUID().toString());

            for (ConsumerRecord<String, String> record : records) {
//                System.out.printf(record.topic() +
//                                "一条新消息 offset = %d, key = %s, value = %s", record.offset(),
//                        record.key(), record.value());
//                System.out.println(record.topic() + "partition:" +
//                        record.partition());

                String value = record.value();
                System.err.println("消费信息--------------------" + value);
                AdministratorOperationRecord administratorOperationRecord = JSONArray.parseObject(value, AdministratorOperationRecord.class);
                administratorOperationRecordService.add(administratorOperationRecord);

                // 业务处理 TODO
            }

            // 同步提交
            if (records.count() > 0) {
                consumer.commitSync();
                System.out.println("批次提交");
            }
        }
    }

    public static KafkaConsumer getKafkaConsumer(String group) {
        Properties propstask = new Properties();
//        propstask.put("bootstrap.servers", "81.69.254.8:9092");
//        //每个消费者分配独立的组号
//        propstask.put("group.id", group);
//        //如果value合法，则自动提交偏移量
//        propstask.put("enable.auto.commit", "false");
//        // 每次拉取5000条
//        propstask.put("max.poll.records", 2);
//        //设置多久一次更新被消费消息的偏移量
//        propstask.put("auto.commit.interval.ms", "1");
//        //设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
//        propstask.put("session.timeout.ms", "1000");
//        //自动重置offset
//        propstask.put("auto.offset.reset", "latest");//latest  earliest
//        propstask.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        propstask.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        propstask.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "81.69.254.8:9092");
        propstask.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propstask.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propstask.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        propstask.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        propstask.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        return new KafkaConsumer<String, String>(propstask);
    }

}