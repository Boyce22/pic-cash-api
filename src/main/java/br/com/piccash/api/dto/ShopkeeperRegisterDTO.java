package br.com.piccash.api.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ShopkeeperRegisterDTO(@NotBlank String nome, @NotBlank String sobreNome, @Email @NotBlank String email,
		@CNPJ @NotBlank String cnpj) {

}
