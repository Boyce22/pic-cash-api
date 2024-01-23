package br.com.piccash.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record PaymentTransferDTO(@NotNull BigDecimal valor, @NotNull Long payer, @NotNull Long payee) {

}
