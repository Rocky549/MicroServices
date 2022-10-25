package com.rocky.kcb.service;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocky.kpb.dto.BookDto;

@Service
public class BookConsumerService {

	@KafkaListener(topics = "bookTopic",groupId = "bookGroup") 
	public void listener(Map<String, Object> dto, ConsumerRecord<String, Map<String,Object>> consumerRecord) {
		 String topic = consumerRecord.topic();
		 if(topic.equalsIgnoreCase("bookTopic")) {
			 System.out.println(" Message Received From Tipic is "+new ObjectMapper().convertValue(dto, BookDto.class));
		 }
	}
	/*
	 * @KafkaListener(topics = "#{'${topics}'.split(',')}", containerFactory =
	 * "kafkaListenerContainerFactory")
	 */
	/*
	 * 
	 * @KafkaListener(topics = "#{'${topics}'.split(',')}", containerFactory =
	 * "kafkaListenerContainerFactory") public void onListening(Map<String, Object>
	 * dto,ConsumerRecord<?, Map<String, Object>> consumerRecord, Acknowledgment
	 * acknowledgment, int partition,int offset) { String topic =
	 * consumerRecord.topic(); if(topic.equalsIgnoreCase("bookTopic")) {
	 * System.out.println(" Message Received From Tipic is "+dto); } }
	 */
}
