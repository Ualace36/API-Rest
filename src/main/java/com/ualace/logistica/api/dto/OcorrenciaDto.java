package com.ualace.logistica.api.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OcorrenciaDto {

	private Long id;
	private String descricao;
	private OffsetDateTime dataRegistro;
	
}
