package com.posadskiy.costaccounting.moneyactions.core.controller.impl;

import com.posadskiy.costaccounting.moneyactions.core.controller.UserController;
import com.posadskiy.costaccounting.moneyactions.core.db.UserRepository;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbIncome;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbPurchase;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbUser;
import com.posadskiy.costaccounting.moneyactions.core.exception.DeletingOldIncomeWithoutIdException;
import com.posadskiy.costaccounting.moneyactions.core.exception.DeletingOldPurchaseWithoutIdException;
import com.posadskiy.costaccounting.moneyactions.core.exception.UserDoesNotExistException;
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

        final DbPurchase deletedPurchase = CollectionUtils.find(foundUser.getPurchases(), dbPurchase -> StringUtils.equals(dbPurchase.getId(), purchaseId));
        if (deletedPurchase == null) throw new DeletingOldPurchaseWithoutIdException();

        final List<DbPurchase> purchasesWithoutDeleted = ListUtils.removeAll(foundUser.getPurchases(), Collections.singleton(deletedPurchase));

		/*categoryStatisticsController.decreaseMoneyActionToStatisticCategory(
			foundUser.getStatistics()
				.get(Utils.getMonthAndYear(deletedPurchase.getDate()))
				.getPurchaseCategories()
				.get(deletedPurchase.getCategory().getId())
			, deletedPurchase);*/

        foundUser.setPurchases(purchasesWithoutDeleted);
        return this.save(foundUser);
    }

    public DbUser deleteIncome(String userId, String incomeId) {
        DbUser foundUser = getById(userId);

        final DbIncome deletedIncome = CollectionUtils.find(foundUser.getIncomes(), dbIncome -> StringUtils.equals(dbIncome.getId(), incomeId));
        if (deletedIncome == null) throw new DeletingOldIncomeWithoutIdException();

        final List<DbIncome> incomesWithoutDeleted = ListUtils.removeAll(foundUser.getIncomes(), Collections.singleton(deletedIncome));

		/*categoryStatisticsController.decreaseMoneyActionToStatisticCategory(
			foundUser.getStatistics()
				.get(Utils.getMonthAndYear(deletedIncome.getDate()))
				.getIncomeCategories()
				.get(deletedIncome.getCategory().getId())
			, deletedIncome);*/

        foundUser.setIncomes(incomesWithoutDeleted);
        return this.save(foundUser);
    }
}
