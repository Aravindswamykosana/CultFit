package com.jsp.CultFit.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.springboot_student.exception.EmailWrongException;
import com.jsp.springboot_student.util.ResponseStructure;
@RestControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>>SQLIntegrityConstraintViolationException (SQLIntegrityConstraintViolationException ex){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData("please provide valid email");
		rs.setMessage("student email is already exists");
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(PwdWrongException.class)
	public ResponseEntity<ResponseStructure<String>> PwdWrong(PwdWrongException p){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(p.getMsg());
		rs.setMessage("User login failed pwd wrong.....!");
		rs.setStatus(HttpStatus.UNAUTHORIZED.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.UNAUTHORIZED);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idWrong(IdNotFoundException p){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(p.getMsg());
		rs.setMessage("User id has not found.....!");
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> EmailNotFoundException(EmailNotFoundException p){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setData(p.getMsg());
		rs.setMessage("User email has not found.....!");
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setDt(LocalDateTime.now());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
}
