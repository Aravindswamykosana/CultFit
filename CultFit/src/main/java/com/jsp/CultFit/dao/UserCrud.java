package com.jsp.CultFit.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CultFit.entity.User;
import com.jsp.CultFit.repo.UserRepo;

@Repository
public class UserCrud {
	@Autowired
	private UserRepo repo;
	
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	public User fetchUser(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}
		return null;
	}
	
	public User deleteUser(int id) {
		Optional<User> db = repo.findById(id);
		if(db.isPresent()){
			User u = db.get();
			repo.delete(u);
			return u;
		}
		else {
			return null;
		}
	}
	
	public User updateUser(User user) {
		Optional<User> db = repo.findById(user.getId());
		if(db.isPresent()) {
			User d = db.get();
			if(d.getF_name()!=null) {
				d.setF_name(user.getF_name());
			}
			if(d.getL_name()!=null) {
				d.setL_name(user.getL_name());
			}
			if(d.getDob()!=null) {
				d.setDob(user.getDob());
			}
			if(d.getEmail()!=null) {
				d.setEmail(user.getEmail());
			}
			if(d.getGender()!=null) {
				d.setGender(user.getGender());
			}
			if(d.getPhone()!=0) {
				d.setPhone(user.getPhone());
			}
			if(d.getPwd()!=null) {
				d.setPwd(user.getPwd());
			}
			return repo.save(d);
		}
		return null;
	}
}
