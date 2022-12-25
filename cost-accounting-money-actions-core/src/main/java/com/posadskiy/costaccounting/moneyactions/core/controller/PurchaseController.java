package com.posadskiy.costaccounting.moneyactions.core.controller;

import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public interface PurchaseController {
	void addPurchase(@NotNull final String userId, @NotNull final Purchase purchase);
	void deletePurchase(@NotNull final String userId, @NotNull final String purchaseId);
}
