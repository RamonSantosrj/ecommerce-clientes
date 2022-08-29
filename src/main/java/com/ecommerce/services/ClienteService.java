package com.ecommerce.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dtos.AuthenticationResponseDTO;
import com.ecommerce.helpers.MD5Helper;
import com.ecommerce.models.Cliente;
import com.ecommerce.repositories.IClienteRepository;

@Service
public class ClienteService {

	@Autowired
	IClienteRepository clienteRepository;

	public Cliente save(Cliente cliente) {

		if (clienteRepository.findByEmail(cliente.getEmail()).isPresent())
			throw new IllegalArgumentException("Email ja cadastrado, tente outro");

		if (clienteRepository.findByTelefone(cliente.getTelefone()).isPresent())
			throw new IllegalArgumentException("Telefone ja cadastrado, tente outro");

		cliente.setSenha(MD5Helper.getHashMd5(cliente.getSenha()));
		cliente.setCadastradoEm(Instant.now());
		cliente.setAtualizadoEm(Instant.now());
		clienteRepository.save(cliente);

		return cliente;
	}
	
	
	public Cliente get(String email,String Senha){
		
		Optional<Cliente> optional = clienteRepository.findByEmailAndSenha(email, MD5Helper.getHashMd5(Senha));
		if(optional.isEmpty()) 
			throw new IllegalArgumentException("dados Invalidos");
		
		Cliente cliente = optional.get();
		
		
		return cliente;
		
	}
	
	
	
}
