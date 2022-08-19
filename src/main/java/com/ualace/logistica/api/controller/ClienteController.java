package com.ualace.logistica.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ualace.logistica.domain.model.Cliente;
import com.ualace.logistica.domain.repository.ClienteRepository;
import com.ualace.logistica.domain.service.CatalagoClienteService;

import lombok.AllArgsConstructor;

@RequestMapping("/clientes")
@AllArgsConstructor
@RestController
public class ClienteController {
	
	
	private ClienteRepository clienteRepository;
	private CatalagoClienteService catalogoClienteService;
	//buscar lista de clientes
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
        
	}
	//buscar cliente por id
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		// caso o id exista no database será retornado um body correspondente ao id e um status 200
		if (cliente.isPresent()) {
				return ResponseEntity.ok(cliente.get());
	
		}
		//caso o usuário passe um id inexistente no database será retornado um status 404
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,@Valid @RequestBody Cliente cliente){
	     if (!clienteRepository.existsById(clienteId)) {
	    	 return ResponseEntity.notFound().build();
	     }
	        //a row 60 força a atualização dos dados ao invés de criar 
	        cliente.setId(clienteId);
	        cliente = catalogoClienteService.salvar(cliente);
	        return ResponseEntity.ok(cliente);
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId ){
		if (!clienteRepository.existsById(clienteId)){
		return ResponseEntity.notFound().build();
	}
	
    catalogoClienteService.excluir(clienteId);
    return ResponseEntity.noContent().build();
	
	}
	
}
	
