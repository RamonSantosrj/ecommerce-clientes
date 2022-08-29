package com.ecommerce.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {

	@Email(message = "")
	@NotBlank(message = "campo obrigatorio.")
	private String email;
	
	@Size(min = 8, max = 20, message = "a senha deve conter no minimo 8 caracter e no maximo 20")
	@NotBlank(message = "Campo obrigatorio")
	private String senha;
	
	
}
