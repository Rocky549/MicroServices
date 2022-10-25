package com.titan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.titan.entities.ImportGeneralManifestBO;

@Repository
public interface ImportGeneralManifestRepositroy extends JpaRepository<ImportGeneralManifestBO, Long> {
	List<ImportGeneralManifestBO> findByVcnNum(String vcnNum);
	ImportGeneralManifestBO findByIgmid(long id);
}
