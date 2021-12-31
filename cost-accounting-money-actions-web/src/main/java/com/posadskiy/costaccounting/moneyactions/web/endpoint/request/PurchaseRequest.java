package com.posadskiy.costaccounting.moneyactions.web.endpoint.request;

import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import lombok.Data;

@Data
public class PurchaseRequest {
	private String userId;
	private String purchaseId;
	private Purchase purchase;
}
