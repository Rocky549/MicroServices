package com.rocky.kcb.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.rocky.kcb.dto.BookDto1;
import com.rocky.kpb.dto.BookDto;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String,Map> consumerFactory(){
		Map<String,Object> map=new HashMap();
		/*map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		map.put(ConsumerConfig.GROUP_ID_CONFIG, "bookGroup");
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		*/
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, "bookGroup");
		map.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
		map.put(JsonDeserializer.KEY_DEFAULT_TYPE,StringDeserializer.class);
		map.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
		map.put(JsonDeserializer.VALUE_DEFAULT_TYPE, com.rocky.kcb.dto.BookDto1.class);
		map.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		/*
		 * map.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.rocky.kcb.BookDto");
		 * map.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
		 * JsonDeserializer.class.getName());
		 * map.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.rocky.kcb.BookDto");
		 */
		/* map.put(JsonDeserializer.TRUSTED_PACKAGES, "com.rocky.kcb"); */
		return new DefaultKafkaConsumerFactory<>(map);
		
		/*
		 * 
		 * props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,
		 * JsonDeserializer.class); props.put(JsonDeserializer.KEY_DEFAULT_TYPE,
		 * "com.example.MyKey")
		 * props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
		 * JsonDeserializer.class.getName());
		 * props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.MyValue")
		 * props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example") return new
		 * DefaultKafkaConsumerFactory<>(props);
		 */
		
	}
	
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,Map> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,Map> concurrentKafkaListenerContainerFactory=new ConcurrentKafkaListenerContainerFactory();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
		return concurrentKafkaListenerContainerFactory;
	}
}
