package com.posadskiy.costaccounting.moneyactions.core.db;

import com.posadskiy.costaccounting.moneyactions.core.db.model.DbProject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<DbProject, String> {
}
