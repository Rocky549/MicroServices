package com.rocky.gc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rocky.gc.entity.ImportGeneralManifest;

@Repository
public interface IgmRepository extends JpaRepository<ImportGeneralManifest, Long> {

	List<ImportGeneralManifest> findByIgmNum(String igmNo);

}
