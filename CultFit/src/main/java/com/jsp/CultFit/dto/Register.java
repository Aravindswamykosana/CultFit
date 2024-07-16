package com.jsp.CultFit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {
	private String f_name;
	private String l_name;
	private String email;
	private String pwd;
	private long phone;
}
