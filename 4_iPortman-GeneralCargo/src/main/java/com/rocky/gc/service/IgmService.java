package com.rocky.gc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rocky.gc.entity.ImportGeneralManifest;

@Service
public interface IgmService {

	public List<ImportGeneralManifest> getAllIgmReocrds();

	public List<ImportGeneralManifest> getIgmDataBasedOnIgmNum(String igmNO);

}
