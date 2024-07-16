package com.jsp.CultFit.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClone {
	private int id;
	private String f_name;
	private String l_name;
	private String email;
	private String gender;
	private String dob;
	private long phone;
}
