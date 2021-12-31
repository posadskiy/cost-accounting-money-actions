package com.posadskiy.costaccounting.moneyactions.core.controller;

import com.posadskiy.costaccounting.moneyactions.core.db.model.DbCategory;
import com.posadskiy.costaccounting.moneyactions.api.dto.Category;

import java.util.List;

public interface CategoryController {
	List<Category> getIncomeCategories();
	List<Category> getPurchaseCategories();
	DbCategory getById(String id);
	DbCategory getByName(String name);
	//String getNameWithEmoji(DbCategory category);
}
