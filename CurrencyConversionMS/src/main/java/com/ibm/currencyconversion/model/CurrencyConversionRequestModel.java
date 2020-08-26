package com.ibm.currencyconversion.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class CurrencyConversionRequestModel {

	@NotNull(message = "Country Code can not null")
	@Size(min = 2, message = "Minmum 2 Charachter")
	private String countryCode;

	@NotNull(message = "Country factor can not null")
	private double countryfactor;

	public CurrencyConversionRequestModel() {

	}

	public CurrencyConversionRequestModel(String countryCode, double countryfactor) {
		super();
		this.countryCode = countryCode;
		this.countryfactor = countryfactor;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public double getCountryfactor() {
		return countryfactor;
	}

	public void setCountryfactor(double countryfactor) {
		this.countryfactor = countryfactor;
	}

}
