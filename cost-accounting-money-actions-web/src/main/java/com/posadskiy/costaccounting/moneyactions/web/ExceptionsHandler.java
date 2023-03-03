package com.posadskiy.costaccounting.moneyactions.web;

import com.posadskiy.costaccounting.moneyactions.core.exception.IncomeDoesNotExistOrTooOldException;
import com.posadskiy.costaccounting.moneyactions.core.exception.PurchaseDoesNotExistOrTooOldException;
import com.posadskiy.costaccounting.moneyactions.core.exception.UserDoesNotHaveAnyPurchaseException;
import com.posadskiy.costaccounting.moneyactions.web.dto.RestException;
import com.posadskiy.currencyconverter.exception.CurrencyConverterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(UserDoesNotHaveAnyPurchaseException.class)
	public ResponseEntity<?> userDoesNotHaveAnyPurchaseException(UserDoesNotHaveAnyPurchaseException exception) {
		log.debug("UserDoesNotHaveAnyPurchaseException", exception);
		RestException restException = new RestException("User doesn't have purchases", 1, "User doesn't have purchases");
		return new ResponseEntity<>(restException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PurchaseDoesNotExistOrTooOldException.class)
	public ResponseEntity<?> purchaseDoesNotExistOrTooOldException(PurchaseDoesNotExistOrTooOldException exception) {
		log.debug("PurchaseDoesNotExistOrTooOldException", exception);
		RestException restException = new RestException("Request problem", 1, "Purchase doesn't exist or too old");
		return new ResponseEntity<>(restException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IncomeDoesNotExistOrTooOldException.class)
	public ResponseEntity<?> incomeDoesNotExistOrTooOldException(IncomeDoesNotExistOrTooOldException exception) {
		log.debug("IncomeDoesNotExistOrTooOldException", exception);
		RestException restException = new RestException("Request problem", 2, "Income doesn't exist or too old");
		return new ResponseEntity<>(restException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CurrencyConverterException.class)
	public ResponseEntity<?> currencyConverterException(CurrencyConverterException exception) {
		log.debug("CurrencyConverterException", exception);
		RestException restException = new RestException("Currency conversion problem", 3, "Provided currency isn't supported");
		return new ResponseEntity<>(restException, HttpStatus.BAD_REQUEST);
	}

}
