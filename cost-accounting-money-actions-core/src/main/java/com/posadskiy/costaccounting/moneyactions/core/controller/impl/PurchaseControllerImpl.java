package com.posadskiy.costaccounting.moneyactions.core.controller.impl;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.costaccounting.moneyactions.core.controller.*;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbPurchase;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbUser;
import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import com.posadskiy.costaccounting.moneyactions.core.exception.PurchaseDoesNotExistOrTooOldException;
import com.posadskiy.costaccounting.moneyactions.core.mapper.PurchaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class PurchaseControllerImpl implements PurchaseController {
	
	private final UserController userController;
	private final ProjectController projectController;
	private final PurchaseMapper purchaseMapper;
	private final CurrencyConverter currencyConverter;
	
	@Autowired
	public PurchaseControllerImpl(UserController userController, ProjectController projectController, PurchaseMapper purchaseMapper, CurrencyConverter currencyConverter) {
		this.userController = userController;
		this.projectController = projectController;
		this.purchaseMapper = purchaseMapper;
		this.currencyConverter = currencyConverter;
	}

    @Override
    public Purchase getPurchase(@NotNull String userId, @NotNull String purchaseId) {
        final Optional<DbPurchase> purchase = userController.getPurchase(userId, purchaseId);
        if (!purchase.isPresent()) {
            throw new PurchaseDoesNotExistOrTooOldException();
        }

        return purchaseMapper.mapToDto(
            purchase.get()
        );
    }

	public Purchase addPurchase(@NotNull final String userId, @NotNull final Purchase purchase) {
		final DbUser foundUser = userController.getById(userId);
		final DbPurchase dbPurchase = purchaseMapper.mapFromDto(purchase, currencyConverter);

		userController.savePurchase(foundUser.getId(), dbPurchase);
		projectController.savePurchase(foundUser.getProjectId(), dbPurchase);
        
        return purchaseMapper.mapToDto(
            dbPurchase
        );
	}

	@Override
	public void deletePurchase(@NotNull String userId, @NotNull String purchaseId) {
		final DbUser foundUser = userController.getById(userId);

		userController.deletePurchase(foundUser.getId(), purchaseId);
		final DbPurchase purchaseForDeleting = getPurchaseById(foundUser.getPurchases(), purchaseId);
		projectController.deletePurchase(foundUser.getProjectId(), purchaseForDeleting);
	}

	private @NotNull DbPurchase getPurchaseById(@NotNull  List<DbPurchase> purchases, @NotNull String purchaseId) {
		final Optional<DbPurchase> possiblePurchaseForDeleting = purchases
			.stream()
			.filter(dbPurchase -> StringUtils.equals(dbPurchase.getId(), purchaseId))
			.findFirst();
		if (!possiblePurchaseForDeleting.isPresent()) throw new PurchaseDoesNotExistOrTooOldException();
		return possiblePurchaseForDeleting.get();
	}
}
