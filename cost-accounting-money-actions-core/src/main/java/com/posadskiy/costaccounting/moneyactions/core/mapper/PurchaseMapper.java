package com.posadskiy.costaccounting.moneyactions.core.mapper;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.enums.Currency;
import com.posadskiy.costaccounting.moneyactions.core.db.model.DbPurchase;
import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import com.posadskiy.currencyconverter.exception.CurrencyConverterException;
import org.apache.commons.lang3.RandomStringUtils;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Mapper(componentModel = "spring")
@Component
public interface PurchaseMapper {

	@Mapping(source = "id", target = "id", qualifiedByName = "mapIdToDbId")
	@Mapping(source = "name", target = "name")
	@Mapping(target = "amount", ignore = true)
	@Mapping(source = "date", target = "date")
	@Mapping(source = "isPrivate", target = "isPrivate")
	DbPurchase mapFromDto(Purchase purchase, @Context CurrencyConverter currencyConverter);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "isPrivate", target = "isPrivate")
    Purchase mapToDto(DbPurchase dbPurchase);

	@Named("idToDbId")
	default String mapIdToDbId(String id) {
		return id != null ? id : RandomStringUtils.randomAlphabetic(10);
	}

	@AfterMapping
	default void mapAmountAndCurrencyToAmount(@MappingTarget DbPurchase target, Purchase purchase, @Context CurrencyConverter currencyConverter) {
        final Optional<Currency> byCode = Currency.findByCode(purchase.getCurrency());
        if (!byCode.isPresent()) {
            throw new CurrencyConverterException("Currency doesn't supported");
        }

        Double rate = currencyConverter.rateToUsd(byCode.get());
		target.setAmount(purchase.getAmount() * rate);
	}
}
