package com.rocky.ca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rocky.ca.dto.VoyageRegDTO;

@Service
public class MarineService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired DiscoveryClient discoveryClient;
	
	public String serviceUrl() {
		List<ServiceInstance> instances=discoveryClient.getInstances("TEST-CONFIG-MS");
		if(null!=instances)
			return instances.get(0).getUri().toString();
		return null;
	}

	public List<VoyageRegDTO> findAllVoyages(){
		//System.out.println(serviceUrl());
		List<VoyageRegDTO> result=new ArrayList<>();
		String uri=UriComponentsBuilder.fromUriString("http://MARINE-CONFIG/voyage/findAllVoyage").build().toUriString();
		ResponseEntity<List<VoyageRegDTO>> responceEntity=restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<VoyageRegDTO>>(){});
		if(responceEntity.getStatusCode()==HttpStatus.OK)
			result=responceEntity.getBody();
		return result;
	}
	
	
}
