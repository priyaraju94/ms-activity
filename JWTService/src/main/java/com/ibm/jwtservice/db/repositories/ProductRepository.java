package com.ibm.jwtservice.db.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.jwtservice.db.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Optional<List<Product>> findByStoreId(int storeId);

}
