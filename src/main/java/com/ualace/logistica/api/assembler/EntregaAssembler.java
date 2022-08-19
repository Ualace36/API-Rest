package com.ualace.logistica.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ualace.logistica.api.dto.EntregaDto;
import com.ualace.logistica.api.dto.input.EntregaInput;
import com.ualace.logistica.domain.model.Entrega;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Component
public class EntregaAssembler {

	private ModelMapper modelMapper;
	
	public EntregaDto toDto (Entrega entrega) {
		return modelMapper.map(entrega, EntregaDto.class);
	}
	
	public List<EntregaDto> toCollectionModel(List<Entrega> entregas){
		
		return entregas.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		
		return modelMapper.map(entregaInput, Entrega.class);
		
	}

	
}
