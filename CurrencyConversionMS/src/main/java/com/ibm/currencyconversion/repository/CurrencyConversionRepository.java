package com.ibm.currencyconversion.repository;

import org.springframework.data.repository.CrudRepository;

import com.ibm.currencyconversion.entity.CurrencyConversionEntity;


public interface CurrencyConversionRepository extends CrudRepository<CurrencyConversionEntity, Long> {

	// ConversionFactorEntity findById(String Id);
	CurrencyConversionEntity findBycountryCode(String countrycode);
}
