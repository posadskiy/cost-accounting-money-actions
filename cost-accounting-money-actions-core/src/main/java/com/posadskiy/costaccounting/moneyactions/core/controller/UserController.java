package com.posadskiy.costaccounting.moneyactions.core.controller;

import com.posadskiy.costaccounting.moneyactions.core.db.model.DbIncome;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbPurchase;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbUser;
import com.posadskiy.costaccounting.moneyactions.api.dto.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface UserController {
	DbUser getById(String userId);
	User getMappedById(String userId);
	DbUser getByEmail(String email);
	DbUser getByChatId(Long chatId);
	DbUser save(@NotNull DbUser dbUser);
	void savePurchase(String userId, DbPurchase dbPurchase);
	void saveIncome(String userId, DbIncome dbIncome);
	List<DbPurchase> lastPurchases(String userId);
	List<DbIncome> lastIncomes(String userId);
	DbUser deletePurchase(String userId, String purchaseId);
	DbUser deleteIncome(String userId, String incomeId);
	List<User> getAllUsersByUserId(String userId);
	List<DbUser> getAllUsersByProjectId(String userId);
	User updateUser(User user);
}
