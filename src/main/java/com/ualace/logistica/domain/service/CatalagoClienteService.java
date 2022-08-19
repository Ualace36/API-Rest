package com.ualace.logistica.domain.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ualace.logistica.domain.exception.NegocioException;
import com.ualace.logistica.domain.model.Cliente;
import com.ualace.logistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;
//Class para defenir as regras de negócio
@AllArgsConstructor
@Service
public class CatalagoClienteService {
   
	private ClienteRepository clienteRepository;
	
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
	}
		
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
		.stream()
		.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email.");
		}
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long cliente) {
		clienteRepository.deleteById(cliente);
	}
}
