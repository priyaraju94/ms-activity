
package com.ibm.currencyconversion.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.currencyconversion.dto.CurrencyConversionDTO;

//@FeignClient(name= "currency-conversion-service" ,url="localhost:8100") 
@FeignClient(name = "conversion-factor-ms")
@RibbonClient(name = "conversion-factor-ms")

public interface CurrencyConversionServiceProxy {

	@GetMapping(path = "/conversion-factor/{countryCode}", produces = "application/json")
	public CurrencyConversionDTO getConversionFactorFeign(@PathVariable("countryCode") String countrycode);

	/*
	 * @GetMapping(path = "/conversion-factor/{countryCode}", produces =
	 * "application/json") public CurrencyConversionDTO
	 * addConversionFactorFeign(@PathVariable("countryCode") String countrycode);
	 * 
	 * @GetMapping(path = "/conversion-factor/{countryCode}", produces =
	 * "application/json") public CurrencyConversionDTO
	 * updateConversionFactorFeign(@PathVariable("countryCode") String countrycode);
	 */
}
