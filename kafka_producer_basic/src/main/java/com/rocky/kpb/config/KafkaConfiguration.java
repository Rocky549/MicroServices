package com.rocky.kpb.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.rocky.kpb.dto.BookDto;

@Configuration
public class KafkaConfiguration {

	@Bean
	public ProducerFactory<String,Map> producerFactory(){
		Map<String,Object> config=new HashMap();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Map>(config);
	}
	
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, Map> kafkaTemplate(){
		return new KafkaTemplate<String, Map>(producerFactory());
	}
}
