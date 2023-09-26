package com.jmurienega.demo.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmurienega.demo.domain.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findByEmail(String email);
	Boolean existsByName(String name);
}
