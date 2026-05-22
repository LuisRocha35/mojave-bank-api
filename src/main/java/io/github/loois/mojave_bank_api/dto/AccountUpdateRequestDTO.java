package io.github.loois.mojave_bank_api.dto;

import lombok.Data;

@Data
public class AccountUpdateRequestDTO {
    // Só campo que ta tranquilo editar
    private String name;
    private String password;
}