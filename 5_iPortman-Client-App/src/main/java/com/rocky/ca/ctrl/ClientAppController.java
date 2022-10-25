package com.rocky.ca.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rocky.ca.dto.VoyageRegDTO;
import com.rocky.ca.service.MarineService;

@Controller
@RequestMapping("/voyage")
public class ClientAppController {
	
	@Autowired
	MarineService marineService;

	/*
	 * @GetMapping("/getAllVoyage") public List<VoyageRegDTO> getAllVoyageRecords(){
	 * marineService. return null; }
	 */
}
