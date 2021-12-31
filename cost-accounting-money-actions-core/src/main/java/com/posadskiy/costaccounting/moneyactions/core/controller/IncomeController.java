package com.posadskiy.costaccounting.moneyactions.core.controller;

import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IncomeController {
	void addIncome(@NotNull final String userId, @NotNull final Income income);
	void deleteIncome(@NotNull final String userId, @NotNull final String incomeId);
	List<Category> getCategories(@NotNull final String userId);
}
