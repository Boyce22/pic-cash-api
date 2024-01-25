package br.com.piccash.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record DepositDTO(@NotNull Long id, @NotNull BigDecimal value) {

}
