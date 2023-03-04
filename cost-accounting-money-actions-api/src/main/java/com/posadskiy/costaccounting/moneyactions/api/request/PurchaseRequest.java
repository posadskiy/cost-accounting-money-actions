package com.posadskiy.costaccounting.moneyactions.api.request;

import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import lombok.Data;

@Data
public class PurchaseRequest {
	private String userId;

    // TODO: remove
	private String purchaseId;
	private Purchase purchase;
}
