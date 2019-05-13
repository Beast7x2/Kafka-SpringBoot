package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); //Kafka server IP and Port
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id"); //Consumer group name
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest"); // To take values from the topic which were there before starting the listener
        //props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5"); //Batch size
        return new  DefaultKafkaConsumerFactory<>(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        //factory.setConcurrency(2); Based upon the partitions it will create consumers
        //factory.setBatchListener(true); For batch processing
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
