package com.posadskiy.costaccounting.moneyactions.core.controller;

import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import org.jetbrains.annotations.NotNull;

public interface PurchaseController {
    Purchase getPurchase(@NotNull final String userId, @NotNull final String purchaseId);

    String addPurchase(@NotNull final String userId, @NotNull final Purchase purchase);
	void deletePurchase(@NotNull final String userId, @NotNull final String purchaseId);
}
