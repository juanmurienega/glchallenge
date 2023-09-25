package com.jmurienega.demo.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpResponse {
	private UUID id;

	private String name; 
	
	private LocalDateTime  created;
	
	private LocalDateTime  lastLogin;
	
	private boolean isActive; 
	
	private String token;
	
	private List<PhoneDto> phones;
}
