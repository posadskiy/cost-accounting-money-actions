package com.posadskiy.costaccounting.moneyactions.core.controller;

import com.posadskiy.costaccounting.moneyactions.core.db.model.DbSession;

public interface SessionController {
	DbSession create(String sessionId, String userId);
}
