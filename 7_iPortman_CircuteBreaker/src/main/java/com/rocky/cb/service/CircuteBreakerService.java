 package com.rocky.cb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rocky.cb.dto.IgmDTO;
import com.rocky.cb.dto.VoyageRegDTO;

@Service
public class CircuteBreakerService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	public List<IgmDTO> findAllIgms() {
		CircuitBreaker cargoCB = circuitBreakerFactory.create("generalCargo");
		return cargoCB.run(() -> {
			List<IgmDTO> result = new ArrayList<>();
			String uri = UriComponentsBuilder.fromUriString("http://GENERALCARGO-CONFIG/igm/findAll").build()
					.toUriString();
			ResponseEntity<List<IgmDTO>> responceEntity = restTemplate.exchange(uri, HttpMethod.GET, null,new ParameterizedTypeReference<List<IgmDTO>>() {});
			if (responceEntity.getStatusCode() == HttpStatus.OK)
				result = responceEntity.getBody();
			return result;
		},(t)->{
			return Arrays.asList(new IgmDTO[] {});
		});
	}

	public List<VoyageRegDTO> findAllVoyages() {
		CircuitBreaker voyageCB = circuitBreakerFactory.create("voyageService");
		return voyageCB.run(() -> {
			List<VoyageRegDTO> result = new ArrayList<>();
			String uri = UriComponentsBuilder.fromUriString("http://MARINE-CONFIG/voyage/findAllVoyage").build()
					.toUriString();
			ResponseEntity<List<VoyageRegDTO>> responceEntity = restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<VoyageRegDTO>>() {
					});
			if (responceEntity.getStatusCode() == HttpStatus.OK)
				result = responceEntity.getBody();
			return result;
		},(t)->{
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			return Arrays.asList(new VoyageRegDTO[]{});
		});
		
		/**
		 * if you won't write call back then we get exception 
		 * Exception in thread "main" org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException: No fallback available.
		 */

	}

}
