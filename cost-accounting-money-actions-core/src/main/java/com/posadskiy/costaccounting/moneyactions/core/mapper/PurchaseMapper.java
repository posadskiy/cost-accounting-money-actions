package com.posadskiy.costaccounting.moneyactions.core.mapper;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.enums.Currency;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbPurchase;
import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import org.apache.commons.lang3.RandomStringUtils;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PurchaseMapper {

	@Mapping(source = "id", target = "id", qualifiedByName = "mapIdToDbId")
	@Mapping(source = "name", target = "name")
	@Mapping(target = "amount", ignore = true)
	@Mapping(source = "date", target = "date")
	@Mapping(source = "isPrivate", target = "isPrivate")
	DbPurchase mapFromDto(Purchase purchase, @Context CurrencyConverter currencyConverter);

	@Named("idToDbId")
	default String mapIdToDbId(String id) {
		return id != null ? id : RandomStringUtils.randomAlphabetic(10);
	}

	@AfterMapping
	default void mapAmountAndCurrencyToAmount(@MappingTarget DbPurchase target, Purchase purchase, @Context CurrencyConverter currencyConverter) {
		Double rate = currencyConverter.rateToUsd(Currency.valueOf(purchase.getCurrency()));
		target.setAmount(purchase.getAmount() * rate);
	}
}
