package com.posadskiy.costaccounting.moneyactions.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Income {
	private String id;
	private String category;
	private Double amount;
	private String currency;
	private String name;
	private Date date;
	private Boolean isPrivate;
}
