package br.com.piccash.api.dto;

import java.math.BigDecimal;

import br.com.piccash.api.entity.User;

public record ShopkeeperDetailsDTO(Long id, String nome, String sobreNome, String email, String cnpj,
		BigDecimal saldo) {

	public ShopkeeperDetailsDTO(User usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getSobreNome(), usuario.getEmail(), usuario.getCnpj(), usuario.getSaldo());
	}
}
