package com.posadskiy.costaccounting.moneyactions.core.db;

import com.posadskiy.costaccounting.moneyactions.core.db.model.DbSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<DbSession, String> {
}
