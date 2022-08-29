package com.ecommerce.dtos;

import com.ecommerce.models.Cliente;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {

	private String mensagem;
	private String AcessToken;
	private Cliente cliente;
	
	
}
