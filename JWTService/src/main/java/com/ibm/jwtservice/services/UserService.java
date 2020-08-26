package com.ibm.jwtservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.jwtservice.config.TokenService;
import com.ibm.jwtservice.db.entities.User;
import com.ibm.jwtservice.db.repositories.UserRepository;
import com.ibm.jwtservice.dto.LoginDTO;
import com.ibm.jwtservice.dto.UserDTO;
import com.ibm.jwtservice.dto.UserResponseDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TokenService tokenService;

	
	public Optional<String> generateToken(LoginDTO requestData) {
		return userRepo.findByLoginIdAndPassword(requestData.getLoginId(), requestData.getPassword()).map(usr -> {
			String token =tokenService.createToken(usr);
			return Optional.of(token);
		}).orElseGet(Optional :: empty);
	}

	public Optional<UserResponseDTO> createUser(UserDTO requestData) {
		User usr = userRepo.save(requestData.getEntity(0));
		return Optional.of(usr.getDto());
	}

	public Optional<UserResponseDTO> udpateUser(UserDTO requestData, int userId) {
		Optional<User> userInDb = userRepo.findById(userId);
		return userInDb.map(x -> {
			User usr = userRepo.save(requestData.getEntity(userId));
			return Optional.of(usr.getDto());
		}).orElseGet(Optional::empty);
	}

	public Optional<Integer> deleteUser(int userId) {
		Optional<User> userInDb = userRepo.findById(userId);
		return userInDb.map(x -> {
			userRepo.deleteById(userId);
			return Optional.of(userId);
		}).orElseGet(Optional::empty);
	}

	public Optional<UserResponseDTO> findUser(int userId) {
		Optional<User> userInDb = userRepo.findById(userId);
		return userInDb.map(x -> {
			return Optional.of(x.getDto());
		}).orElseGet(Optional::empty);
	}

	public List<UserResponseDTO> getAllUsers() {
		return userRepo.findAll().stream().map(User::getDto).collect(Collectors.toList());
	}
}
