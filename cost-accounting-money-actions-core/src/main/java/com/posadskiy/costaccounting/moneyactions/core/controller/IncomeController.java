package com.posadskiy.costaccounting.moneyactions.core.controller;

import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import org.jetbrains.annotations.NotNull;

public interface IncomeController {
	Income getIncome(@NotNull final String userId, @NotNull final String incomeId);
	String addIncome(@NotNull final String userId, @NotNull final Income income);
	void deleteIncome(@NotNull final String userId, @NotNull final String incomeId);
}
