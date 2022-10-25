package com.rocky.kpb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaProducerBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerBasicApplication.class, args);
	}
	
	

}
