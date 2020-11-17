package com.asminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asminds.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	

}
