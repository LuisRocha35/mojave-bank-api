package io.github.loois.mojave_bank_api.dto;

import io.github.loois.mojave_bank_api.model.enums.Coin;
import lombok.Data;

@Data
public class ExchangeRequestDTO {
    private Coin fromCoin;
    private Coin toCoin;
    private double amount;
}