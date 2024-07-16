package com.jsp.CultFit.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsp.CultFit.dao.UserCrud;
import com.jsp.CultFit.dto.Register;
import com.jsp.CultFit.dto.UserClone;
import com.jsp.CultFit.entity.User;
import com.jsp.CultFit.exception.EmailNotFoundException;
import com.jsp.CultFit.exception.IdNotFoundException;
import com.jsp.CultFit.exception.PwdWrongException;
import com.jsp.CultFit.repo.UserRepo;
import com.jsp.springboot_student.util.ResponseStructure;
import com.mysql.cj.protocol.x.Ok;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class UserService {
	@Autowired
	private JavaMailSender emailsender;
	@Autowired
	private UserCrud crud;
	@Autowired
	private	ModelMapper mapper;
	@Autowired
	private UserRepo repo;
	
	public void sendMsg(String email) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("aarvindkosana@gmail.com");
		message.setTo(email);
		message.setSubject("otp success");
		message.setText("dont share otp to anyone");
		emailsender.send(message);
	}
	public int sendOTP(String email) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("aarvindkosana@gmail.com");
		message.setTo(email);
		message.setSubject("you have recieved an otp");
		Random random=new Random();
		int otp=random.nextInt(100000);
		message.setText("your otp for login as:"+otp+"\nplease dont share otp for anyone");
		emailsender.send(message);
		return otp;
	}
	
	public void sendAttachment(String email) throws MessagingException{
		MimeMessage message=emailsender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom("aarvindkosana@gmail.com");
		helper.setTo(email);
		helper.setSubject("Revitalize Your Workout Routine: New Gym Offer Inside!");
		helper.setText("Subject: Elevate Your Fitness Journey with Our Exclusive Gym Content!\r\n"
				+ "\r\n"
				+ "Dear [User],\r\n"
				+ "\r\n"
				+ "Are you ready to take your fitness journey to the next level? Look no further! We're excited to introduce our exclusive gym content designed to help you achieve your fitness goals effectively and efficiently.\r\n"
				+ "\r\n"
				+ "Whether you're a beginner looking to kickstart your fitness journey or a seasoned gym-goer seeking fresh inspiration, our content has something for everyone. From expert workout routines tailored to target specific muscle groups to nutrition tips that fuel your body for optimal performance, we've got you covered.\r\n"
				+ "\r\n"
				+ "Here's a sneak peek at what you can expect from our gym content:\r\n"
				+ "\r\n"
				+ "1. **Workout Plans:** Access comprehensive workout plans curated by fitness experts to help you build strength, improve endurance, and sculpt your dream physique.\r\n"
				+ "2. **Exercise Demonstrations:** Learn proper form and technique with step-by-step exercise demonstrations for a variety of movements, ensuring you get the most out of every rep.\r\n"
				+ "3. **Nutrition Guidance:** Unlock the secrets to fueling your body for success with practical nutrition tips, meal plans, and healthy recipe ideas to support your fitness goals.\r\n"
				+ "4. **Motivational Content:** Stay inspired and motivated on your fitness journey with uplifting stories, success tips, and expert advice from seasoned athletes and trainers.\r\n"
				+ "5. **Exclusive Offers:** Enjoy exclusive discounts, promotions, and special offers on gym memberships, fitness gear, and supplements to enhance your training experience.\r\n"
				+ "\r\n"
				+ "Ready to elevate your fitness game? Sign up now to gain access to our premium gym content and start crushing your goals today!\r\n"
				+ "\r\n"
				+ "To your health and fitness success,\r\n"
				+ "\r\n"
				+ "[Your Gym Name:CultFit]\r\n"
				+ "\r\n");
		FileSystemResource file=new FileSystemResource(new File("C:\\Users\\ARAVIND\\Desktop\\web-tech\\css\\meesho\\assets\\gym.jpg"));
		helper.addAttachment(file.getFilename(), file);
		emailsender.send(message);
	}
	public ResponseEntity<ResponseStructure<Register>> saveUser(Register reg) throws MessagingException{
		User u = mapper.map(reg, User.class);
		User db = crud.saveUser(u);
		ResponseStructure<Register> rs=new ResponseStructure<Register>();
		rs.setData(reg);
		rs.setMessage("User data saved succesfully");
		rs.setStatus(HttpStatus.CREATED.value());
		rs.setDt(LocalDateTime.now());
		sendAttachment(reg.getEmail());
		return new ResponseEntity<ResponseStructure<Register>>(rs,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<UserClone>> loginUser(String email,String pwd){
		 User obj = repo.findByEmail(email);
		if(obj!=null) {
			if(obj.getPwd().equals(pwd)) {
				UserClone db = mapper.map(obj,UserClone.class);
				ResponseStructure<UserClone> rs=new ResponseStructure<UserClone>();
				rs.setData(db);
				rs.setMessage("User Login succesfully");
				rs.setStatus(HttpStatus.OK.value());
				rs.setDt(LocalDateTime.now());
				return new ResponseEntity<ResponseStructure<UserClone>>(rs,HttpStatus.OK);
			}
			else {
				throw new PwdWrongException();
			}
		}
		else {
			throw new EmailNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<UserClone>> fetchUser(int id){
		User db = crud.fetchUser(id);
		if(db!=null) {
			UserClone d = mapper.map(db,UserClone.class);
			ResponseStructure<UserClone> rs=new ResponseStructure<UserClone>();
			rs.setData(d);
			rs.setMessage("User Details fetched succesfully");
			rs.setStatus(HttpStatus.FOUND.value());
			rs.setDt(LocalDateTime.now());
			return new ResponseEntity<ResponseStructure<UserClone>>(rs,HttpStatus.FOUND);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<UserClone>> deleteUser(int id){
		User db = crud.deleteUser(id);
		if(db!=null) {
			UserClone d = mapper.map(db,UserClone.class);
			ResponseStructure<UserClone> rs=new ResponseStructure<UserClone>();
			rs.setData(d);
			rs.setMessage("User Details fetched succesfully");
			rs.setStatus(HttpStatus.GONE.value());
			rs.setDt(LocalDateTime.now());
			return new ResponseEntity<ResponseStructure<UserClone>>(rs,HttpStatus.GONE);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<UserClone>> updateUser(User user){
		User db = crud.updateUser(user);
		if(db!=null) {
			UserClone d = mapper.map(db, UserClone.class);
			ResponseStructure<UserClone> rs=new ResponseStructure<UserClone>();
			rs.setData(d);
			rs.setDt(LocalDateTime.now());
			rs.setStatus(HttpStatus.OK.value());
			rs.setMessage("User details updated successfuly");
			return new ResponseEntity<ResponseStructure<UserClone>>(rs,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Integer>> forgotPwd(String email){
			User db = repo.findByEmail(email);
			if(db!=null) {
				MimeMessage message=emailsender.createMimeMessage();
				int otp = sendOTP(email);
				ResponseStructure<Integer> rs=new ResponseStructure<Integer>();
				rs.setData(otp);
				rs.setDt(LocalDateTime.now());
				rs.setStatus(HttpStatus.CONTINUE.value());
				rs.setMessage("User otp send successfuly");
				return new ResponseEntity<ResponseStructure<Integer>>(rs,HttpStatus.CONTINUE);
			}
			else {
				throw new EmailNotFoundException("user email: "+email+ " doesnot exists to send otp");
			}
	}
}
