package com.jsp.CultFit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotFoundException extends RuntimeException{
	private String msg="User email not found in our database";
}
