package br.com.piccash.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record TransactionDTO(@NotBlank Long payerID, @NotBlank Long payeeID, @NotBlank BigDecimal value) {

}
