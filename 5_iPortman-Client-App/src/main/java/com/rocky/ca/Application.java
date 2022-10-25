package com.rocky.ca;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.rocky.ca.dto.IgmDTO;
import com.rocky.ca.dto.VoyageRegDTO;
import com.rocky.ca.service.CargoSevice;
import com.rocky.ca.service.MarineService;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(Application.class, args);
		MarineService service=ctx.getBean(MarineService.class);
		CargoSevice cservice=ctx.getBean(CargoSevice.class);
		List<VoyageRegDTO> list=service.findAllVoyages();
		list.stream().forEach(System.out::println);
		List<IgmDTO> list1=cservice.findAllIgms();
		list1.stream().forEach(System.out::println);
		
		List<VoyageRegDTO> list2=service.findAllVoyages();
		list2.stream().forEach(System.out::println);
		List<IgmDTO> list3=cservice.findAllIgms();
		list3.stream().forEach(System.out::println);
		
		List<VoyageRegDTO> list4=service.findAllVoyages();
		list4.stream().forEach(System.out::println);
		List<IgmDTO> list5=cservice.findAllIgms();
		list5.stream().forEach(System.out::println);
		
	}

}
