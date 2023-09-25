package com.jmurienega.demo.exceptionhandling;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserException extends RuntimeException {
	private int codigo;
	private String detail;
}
