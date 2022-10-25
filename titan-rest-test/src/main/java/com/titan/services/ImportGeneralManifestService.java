package com.titan.services;

import java.util.List;

import com.titan.entities.ImportGeneralManifestBO;


public interface ImportGeneralManifestService {
	public List<ImportGeneralManifestBO> getAllIgmRecoreds();
	public List<ImportGeneralManifestBO>  findByVcnNum(String vcnNum);
	public ImportGeneralManifestBO insertIgmData(ImportGeneralManifestBO igmBo);
	public void deleteById(long id);
	public ImportGeneralManifestBO findById(Long id);
}
