package com.rocky.gc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocky.gc.entity.ImportGeneralManifest;
import com.rocky.gc.repo.IgmRepository;

@Service
public class IgmServiceImpl implements IgmService {
 
	@Autowired
	private IgmRepository igmRepository;

	@Override
	public List<ImportGeneralManifest> getAllIgmReocrds() {
		System.out.println("================="+igmRepository.findAll().size());
		return igmRepository.findAll();
	}

	@Override
	public List<ImportGeneralManifest> getIgmDataBasedOnIgmNum(String igmNO) {
		return igmRepository.findByIgmNum(igmNO);
	}
	
}
