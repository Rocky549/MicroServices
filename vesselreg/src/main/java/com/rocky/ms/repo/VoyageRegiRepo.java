package com.rocky.ms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rocky.ms.entity.VoyageRegistrationEntity;



@Repository
public interface VoyageRegiRepo extends JpaRepository<VoyageRegistrationEntity, Long> {
	List<VoyageRegistrationEntity> findByVslName(String vslName);
	List<VoyageRegistrationEntity> findByAgtCode(String agtCode);
	List<VoyageRegistrationEntity> findAll();
	@Query("select new com.rocky.ms.entity.VoyageRegistrationEntity(v.imoNo,v.vslName,v.appTyp,v.agtCode) from VoyageRegistrationEntity v where v.agtCode=:agtCode")
	List<VoyageRegistrationEntity> getReqFieldsBasedOnAgtCode(@Param("agtCode") String agtCode);
	@Query("select v.imoNo,v.vslName,v.appTyp,v.agtCode from VoyageRegistrationEntity v where v.statuscode='DRAFT' AND v.appTyp=:apptyp")
	List<Object[]> featchAllVslInfoBasedOnAppTyp(@Param("apptyp") String appTyp);
	@Query("select v.vslName,v.appTyp,v.agtCode from VoyageRegistrationEntity v where v.statuscode='DRAFT' AND v.imoNo=:imoNo")
	List<VoyageRegistrationEntity[]> featchAllVslInfoBasedOnImoNo(@Param("imoNo") String imoNo);
}
