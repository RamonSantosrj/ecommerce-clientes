package com.ecommerce.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ClienteRequestDTO {

	@Size(min = 4, max = 150, message = "Nome do cliente deve ser de 4 a 150 caracteres")
	@NotBlank(message = "Nome do Cliente Obrigatorio")
	private String nome;
	
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Telefone deve ter 11 dígitos numéricos.")
	@NotBlank(message = "telefone Obrigatorio.")
	private String telefone;
	
	@Email(message = "Email do cliente invalido")
	@NotBlank(message = "email obrigatorio.")
	private String email;
	
	@Size(min = 8, max = 20, message = "senha deve conter no minimo 8 e no maximo 20 caracteres")
	@NotBlank(message = "senha obrigatoria")
	private String senha;

}
