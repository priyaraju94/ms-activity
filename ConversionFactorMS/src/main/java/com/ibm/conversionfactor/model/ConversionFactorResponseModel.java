package com.ibm.conversionfactor.model;

public class ConversionFactorResponseModel {
	private String countryCode;
	private double countryfactor;
	private String errorMessage;
	private String statuscode;

	public ConversionFactorResponseModel() {

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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

}
