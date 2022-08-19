  package com.ualace.logistica.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//essa class pode ser embeded na class entrega
@Embeddable
public class Destinatario {
    @NotBlank
	@Column(name = "destinatarioNome")
	private String nome;
	@Column(name = "destinatarioLogradouro")
	@NotBlank
	private String logradouro;
	@Column(name = "destinatarioNumero")
	@NotBlank
	private String numero;
	@Column(name = "destinatarioComplemento")
	@NotBlank
	private String complemento;
	@Column(name = "destinatarioBairro")
	@NotBlank
	private String bairro;
	@Column(name = "destinatarioCidade")
	@NotBlank
	private String cidade;
}
