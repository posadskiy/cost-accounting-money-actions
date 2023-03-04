package com.posadskiy.costaccounting.moneyactions.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import com.posadskiy.costaccounting.moneyactions.api.request.PurchaseRequest;
import com.posadskiy.costaccounting.moneyactions.core.controller.PurchaseController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v0/purchase")
public class PurchaseEndpoint {

	private final PurchaseController purchaseController;

	public PurchaseEndpoint(PurchaseController purchaseController) {
		this.purchaseController = purchaseController;
	}

    @PostMapping("get")
    public Purchase getPurchase(@RequestBody final PurchaseRequest purchaseRequest) {
        return purchaseController.getPurchase(purchaseRequest.getUserId(), purchaseRequest.getPurchase().getId());
    }

	@PostMapping("add")
	public Purchase addPurchase(@RequestBody final PurchaseRequest purchaseRequest) {
		return purchaseController.addPurchase(purchaseRequest.getUserId(), purchaseRequest.getPurchase());
	}
	
	@PostMapping("delete")
	public void deletePurchase(@RequestBody final PurchaseRequest purchaseRequest) {
		purchaseController.deletePurchase(purchaseRequest.getUserId(), purchaseRequest.getPurchaseId());
	}

}
