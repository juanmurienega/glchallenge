package com.jmurienega.demo.model;

import java.time.LocalDateTime;
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
public class UserLoginResponse {
	private UUID id;
	private String name;
	private LocalDateTime created;
	private LocalDateTime lastLogin;
	private String email;
	private String password;
	private boolean isActive;
	private String token;
	private List<PhoneDto> phones;

}
