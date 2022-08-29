package com.ecommerce.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.ClienteRequestDTO;
import com.ecommerce.dtos.ClienteResponseDTO;
import com.ecommerce.dtos.EmailMessageDTO;
import com.ecommerce.helpers.ClienteEmailHelper;
import com.ecommerce.models.Cliente;
import com.ecommerce.producer.EmailMessageProducer;
import com.ecommerce.services.ClienteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Api(tags = "Clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EmailMessageProducer emailMessageProducer;

	@Autowired
	private ObjectMapper objectMapper;

	@ApiOperation("Cadastro de clientes")
	@PostMapping("/v1/clientes")
	public ResponseEntity<ClienteResponseDTO> post(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {

		ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
		HttpStatus status = null;

		try {
			ModelMapper modelMapper = new ModelMapper();

			Cliente cliente = clienteService.save(modelMapper.map(clienteRequestDTO, Cliente.class));
			clienteResponseDTO.setMensagem("cliente cadastrado com sucesso.");
			clienteResponseDTO.setCliente(cliente);
			status = HttpStatus.CREATED;

			EmailMessageDTO emailMessageDTO = ClienteEmailHelper.gerarMensagemDeCriacaoDeConta(cliente);
			String mensagem = objectMapper.writeValueAsString(emailMessageDTO);
			emailMessageProducer.send(mensagem);

		} catch (IllegalArgumentException e) {
			clienteResponseDTO.setMensagem(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return ResponseEntity.status(status).body(clienteResponseDTO);
	}

}
