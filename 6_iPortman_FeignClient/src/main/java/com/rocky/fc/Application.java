package com.rocky.fc;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import com.rocky.fc.api.Iportman_Service_invoker;
import com.rocky.fc.dto.VoyageRegDTO;

@SpringBootApplication
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(Application.class, args);
		Iportman_Service_invoker invoker=ctx.getBean(Iportman_Service_invoker.class);
		/*
		 * List<VoyageRegDTO> list=invoker.findAllVoyages();
		 * list.stream().forEach(System.out::println);
		 */
		for(int i=0;i<10;i++) {
			System.out.println("========================="+i+"===================================");
			List<VoyageRegDTO> vesselNameList=invoker.getVoyageBasedOnVesselName("MTECO");
			vesselNameList.stream().forEach(System.out::println);
			System.out.println("***********************************************************");
		}
		
	}

}
