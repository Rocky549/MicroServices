package com.rocky.kcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaConsumerBsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerBsApplication.class, args);
	}

}
