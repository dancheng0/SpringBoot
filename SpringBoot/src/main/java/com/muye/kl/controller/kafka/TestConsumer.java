package com.muye.kl.controller.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

//	@KafkaListener(topics = "test1")
	public void listen (ConsumerRecord<?, ?> record) throws Exception {
		System.out.println("==================");
		System.out.printf("topic = %s, offset = %s, value = %s \n", record.topic(), record.key(), record.value());
		System.out.println("==================");
	}
	
}