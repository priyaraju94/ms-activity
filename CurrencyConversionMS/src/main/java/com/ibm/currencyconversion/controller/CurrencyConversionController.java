package com.ibm.currencyconversion.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.currencyconversion.dto.CurrencyConversionDTO;
import com.ibm.currencyconversion.model.CurrencyConversionResponseModel;
import com.ibm.currencyconversion.service.CurrencyConversionServiceProxy;
import com.ibm.currencyconversion.service.CurrencyConversionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@EnableHystrix
@ControllerAdvice
public class CurrencyConversionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CurrencyConversionService service;
	@Autowired
	private CurrencyConversionServiceProxy serviceproxy;


	@GetMapping(path = "/exchange-feign/{countryCode}/{amount}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@HystrixCommand(fallbackMethod = "fault_Error")
	public CurrencyConversionDTO convertCurrencyFeign(@PathVariable("countryCode") String countrycode,
			@PathVariable("amount") double amount) throws Exception {

		CurrencyConversionDTO dto = serviceproxy.getConversionFactorFeign(countrycode);

		dto.setCountryCode(countrycode);
		dto.setCountryfactor(dto.getCountryfactor());
		dto.setConversionamount(amount * dto.getCountryfactor());
		CurrencyConversionResponseModel returnValue = new ModelMapper().map(dto, CurrencyConversionResponseModel.class);
		logger.info("{}", dto);

		return dto;

	}

	public CurrencyConversionDTO fault_Error(String a, double b) {
		return new CurrencyConversionDTO("default_US", 10.0);
	}

}
