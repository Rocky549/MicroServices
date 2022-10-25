package com.rocky.ms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.rocky.ms.entity.VoyageRegistrationEntity;
import com.rocky.ms.repo.VoyageRegiRepo;



@Service
@ConfigurationProperties(prefix = "marine")
public class VoyageServiceImpl implements VoyageRegistrationService{

	private String sladays;
	private String MOD;

	@Autowired
	public VoyageRegiRepo voyageRegiRepo;
	
	public List<VoyageRegistrationEntity> getAllVoyageRecords() {
		return (List<VoyageRegistrationEntity>) voyageRegiRepo.findAll();
	}
	
	public List<VoyageRegistrationEntity>  findByVesselName(String vesselName) {
		return voyageRegiRepo.findByVslName(vesselName);
	}

	@Override
	public List<VoyageRegistrationEntity> findByAgtCode(String agtCode) {
		return voyageRegiRepo.findByAgtCode(agtCode);
	}

	@Override
	public List<VoyageRegistrationEntity> getReqFieldsByAgtCode(String agtCode) {
		return voyageRegiRepo.getReqFieldsBasedOnAgtCode(agtCode);
	}

	@Override
	public List<Object[]> findAllVesselsBasedOnAppTyp(String appTyp) {
		return voyageRegiRepo.featchAllVslInfoBasedOnAppTyp(appTyp);
	}

	@Override
	public List<VoyageRegistrationEntity[]> findAllVesselsBasedOnImoNo(String imoNo) {
		return voyageRegiRepo.featchAllVslInfoBasedOnImoNo(imoNo);
	}

	@Override
	public VoyageRegistrationEntity insertVoyage(VoyageRegistrationEntity voyageRegistrationEntity) {
		return voyageRegiRepo.save(voyageRegistrationEntity);
	}
	
	public String getSladays() {
		return sladays;
	}

	public String getMOD() {
		return MOD;
	}

	public void setSladays(String sladays) {
		this.sladays = sladays;
	}
	
	public void setMOD(String mOD) {
		MOD = mOD;
	}

	@Override
	public void deleteById(long id) {
		voyageRegiRepo.deleteById(id);
	}

	@Override
	public Optional<VoyageRegistrationEntity> findById(Long id) {
		return voyageRegiRepo.findById(id);
	}

	

}
