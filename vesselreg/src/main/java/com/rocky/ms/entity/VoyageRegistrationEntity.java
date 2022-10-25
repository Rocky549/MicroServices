package com.rocky.ms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="POS_MAR_T_VOYG_REGSTON_VSL",schema = "dbo")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoyageRegistrationEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOYG_REGSTON_UID", updatable = false, nullable = false)
	private Long voygId;
	@Column(name="IMO_NUM")
	private String imoNo;
	@Column(name="VSL_NAME")
	private String vslName;
	@Column(name="APP_TYP")
	private String appTyp;
	@Column(name="AGT_CODE")
	private String agtCode;
	@Column(name="AGT_NAME")
	private String agtName;
	@Column(name="LAST_PRT_OF_CALL_CODE")
	private String lastPortCallCode;
	@Column(name="LAST_PRT_OF_CALL_NAME")
	private String lastPortCallName;
	@Column(name="STATUSCODE")
	private String statuscode;
	
	public VoyageRegistrationEntity(String imoNo,String vslName,String appTyp,String agtCode) {
		this.agtCode=agtCode;
		this.imoNo=imoNo;
		this.vslName=vslName;
		this.appTyp=appTyp;
	}
	public VoyageRegistrationEntity(String imoNo,String vslName,String appTyp,String agtCode,String agtName,String lastPortCallCode,String lastPortCallName,String statuscode) {
		this.agtCode=agtCode;
		this.imoNo=imoNo;
		this.vslName=vslName;
		this.appTyp=appTyp;
		this.agtName=agtName;
		this.agtCode=agtCode;
		this.lastPortCallCode=lastPortCallCode;
		this.lastPortCallName=lastPortCallName;
	}
}
