package com.posadskiy.costaccounting.moneyactions.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestException {
	private String title;
	private int code;
	private String message;
}
