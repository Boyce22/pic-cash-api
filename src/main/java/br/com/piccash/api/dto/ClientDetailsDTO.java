package br.com.piccash.api.dto;

import java.math.BigDecimal;

import br.com.piccash.api.entity.User;

public record ClientDetailsDTO(Long id, String nome, String sobreNome, String email, String cpf, BigDecimal saldo) {

	public ClientDetailsDTO(User usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getSobreNome(), usuario.getEmail(), usuario.getCpf(), usuario.getSaldo());
	}
	
}
