package com.rocky.cb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IgmDTO {
	private String igmNum;
	private String vesselName;
	private String vcnNum;
	private String imoCode;
}
