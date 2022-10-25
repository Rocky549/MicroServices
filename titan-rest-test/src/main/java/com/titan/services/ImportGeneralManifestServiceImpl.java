package com.titan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.titan.entities.ImportGeneralManifestBO;
import com.titan.repositories.ImportGeneralManifestRepositroy;



@Service
public class ImportGeneralManifestServiceImpl implements ImportGeneralManifestService{
    
	@Autowired
	ImportGeneralManifestRepositroy igmRepository;
	
	@Override
	public List<ImportGeneralManifestBO> getAllIgmRecoreds() {
		return igmRepository.findAll();
	}

	@Override
	public List<ImportGeneralManifestBO> findByVcnNum(String vcnNum) {
		return igmRepository.findByVcnNum(vcnNum);
	}
	
	@Override
	public void deleteById(long id) {
		igmRepository.deleteById(id);
	}

	@Override
	public ImportGeneralManifestBO insertIgmData(ImportGeneralManifestBO igmBo) {
		return igmRepository.save(igmBo);
	}

	@Override
	public ImportGeneralManifestBO findById(Long id) {
		return igmRepository.findByIgmid(id);
	}
		


	

	

}
