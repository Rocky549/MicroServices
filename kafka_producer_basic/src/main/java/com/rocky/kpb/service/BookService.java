package com.rocky.kpb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocky.kpb.dto.BookDto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BookService {
	

	@Autowired
	public KafkaTemplate<String, Map> kafkaTemplate;
	
	public String publishBooks(List<BookDto> books,String topic) {
	    for(BookDto dto: books) {
	    	
	    	ListenableFuture<SendResult<String, Map>> listenableFuture = kafkaTemplate.send(topic, new ObjectMapper().convertValue(dto, HashMap.class));
	    	System.out.println("book details are send to consumer");
	    	listenableFuture.addCallback(new ListenableFutureCallback<>()
	        {
	            @Override
	            public void onFailure(Throwable throwable)
	            {
	                log.error("Error sending message to topic {}", topic);
	            }

	            @Override
	            public void onSuccess(SendResult<String, Map> stringMapSendResult)
	            {
	                log.debug("Message {} sent to topic {}", dto, topic);
	            }
	        });
	    }
		return "Books are added to kafka Queue......";
	}
}
