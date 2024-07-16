package com.jsp.CultFit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CultFit.dto.Register;
import com.jsp.CultFit.dto.UserClone;
import com.jsp.CultFit.entity.User;
import com.jsp.CultFit.service.UserService;
import com.jsp.springboot_student.util.ResponseStructure;

import jakarta.mail.MessagingException;
@RestController
public class UserController{
	@Autowired
	private UserService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Register>> saveUser(@RequestBody Register register) throws MessagingException {
		return service.saveUser(register);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<UserClone>> loginUser(@RequestParam String email,@RequestParam String pwd){
		return service.loginUser(email, pwd);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<UserClone>> fetchUser(@RequestParam int id){
		return service.fetchUser(id);
	}
	
	@PostMapping("/update")
	public ResponseEntity<ResponseStructure<UserClone>> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<ResponseStructure<UserClone>> updateUser(@RequestParam int id){
		return service.deleteUser(id);
	}
	
	@GetMapping("/otp")
	public ResponseEntity<ResponseStructure<Integer>> forgotPwd(@RequestParam String email){
		return service.forgotPwd(email);
	}
}
