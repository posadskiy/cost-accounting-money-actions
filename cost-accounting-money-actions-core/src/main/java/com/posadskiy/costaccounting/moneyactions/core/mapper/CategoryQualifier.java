package com.posadskiy.costaccounting.moneyactions.core.mapper;

import com.posadskiy.costaccounting.moneyactions.core.controller.CategoryController;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbCategory;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryQualifier {
	@Autowired
	private CategoryController categoryController;
	
	DbCategory qualifyCategoryIdToInstance(String categoryId) {
		return categoryController.getById(categoryId);
	}
}
