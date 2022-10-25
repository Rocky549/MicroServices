package com.rocky.cb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoyageRegDTO {
	private String imoNo;
	private String vslName;
	private String appTyp;
	private String agtCode;
}
	