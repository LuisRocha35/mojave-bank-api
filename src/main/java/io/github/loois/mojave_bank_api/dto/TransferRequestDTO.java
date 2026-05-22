package io.github.loois.mojave_bank_api.dto;

import io.github.loois.mojave_bank_api.model.enums.Coin;
import lombok.Data;

// implementando "ACID" no banco dados para não perder dados se tiver operação interrompida
@Data
public class TransferRequestDTO {
    private String targetAccountNumber;
    private Coin coin;
    private double amount;
}