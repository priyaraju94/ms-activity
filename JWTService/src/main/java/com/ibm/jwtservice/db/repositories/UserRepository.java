package com.ibm.jwtservice.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.jwtservice.db.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByLoginId(String loginId);
	Optional<User> findByLoginIdAndPassword(String loginId, String password);
}
