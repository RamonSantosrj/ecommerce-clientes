package com.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.AuthenticationRequestDTO;
import com.ecommerce.dtos.AuthenticationResponseDTO;
import com.ecommerce.models.Cliente;
import com.ecommerce.security.TokenAuthenticationService;
import com.ecommerce.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api("Authentication")
@RestController
public class AuthenticationController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	TokenAuthenticationService tokenAuthenticationService;
	
	@ApiOperation("metodo de authenticacao")
	@PostMapping("/v1/authentication")
	public ResponseEntity<AuthenticationResponseDTO> post(@Valid @RequestBody AuthenticationRequestDTO dto){
		
		AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
		HttpStatus status = null;
		
		try {
		Cliente cliente = clienteService.get(dto.getEmail(), dto.getSenha());
		authenticationResponseDTO.setMensagem("cliente obtido com sucesso");
		authenticationResponseDTO.setCliente(cliente);
		authenticationResponseDTO.setAcessToken(tokenAuthenticationService.generateToken(cliente.getEmail()));
		status = HttpStatus.OK;
			
			
		} catch (IllegalArgumentException e) {
			authenticationResponseDTO.setMensagem(e.getMessage());
			status = HttpStatus.UNAUTHORIZED;
		}
		
		
		
		return ResponseEntity.status(status).body(authenticationResponseDTO);
	}
	
	
	
	
}
