package com.posadskiy.costaccounting.moneyactions.core.db.model;

import lombok.Data;

@Data
public class DbCategoryCurrentLimit {
	private DbCategory category;
	private Double limit;
}
