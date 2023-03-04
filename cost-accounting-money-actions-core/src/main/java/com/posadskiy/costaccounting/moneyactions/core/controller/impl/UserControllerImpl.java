package com.posadskiy.costaccounting.moneyactions.core.controller.impl;

import com.posadskiy.costaccounting.moneyactions.core.controller.UserController;
import com.posadskiy.costaccounting.moneyactions.core.db.UserRepository;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbIncome;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbPurchase;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbUser;
import com.posadskiy.costaccounting.moneyactions.core.exception.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserControllerImpl implements UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserControllerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DbUser getById(String userId) {
        Optional<DbUser> foundUser = userRepository.findById(userId);
        if (!foundUser.isPresent()) throw new UserDoesNotExistException();

        return foundUser.get();
    }

    @Override
    public DbUser save(@NotNull DbUser dbUser) {
        return userRepository.save(dbUser);
    }
    
    @Override
    public Optional<DbPurchase> getPurchase(String userId, String purchaseId) {
        DbUser foundUser = getById(userId);

        final List<DbPurchase> purchases = foundUser.getPurchases();
        if (CollectionUtils.isEmpty(purchases)) {
            throw new UserDoesNotHaveAnyPurchaseException();
        }

        return purchases.stream()
            .filter(purchase -> purchase != null &&purchase.getId() != null && purchaseId.equals(purchase.getId()))
            .findFirst();
    }

    @Override
    public Optional<DbIncome> getIncome(String userId, String incomeId) {
        DbUser foundUser = getById(userId);

        final List<DbIncome> incomes = foundUser.getIncomes();
        if (CollectionUtils.isEmpty(incomes)) {
            throw new UserDoesNotHaveAnyIncomeException();
        }

        return incomes.stream()
            .filter(income -> income != null && income.getId() != null && incomeId.equals(income.getId()))
            .findFirst();
    }

    @Override
    public void savePurchase(String userId, DbPurchase dbPurchase) {
        DbUser foundUser = getById(userId);

        foundUser.getPurchases().add(dbPurchase);

        //categoryStatisticsController.increasePurchaseToStatisticCategory(foundUser, dbPurchase);

        this.save(foundUser);
    }

    @Override
    public void saveIncome(String userId, DbIncome dbIncome) {
        DbUser foundUser = getById(userId);

        foundUser.getIncomes().add(dbIncome);

        //categoryStatisticsController.increaseIncomeToStatisticCategory(foundUser, dbIncome);

        this.save(foundUser);
    }

    public DbUser deletePurchase(String userId, String purchaseId) {
        DbUser foundUser = getById(userId);

        final Optional<DbPurchase> deletedPurchase = foundUser.getPurchases()
            .stream()
            .filter(purchase -> purchase != null && purchase.getId() != null && purchaseId.equals(purchase.getId()))
            .findFirst();

        if (!deletedPurchase.isPresent()) {
            throw new DeletingOldPurchaseWithoutIdException();
        }

        foundUser.getPurchases().remove(deletedPurchase.get());
		/*categoryStatisticsController.decreaseMoneyActionToStatisticCategory(
			foundUser.getStatistics()
				.get(Utils.getMonthAndYear(deletedPurchase.getDate()))
				.getPurchaseCategories()
				.get(deletedPurchase.getCategory().getId())
			, deletedPurchase);*/

        return this.save(foundUser);
    }

    public DbUser deleteIncome(String userId, String incomeId) {
        DbUser foundUser = getById(userId);

        final Optional<DbIncome> deletedIncome = foundUser.getIncomes()
            .stream()
            .filter(income -> income != null && income.getId() != null && incomeId.equals(income.getId()))
            .findFirst();
        
        if (!deletedIncome.isPresent()) {
            throw new DeletingOldIncomeWithoutIdException();
        }

        foundUser.getIncomes().remove(deletedIncome.get());

		/*categoryStatisticsController.decreaseMoneyActionToStatisticCategory(
			foundUser.getStatistics()
				.get(Utils.getMonthAndYear(deletedIncome.getDate()))
				.getIncomeCategories()
				.get(deletedIncome.getCategory().getId())
			, deletedIncome);*/

        return this.save(foundUser);
    }
}
