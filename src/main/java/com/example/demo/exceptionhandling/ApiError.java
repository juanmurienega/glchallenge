package com.example.demo.exceptionhandling;


import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {


	public ApiError(int codigo, String detail) {
		this.error.add(new Error(codigo,detail));
	}

	public ApiError(CustomUserException ex) {
		this.error.add(new Error(ex.getCodigo(),ex.getDetail()));
	}

	private List<Error> error=new ArrayList<Error>();
}