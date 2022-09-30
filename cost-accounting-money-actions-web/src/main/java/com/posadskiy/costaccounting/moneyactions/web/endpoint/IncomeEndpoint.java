package com.posadskiy.costaccounting.moneyactions.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.api.request.IncomeRequest;
import com.posadskiy.costaccounting.moneyactions.core.controller.IncomeController;
import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("income")
public class IncomeEndpoint {

	private final IncomeController incomeController;

	@Autowired
	public IncomeEndpoint(IncomeController incomeController) {
		this.incomeController = incomeController;
	}

	@PostMapping("add")
	public void addIncome(@RequestBody final IncomeRequest incomeRequest) {
		incomeController.addIncome(incomeRequest.getUserId(), incomeRequest.getIncome());
	}

	@PostMapping("delete")
	public void deleteIncome(@RequestBody final IncomeRequest incomeRequest) {
		incomeController.deleteIncome(incomeRequest.getUserId(), incomeRequest.getIncomeId());
	}

	@PostMapping("categories")
	public List<Category> categories(@RequestBody final IncomeRequest incomeRequest) {
		return incomeController.getCategories(incomeRequest.getUserId());
	}
}
