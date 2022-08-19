package com.ualace.logistica.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ualace.logistica.domain.model.ClienteRsumo;
import com.ualace.logistica.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EntregaDto {

	

	

		private Long entregaId;
		private ClienteRsumo cliente;
		private DestinatarioDto destinatario;
		private BigDecimal taxa;
		private StatusEntrega status;
		private OffsetDateTime dataPedido;
		private OffsetDateTime dataFinalizacao;
		
}
