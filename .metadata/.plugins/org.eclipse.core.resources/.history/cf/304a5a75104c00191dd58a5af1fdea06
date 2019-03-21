package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RealConsumer {

	 @KafkaListener(containerFactory = "kafkaListenerContainerFactory",topics = "test", groupId = "test_group")
	 public void consume(String message) {
		 System.out.println("Consumed message: " + message);
	    
	 }
}
