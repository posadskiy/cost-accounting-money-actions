package com.posadskiy.costaccounting.moneyactions.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.core.controller.CurrencyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v0/currency")
public class CurrencyEndpoint {
	
	private final CurrencyController currencyController;

	@Autowired
	public CurrencyEndpoint(CurrencyController currencyController) {
		this.currencyController = currencyController;
	}

	@GetMapping("all")
	public List<String> all() {
		return currencyController.getCurrencies();
	}
}
