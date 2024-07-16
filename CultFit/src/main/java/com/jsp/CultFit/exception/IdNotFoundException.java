package com.jsp.CultFit.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdNotFoundException extends RuntimeException{
	private String msg="User Id not found in our database";
}
