package com.rocky.ca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rocky.ca.dto.IgmDTO;


@Service
public class CargoSevice {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired DiscoveryClient discoveryClient;
	
	public List<IgmDTO> findAllIgms(){
		//System.out.println(serviceUrl());
		List<IgmDTO> result=new ArrayList<>();
		String uri=UriComponentsBuilder.fromUriString("http://GENERALCARGO-CONFIG/igm/findAll").build().toUriString();
		ResponseEntity<List<IgmDTO>> responceEntity=restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<IgmDTO>>(){});
		if(responceEntity.getStatusCode()==HttpStatus.OK)
			result=responceEntity.getBody();
		return result;
	}
}
