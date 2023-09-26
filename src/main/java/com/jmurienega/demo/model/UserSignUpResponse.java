package com.jmurienega.demo.model;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	private LocalDateTime  created;
	private LocalDateTime  lastLogin;
	private String token;
	@JsonProperty("isActive")
	private boolean isActive; 
}
