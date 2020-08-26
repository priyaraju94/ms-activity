package com.ibm.jwtservice.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.jwtservice.db.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

}
