package io.github.loois.mojave_bank_api.dto;

import lombok.Data;

@Data
public class AccountResponseDTO {
    private Long id;
    private String number;
    private String name;
    private boolean isAdmin;
    private double caps;
    private double dolarsNCR;
    private double denariuslegions;
    private double casino;
}
