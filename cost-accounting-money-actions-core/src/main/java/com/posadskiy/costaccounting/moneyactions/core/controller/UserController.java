package com.posadskiy.costaccounting.moneyactions.core.controller;

import com.posadskiy.costaccounting.moneyactions.core.db.model.DbIncome;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbPurchase;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbUser;
import com.posadskiy.costaccounting.moneyactions.api.dto.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface UserController {
	DbUser getById(String userId);
	DbUser save(@NotNull DbUser dbUser);
	void savePurchase(String userId, DbPurchase dbPurchase);
	void saveIncome(String userId, DbIncome dbIncome);
	DbUser deletePurchase(String userId, String purchaseId);
	DbUser deleteIncome(String userId, String incomeId);

}
