package com.posadskiy.costaccounting.moneyactions.web.endpoint.request;

import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import lombok.Data;

@Data
public class IncomeRequest {
	private String userId;
	private String incomeId;
	private Income income;
}
