package com.novamart.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.novamart.entities.user.UserDetails;

public interface UsersRepository extends MongoRepository<UserDetails, String>{
	@Query(value = "{'username': ?0}")
	UserDetails fetchUserDetailsByUsername(String username);
}