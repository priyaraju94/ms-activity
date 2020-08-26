package com.ibm.jwtservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.jwtservice.db.entities.Store;
import com.ibm.jwtservice.db.repositories.StoreRepository;
import com.ibm.jwtservice.dto.StoreDTO;
import com.ibm.jwtservice.dto.StoreResponseDTO;

@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepo;

	public Optional<StoreResponseDTO> createStore(StoreDTO requestData) {
		Store store = storeRepo.save(requestData.getEntity(0));
		return Optional.of(store.getDto());
	}

	public Optional<StoreResponseDTO> udpateStore(StoreDTO requestData, int userId) {
		Optional<Store> userInDb = storeRepo.findById(userId);
		return userInDb.map(x -> {
			Store store = storeRepo.save(requestData.getEntity(userId));
			return Optional.of(store.getDto());
		}).orElseGet(Optional::empty);
	}

	public Optional<Integer> deleteStore(int userId) {
		Optional<Store> userInDb = storeRepo.findById(userId);
		return userInDb.map(x -> {
			storeRepo.deleteById(userId);
			return Optional.of(userId);
		}).orElseGet(Optional::empty);
	}

	public Optional<StoreResponseDTO> findStore(int storeId){
		Optional<Store> userInDb = storeRepo.findById(storeId);
		return userInDb.map(x -> {
			return Optional.of(x.getDto());
		}).orElseGet(Optional::empty);
	}
	
	
	public List<StoreResponseDTO> getAllStores() {
		return storeRepo.findAll().stream().map(Store::getDto).collect(Collectors.toList());
	}
}
