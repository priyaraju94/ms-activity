package com.ibm.conversionfactor.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.conversionfactor.dto.ConversionFactorDTO;
import com.ibm.conversionfactor.exception.ConversionFactorException;
import com.ibm.conversionfactor.model.ConversionFactorRequestModel;
import com.ibm.conversionfactor.model.ConversionFactorResponseModel;
import com.ibm.conversionfactor.service.ConversionFactorService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import feign.FeignException;

@RestController
@RequestMapping(path = "/conversion-factor")
public class ConversionFactorController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ConversionFactorService service;

	@PostMapping
	@ExceptionHandler(value = ConversionFactorException.class)
	public ResponseEntity<ConversionFactorResponseModel> addConversionFactor(
			@Valid @RequestBody ConversionFactorRequestModel reqmodel) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ConversionFactorDTO dto = mapper.map(reqmodel, ConversionFactorDTO.class);
		ConversionFactorDTO dto1 = service.addConversionFactor(dto);

		ConversionFactorResponseModel returnValue = mapper.map(dto1, ConversionFactorResponseModel.class);

		returnValue.setStatuscode(HttpStatus.CREATED.toString());
		return new ResponseEntity<ConversionFactorResponseModel>(returnValue, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<ConversionFactorResponseModel> updateConversionFactor(
			@Valid @RequestBody ConversionFactorRequestModel model) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ConversionFactorDTO dto = mapper.map(model, ConversionFactorDTO.class);
		ConversionFactorDTO dto1 = service.updateConversionFactor(dto);

		ConversionFactorResponseModel returnValue = mapper.map(dto1, ConversionFactorResponseModel.class);

		returnValue.setStatuscode(HttpStatus.CREATED.toString());
		return new ResponseEntity<ConversionFactorResponseModel>(returnValue, HttpStatus.CREATED);

	}

	
	 
	@ExceptionHandler(FeignException.class)
	@GetMapping(value = "/{countryCode}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ConversionFactorDTO getConversionFactorFeign(@PathVariable("countryCode") String countrycode) {
		ModelMapper mapper = new ModelMapper();
		ConversionFactorDTO dto = service.getfactorBycountrycode(countrycode);

		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ConversionFactorResponseModel returnValue = mapper.map(dto, ConversionFactorResponseModel.class);

		logger.info("{}", dto);

		return dto;
	}


}
