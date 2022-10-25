package com.rocky.fc.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rocky.fc.dto.VoyageRegDTO;

@FeignClient(name = "MARINE-CONFIG")
public interface Iportman_Service_invoker {

	/*
	 * @GetMapping(value = "/voyage/findAllVoyage",produces =
	 * {MediaType.APPLICATION_JSON_VALUE}) public List<VoyageRegDTO>
	 * findAllVoyages();
	 */
	
	@GetMapping(value = "/voyage/findByVslName/{vslName}",produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<VoyageRegDTO>  getVoyageBasedOnVesselName(@PathVariable("vslName") String vslName);
}
