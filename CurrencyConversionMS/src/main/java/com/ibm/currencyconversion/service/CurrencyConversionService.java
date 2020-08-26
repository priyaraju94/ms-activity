package com.ibm.currencyconversion.service;

import com.ibm.currencyconversion.dto.CurrencyConversionDTO;

public interface CurrencyConversionService {

	
	CurrencyConversionDTO getCurrencyBycountrycode(String countrycode,double amount);
	CurrencyConversionDTO getCurrencyBycountrycodeFeign(double factor,double amount);
}
