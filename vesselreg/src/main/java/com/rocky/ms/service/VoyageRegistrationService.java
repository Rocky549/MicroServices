package com.rocky.ms.service;

import java.util.List;
import java.util.Optional;

import com.rocky.ms.entity.*;


public interface VoyageRegistrationService {

	public List<VoyageRegistrationEntity> getAllVoyageRecords();

	public List<VoyageRegistrationEntity>  findByVesselName(String vesselName);

	public List<VoyageRegistrationEntity> findByAgtCode(String agtCode);

	public List<VoyageRegistrationEntity> getReqFieldsByAgtCode(String agtCode);
	
	public  List<Object[]> findAllVesselsBasedOnAppTyp(String appTyp);
	
	public List<VoyageRegistrationEntity[]> findAllVesselsBasedOnImoNo(String imoNo);

	public VoyageRegistrationEntity insertVoyage(VoyageRegistrationEntity voyageRegistrationEntity);
	public String getMOD();
	public String getSladays();
	public void setMOD(String mOD);
	public void setSladays(String sladays);
	public void deleteById(long id);
	public Optional<VoyageRegistrationEntity> findById(Long id);
}
