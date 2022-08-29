package com.ecommerce.dtos;

import com.ecommerce.models.Cliente;

import lombok.Data;

@Data
public class ClienteResponseDTO {
	
	private String mensagem;
	private Cliente cliente;

}
