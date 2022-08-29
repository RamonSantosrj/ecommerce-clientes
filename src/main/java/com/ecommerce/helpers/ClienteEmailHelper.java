package com.ecommerce.helpers;

import com.ecommerce.dtos.EmailMessageDTO;
import com.ecommerce.models.Cliente;

public class ClienteEmailHelper {

	public static EmailMessageDTO gerarMensagemDeCriacaoDeConta(Cliente cliente) {

		EmailMessageDTO dto = new EmailMessageDTO();
		dto.setTo(cliente.getEmail());
		dto.setSubject("Parabens, sua conta De cliente foi criada com sucesso!");
		dto.setBody("Ola, " + cliente.getNome()
				+ "\n\nSua conta de cliente foi cadastrada com sucesso no ecommerce COTI." 
				+ "\nSeus dados Sao:"
				+ "\nNome: " + cliente.getNome() 
				+ "\nEmail: " + cliente.getEmail()
				+ "\nTelefone: " + cliente.getTelefone()
				+ "\n\nAtt"
				+ "\nEquipee COTI Informaticaa");

		return dto;
	}

}
