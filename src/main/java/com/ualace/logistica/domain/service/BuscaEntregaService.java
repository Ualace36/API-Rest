package com.ualace.logistica.domain.service;

import org.springframework.stereotype.Service;

import com.ualace.logistica.domain.exception.EntidadeNaoEncontradaException;
import com.ualace.logistica.domain.exception.NegocioException;
import com.ualace.logistica.domain.model.Entrega;
import com.ualace.logistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		
			return  entregaRepository.findById(entregaId)
					 .orElseThrow(()-> new EntidadeNaoEncontradaException("Entrega não encontrada"));
		      
		
	}
	
}
