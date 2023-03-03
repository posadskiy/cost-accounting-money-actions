package com.posadskiy.costaccounting.moneyactions.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import com.posadskiy.costaccounting.moneyactions.api.request.IncomeRequest;
import com.posadskiy.costaccounting.moneyactions.core.controller.IncomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v0/income")
public class IncomeEndpoint {

	private final IncomeController incomeController;

	@Autowired
	public IncomeEndpoint(IncomeController incomeController) {
		this.incomeController = incomeController;
	}

	@PostMapping("get")
	public Income getIncome(@RequestBody final IncomeRequest incomeRequest) {
		return incomeController.getIncome(incomeRequest.getUserId(), incomeRequest.getIncome().getId());
	}

	@PostMapping("add")
	public String addIncome(@RequestBody final IncomeRequest incomeRequest) {
		return incomeController.addIncome(incomeRequest.getUserId(), incomeRequest.getIncome());
	}

	@PostMapping("delete")
	public void deleteIncome(@RequestBody final IncomeRequest incomeRequest) {
		incomeController.deleteIncome(incomeRequest.getUserId(), incomeRequest.getIncomeId());
	}

}
