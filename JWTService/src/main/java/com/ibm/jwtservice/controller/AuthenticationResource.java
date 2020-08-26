package com.ibm.jwtservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.jwtservice.dto.LoginDTO;
import com.ibm.jwtservice.services.UserService;

@RestController

public class AuthenticationResource {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/authorize")
	public ResponseEntity<String> authenticate(@RequestBody LoginDTO requestData) {

		
		return userService.generateToken(requestData).map(data -> {
			return ResponseEntity.ok().body(data);
		}).orElseGet(() -> {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		});
		
		
	}
}
