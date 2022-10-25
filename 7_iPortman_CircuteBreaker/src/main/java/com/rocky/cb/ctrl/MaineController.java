package com.rocky.cb.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rocky.cb.dto.VoyageRegDTO;




@RestController
@RequestMapping("/voyage")
public class MaineController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CircuitBreakerFactory<Resilience4JCircuitBreakerConfiguration, Resilience4JConfigBuilder> circuteBreaker;
	
	@RequestMapping("/hi")
	public String getHai() {
		return "HAI";
	}
	
	//http://localhost:8007/voyage/getByVslName/MTECO
	@GetMapping(value = "/getByVslName/{vslName}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getVoyageDetailsOfVessel(@PathVariable String vslName){
		
		CircuitBreaker cb=circuteBreaker.create("voyage");
		return cb.run(()->{
			Map<String,Object> uriVariables=new HashMap();
			uriVariables.put("vslName", vslName);
			String baseUrl=UriComponentsBuilder.fromUriString("http://MARINE-CONFIG/voyage/findByVslName/{vslName}").uriVariables(uriVariables).build().toUriString();
			ResponseEntity<?> listOfVoyages=restTemplate.exchange(baseUrl, HttpMethod.GET,null,new ParameterizedTypeReference<List<VoyageRegDTO>>() {});
			if(listOfVoyages.getStatusCode()==HttpStatus.OK)
			return listOfVoyages;
			return null;
		},(t)->{
			return ResponseEntity.internalServerError().body("non responsive service");
		});
		
		
		
	}
}
