package com.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.dtos.AuthenticationRequestDTO;
import com.ecommerce.dtos.ClienteRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceClientesApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	
	@Test
	public void postCliente() throws Exception{
		ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO();
		
		Faker faker = new Faker(new Locale("pt-BR"));
		
		
		clienteRequestDTO.setNome(faker.name().fullName());
		clienteRequestDTO.setEmail(faker.internet().emailAddress());
		clienteRequestDTO.setTelefone(faker.number().digits(11));
		clienteRequestDTO.setSenha(faker.internet().password(8,12));
		
		mockMvc.perform(
				post("/v1/clientes")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(clienteRequestDTO)))
				.andExpect(status()
						.isCreated());
	}
	
	
	@Test
	public void postAuthentication() throws Exception{
		ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO();
		
		Faker faker = new Faker(new Locale("pt-BR"));
		
		
		clienteRequestDTO.setNome(faker.name().fullName());
		clienteRequestDTO.setEmail(faker.internet().emailAddress());
		clienteRequestDTO.setTelefone(faker.number().digits(11));
		clienteRequestDTO.setSenha(faker.internet().password(8,12));
		
		
		mockMvc.perform(
				post("/v1/clientes")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(clienteRequestDTO)))
				.andExpect(status()
						.isCreated());
		
		
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();
		authenticationRequestDTO.setEmail(clienteRequestDTO.getEmail());
		authenticationRequestDTO.setSenha(clienteRequestDTO.getSenha());
		
		mockMvc.perform(
				post("/v1/authentication")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(authenticationRequestDTO)))
				.andExpect(status()
						.isOk());
		
	}
	
	@Test
	public void postAccessDenied() throws Exception {
				
		AuthenticationRequestDTO authDto = new AuthenticationRequestDTO();
		Faker faker = new Faker(new Locale("pt-BR"));
		
		authDto.setEmail(faker.internet().emailAddress());
		authDto.setSenha(faker.internet().password(8,12));
		
		mockMvc.perform(
				post("/v1/authentication")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(authDto)))
				.andExpect(status()
						.isUnauthorized());		
	}
	
	
	
	
	
	
}
