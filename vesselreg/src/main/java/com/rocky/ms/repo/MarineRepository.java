package com.rocky.ms.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MarineRepository<T, Long extends Serializable> /* extends JpaRepository<T, Long> */ {
	   @Query("select new com.rocky.ms.entity.VoyageRegistrationEntity(v.imoNo,v.vslName,v.appTyp,v.agtCode) from VoyageRegistrationEntity v where v.agtCode=:agtCode")
	   List<T> getReqFieldsBasedOnAgtCode(@Param("agtCode") String agtCode);
	   @Query("select v.imoNo,v.vslName,v.appTyp,v.agtCode from VoyageRegistrationEntity v where v.statuscode='DRAFT' AND v.appTyp=:apptyp")
	   List<Object[]> featchAllVslInfoBasedOnAppTyp(@Param("apptyp") String appTyp);
	   @Query("select v.vslName,v.appTyp,v.agtCode from VoyageRegistrationEntity v where v.statuscode='DRAFT' AND v.imoNo=:imoNo")
	   List<T[]> featchAllVslInfoBasedOnImoNo(@Param("imoNo") String imoNo);
}
