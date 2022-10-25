package com.rocky.ms.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.PathParam;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocky.ms.dto.VoyageRegDTO;
import com.rocky.ms.entity.VoyageRegistrationEntity;
import com.rocky.ms.service.VoyageRegistrationService;




@RestController
@RequestMapping("/voyage")
public class VoyageRegistrationController {
	@Autowired ModelMapper modelMapper;
	
	@Autowired
	private VoyageRegistrationService voyageRegistrationService;
	
	@RequestMapping("/hi")
	public String test() {
		return "HI IM FROM VOYAGE";
	}
	
	@RequestMapping("/findAllVoyage")
	public List<VoyageRegistrationEntity> findAllVoyageRecords() {
		return voyageRegistrationService.getAllVoyageRecords();
	}
	
	//http://localhost:8080/voyage/findByVslName/MTECO
	@RequestMapping("/findByVslName/{vslName}")
	public List<VoyageRegDTO>  getVoyageBasedOnVesselName(@PathVariable String vslName) throws InterruptedException {
		//Thread.sleep(2000);
		return voyageRegistrationService.findByVesselName(vslName).stream().map(this::convertToVoyageDTO).collect(Collectors.toList());
	}
	
	//http://localhost:8080/voyage/findByAgtCode?agtCode=SRS
	@RequestMapping("/findByAgtCode")
	public List<VoyageRegistrationEntity>  getVoyageBasedOnAgtCode(@RequestParam String agtCode) {
		return voyageRegistrationService.findByAgtCode(agtCode);
	}
	
	@RequestMapping("/findReqDataByAgtCode")
	public List<VoyageRegDTO> getOnlyReqFieldsBasedOnAgtCode(@RequestParam String agtCode){
		return voyageRegistrationService.getReqFieldsByAgtCode(agtCode).stream().map(this::convertToVoyageDTO).collect(Collectors.toList());
	}
	
	//http://localhost:8080/voyage/findAllVslByAppTyp/IMPORT
	@GetMapping(value = "findAllVslByAppTyp/{appTyp}" , produces = MediaType.APPLICATION_JSON_VALUE )
	public List<VoyageRegDTO> getMatchedAppTypVessels(@PathVariable String appTyp){
		List<VoyageRegDTO> voyageRegDTOList=new ArrayList<>();
		List<Object[]> vesselInfoList=voyageRegistrationService.findAllVesselsBasedOnAppTyp(appTyp);
		vesselInfoList.stream().forEach(x->{
			VoyageRegDTO dto=new VoyageRegDTO();
			dto.setImoNo(null!=x[0]?x[0].toString():"");
			dto.setVslName(null!=x[1]?x[1].toString():"");
			dto.setAppTyp(null!=x[2]?x[2].toString():"");
			dto.setAgtCode(null!=x[3]?x[3].toString():"");
			voyageRegDTOList.add(dto);
		});
		return voyageRegDTOList;
	}
	
	@GetMapping(value = "/propsInfo",produces = MediaType.APPLICATION_JSON_VALUE)
	public String featchconfigProps() throws JsonProcessingException {
		Map<String,Object> dataMap=new HashMap<>();
		String jsonString=null;
		dataMap.put("sladays",voyageRegistrationService.getSladays());
		dataMap.put("MOD",voyageRegistrationService.getMOD());
		ObjectMapper objectMapper=new ObjectMapper();
		jsonString=objectMapper.writeValueAsString(dataMap);
		return jsonString;
	}
	
	
	@PostMapping(value = "/createVoyage",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public VoyageRegDTO createVoyage(@RequestBody VoyageRegistrationEntity vr) {
		VoyageRegistrationEntity voyEntity=new VoyageRegistrationEntity(vr.getImoNo(),vr.getVslName(),vr.getAppTyp(),vr.getAgtCode(),vr.getAgtName(),vr.getLastPortCallCode(),vr.getLastPortCallName(),vr.getStatuscode());
		VoyageRegDTO dto=null;
		dto=convertToVoyageDTO(voyageRegistrationService.insertVoyage(voyEntity));
		return dto;
	}
	
     //http://localhost:8001/voyage/deleteById/2362
	 @DeleteMapping("/deleteById/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	    	voyageRegistrationService.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 //http://localhost:8001/voyage/updateById/2363
	 @PutMapping("/updateById/{id}")
	 public ResponseEntity<VoyageRegistrationEntity> updateVoyageDetails(@PathVariable("id") Long id,@RequestBody VoyageRegistrationEntity voyage){
		 Optional<VoyageRegistrationEntity> voyageData = voyageRegistrationService.findById(id);
		    if (voyageData.isPresent()) {
		    	VoyageRegistrationEntity dbData = voyageData.get();
		    	dbData.setAgtCode(voyage.getAgtCode());
		    	dbData.setAgtName(voyage.getAgtName());
		    	dbData.setVoygId(id);
		      return new ResponseEntity<>(voyageRegistrationService.insertVoyage(dbData), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
	 }
	 
	public List<VoyageRegDTO> getAllVesselsBasedOnImo(@PathVariable String imoNo){
		return null;
	}
	
	
	public VoyageRegDTO convertToVoyageDTO(VoyageRegistrationEntity voyageRegistrationEntity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		VoyageRegDTO voyageRegDTO = modelMapper.map(voyageRegistrationEntity, VoyageRegDTO.class);	
		return voyageRegDTO;
	}
	

}
