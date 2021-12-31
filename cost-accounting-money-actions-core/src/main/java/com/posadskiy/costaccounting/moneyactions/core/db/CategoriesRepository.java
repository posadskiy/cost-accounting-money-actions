package com.posadskiy.costaccounting.moneyactions.core.db;

import com.posadskiy.costaccounting.moneyactions.core.db.model.DbCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriesRepository extends MongoRepository<DbCategory, String> {
	DbCategory findByName(String name);
}
