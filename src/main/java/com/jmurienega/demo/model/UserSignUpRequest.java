package com.jmurienega.demo.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class UserSignUpRequest {
	@NotEmpty(message = "El nombre es requerido.")
	private String name;
	@NotEmpty(message = "El email es requerido.")
	@Email(message = "El email es invalido.", flags = { Flag.CASE_INSENSITIVE })
	private String email;
	@NotEmpty(message = "El password es requerido.")
	@Size(min = 8, max = 12, message = "El password debe tener un minimo de 8 caracteres y un maximo de 12 caracteres.")	
	@Pattern(regexp = "^[a-z0-9]*[A-Z][a-z0-9]*$", message = "El password debe tener una Mayúscula")
	@Pattern(regexp = "^[a-zA-Z]*\\d[a-zA-Z]*\\d[a-zA-Z]*$", message = "El password debe tener solo dos numeros")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "El password debe tener solo caracteres alfanumericos")
	
	private String password;
	private List<PhoneDto> phones;

}
