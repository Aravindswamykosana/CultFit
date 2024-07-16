package com.jsp.CultFit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.CultFit.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	public abstract User findByEmail(String email);
}
