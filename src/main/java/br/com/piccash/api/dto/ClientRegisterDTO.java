package br.com.piccash.api.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRegisterDTO(@NotBlank String nome, @NotBlank String sobreNome, @Email @NotBlank String email,
		@CPF @NotBlank String cpf) {
}
