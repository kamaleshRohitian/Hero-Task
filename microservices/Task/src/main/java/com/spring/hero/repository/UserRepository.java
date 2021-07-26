package com.spring.hero.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.hero.entity.UserDetails;

@Repository
public interface UserRepository extends CrudRepository<UserDetails,Long> {
	List<UserDetails> findByUsername(String username);

}
