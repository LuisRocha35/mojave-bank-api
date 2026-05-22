package io.github.loois.mojave_bank_api.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Data
public class AccountRequestDTO {

    @NotBlank(message = "O número da conta é obrigatório.")
    private String number;

    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotBlank(message = "A palavra-passe não pode estar vazia.")
    private String password;

    @PositiveOrZero(message = "O saldo inicial não pode ser negativo.")
    private double initialCaps;
}