package com.titan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImportGeneralManifestDTO {
	private String igmNum;
	private String vslName;
	private String vcnNum;
	private String imoCode;
}
