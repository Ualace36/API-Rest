package com.ualace.logistica.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ualace.logistica.api.dto.OcorrenciaDto;
import com.ualace.logistica.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Component

public class OcorrenciaAssembler {

	private ModelMapper modelMapper;
	
	
	
	public OcorrenciaDto toModel(Ocorrencia ocorrencia) {
		
		return modelMapper.map(ocorrencia, OcorrenciaDto.class);
	}
	

    public List<OcorrenciaDto> toCollectionModel (List<Ocorrencia> ocorrencias){
    	return ocorrencias.stream()
    			.map(this::toModel)
    			.collect(Collectors.toList());
    }

}

