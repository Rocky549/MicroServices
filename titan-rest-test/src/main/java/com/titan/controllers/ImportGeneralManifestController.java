package com.titan.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.titan.dto.ImportGeneralManifestDTO;
import com.titan.entities.ImportGeneralManifestBO;
import com.titan.exception.ResourceNotFoundException;
import com.titan.repositories.ImportGeneralManifestRepositroy;
import com.titan.services.ImportGeneralManifestService;

@RestController
@RequestMapping(value="/V1.0/igm")
@Transactional
public class ImportGeneralManifestController {

	@Autowired ModelMapper modelMapper;
	
	@Autowired
	@Qualifier(value = "importGeneralManifestServiceImpl")
	ImportGeneralManifestService igmService;
	
	@Autowired
	ImportGeneralManifestRepositroy igmRepository;
	
	@GetMapping(value = "/test")
	public String testApi() {
		return "TestApi";
	}
	
	@GetMapping("/")
	public ResponseEntity findAllIgmRecords() {
		return ResponseEntity.status(HttpStatus.OK).body(igmService.getAllIgmRecoreds());
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/igm/{igmid}")
    public ResponseEntity getIgmDataById(@PathVariable Long igmId)
            throws ResourceNotFoundException {
		ImportGeneralManifestBO igmBo = igmRepository.findById(igmId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"ImportGeneralManifest not found for this id :: " + igmId));
        return ResponseEntity.status(HttpStatus.OK).body(convertToIgmDTO(igmBo));
    }

	
	@PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createIgmRecord(@RequestBody ImportGeneralManifestBO igmBO) {
		ImportGeneralManifestBO igmEntityBO;
		try {
			igmEntityBO=igmService.insertIgmData(igmBO);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong while perfroming save operation");
		}
		return ResponseEntity.status(HttpStatus.OK).body(convertToIgmDTO(igmEntityBO));
	}
	
	
     
    @PutMapping("/igmUpdate")
    public ResponseEntity updateImportGeneralManifestData(ImportGeneralManifestBO igmBO) throws ResourceNotFoundException {
    	ImportGeneralManifestBO igmDataBo = igmService.findById(igmBO.getIgmid());
		if (null!=igmDataBo) {
		throw new ResourceNotFoundException("ImportGeneralManifest not found for this id :: " + igmBO.getIgmid());
		} else {
			ImportGeneralManifestBO igm = igmRepository.findById(igmBO.getIgmid())
	                .orElseThrow(() -> new ResourceNotFoundException("ImportGeneralManifest not found for this id :: " + igmBO.getIgmid()));
			
			 final ImportGeneralManifestBO updatedIgm = igmRepository.save(igm);
		        return ResponseEntity.status(HttpStatus.OK).body(updatedIgm);
		}
	}
    
    
    @DeleteMapping("/deleteIgm/{id}")
    public ResponseEntity deleteEmployee(@PathVariable(value = "igmId") Long igmId)
            throws ResourceNotFoundException {
        ImportGeneralManifestBO igmBo = igmRepository.findById(igmId)
                .orElseThrow(() -> new ResourceNotFoundException("ImportGeneralManifest not found for this id :: " + igmId));
        igmRepository.delete(igmBo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    
    
    
	public ImportGeneralManifestDTO convertToIgmDTO(ImportGeneralManifestBO igmBo) {
		return modelMapper.map(igmBo, ImportGeneralManifestDTO.class);
	}
	
	
	@GetMapping("/findByVcnNum/{vcnNum}")
	public ResponseEntity  getIgmBasedOnVcnNum(@PathVariable String vcnNum) throws InterruptedException {
		//Thread.sleep(2000);
		return ResponseEntity.status(HttpStatus.OK).body(igmService.findByVcnNum(vcnNum).stream().map(this::convertToIgmDTO).collect(Collectors.toList()));
	}
	
	@RequestMapping("/findByVcnNum")
	public ResponseEntity  getIgmDetailsBasedOnVcnNum(@RequestParam String vcnNum) {
		return ResponseEntity.status(HttpStatus.OK).body(igmService.findByVcnNum(vcnNum).stream().map(this::convertToIgmDTO).collect(Collectors.toList()));
	}
	
	
	 @DeleteMapping("/deleteIgmById/{id}")
	  public ResponseEntity deleteTutorial(@PathVariable("id") long id) {
	    try {
	    	igmService.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	
}
