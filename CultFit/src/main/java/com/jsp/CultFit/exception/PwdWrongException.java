package com.jsp.CultFit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PwdWrongException extends RuntimeException{
	private String msg="User password has been wrong";
}