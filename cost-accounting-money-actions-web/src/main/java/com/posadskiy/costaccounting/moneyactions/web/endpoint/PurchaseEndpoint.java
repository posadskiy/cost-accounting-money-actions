package com.posadskiy.costaccounting.moneyactions.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.api.request.PurchaseRequest;
import com.posadskiy.costaccounting.moneyactions.core.controller.PurchaseController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("purchase")
public class PurchaseEndpoint {

	private final PurchaseController purchaseController;

	public PurchaseEndpoint(PurchaseController purchaseController) {
		this.purchaseController = purchaseController;
	}

	@PostMapping("add")
	public void addPurchase(@RequestBody final PurchaseRequest purchaseRequest) {
		purchaseController.addPurchase(purchaseRequest.getUserId(), purchaseRequest.getPurchase());
	}
	
	@PostMapping("delete")
	public void deletePurchase(@RequestBody final PurchaseRequest purchaseRequest) {
		purchaseController.deletePurchase(purchaseRequest.getUserId(), purchaseRequest.getPurchaseId());
	}

}
