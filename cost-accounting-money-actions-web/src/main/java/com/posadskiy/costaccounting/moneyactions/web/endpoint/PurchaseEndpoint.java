package com.posadskiy.costaccounting.moneyactions.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.api.request.PurchaseRequest;
import com.posadskiy.costaccounting.moneyactions.core.controller.PurchaseController;
import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	
	@PostMapping("categories")
	public List<Category> categories(@RequestBody final PurchaseRequest purchaseRequest) {
		return purchaseController.getCategories(purchaseRequest.getUserId());
	}
}
