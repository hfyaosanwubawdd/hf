package com.hf.entity;

import lombok.Data;

@Data
public class IpsEntity extends BaseEntity<Integer> {

	private String ip;
	private String datafrom;
	private Integer useport;
	private Integer state;
	private Integer type;
	
}
