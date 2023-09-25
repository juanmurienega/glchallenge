package com.example.demo.exceptionhandling;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Error {
	public Error(int codigo, String detail) {
		this.codigo = codigo;
		this.detail = detail;
		this.timestamp = LocalDateTime.now();
	}
	private LocalDateTime timestamp;
    private int codigo;
    private String detail;

}
