package com.rocky.gc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.PathParam;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocky.gc.dto.IgmDTO;
import com.rocky.gc.entity.ImportGeneralManifest;
import com.rocky.gc.service.IgmService;

@RestController
@RequestMapping("/igm")
public class IgmController {

	@Autowired ModelMapper modelMapper;
	
	@Autowired
	private IgmService igmService;
	
	@GetMapping("/test")
	public String test() {
		return "HI IM FROM IGM  CTRL";
	}
	
	@RequestMapping(value = "/findAll")
	public List<IgmDTO> findAllIgm(){
		 return igmService.getAllIgmReocrds().stream().map(this::convertToVoyageDTO).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/findByIgmNum/{igmNum}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IgmDTO> findBasedOnIgmNo(@PathVariable("igmNum") String igmNO){
		return igmService.getIgmDataBasedOnIgmNum(igmNO).stream().map(this::convertToVoyageDTO).collect(Collectors.toList());
	}
	
	public IgmDTO convertToVoyageDTO(ImportGeneralManifest igmEntity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		IgmDTO voyageRegDTO = modelMapper.map(igmEntity, IgmDTO.class);	
		return voyageRegDTO;
	}
}
