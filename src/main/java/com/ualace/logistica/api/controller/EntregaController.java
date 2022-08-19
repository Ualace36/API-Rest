package com.ualace.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ualace.logistica.api.assembler.EntregaAssembler;
import com.ualace.logistica.api.dto.EntregaDto;
import com.ualace.logistica.api.dto.input.EntregaInput;
import com.ualace.logistica.domain.model.Entrega;
import com.ualace.logistica.domain.repository.EntregaRepository;
import com.ualace.logistica.domain.service.FinalizacaoEntregaService;
import com.ualace.logistica.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping ("/entregas")
public class EntregaController {
    
	private EntregaRepository entregaRepository;
	
	private EntregaAssembler entregaAssembler;
	
    private FinalizacaoEntregaService finalizacaoEntregaService;
	
	private SolicitacaoEntregaService solicitacaoEntregaService;
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDto solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		
				Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
				Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		        return  entregaAssembler.toDto(entregaSolicitada);
	}
	
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		
		finalizacaoEntregaService.finalizar(entregaId);
		
	}
	
	@GetMapping
	public List<EntregaDto> listar(){
		return  entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDto> buscar(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(entrega ->  ResponseEntity.ok (entregaAssembler.toDto(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
}
