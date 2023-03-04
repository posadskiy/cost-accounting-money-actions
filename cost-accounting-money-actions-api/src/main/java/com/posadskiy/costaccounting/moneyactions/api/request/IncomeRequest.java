package com.posadskiy.costaccounting.moneyactions.api.request;

import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import lombok.Data;

@Data
public class IncomeRequest {
	private String userId;
    
    // TODO: remove
	private String incomeId;
	private Income income;
}
