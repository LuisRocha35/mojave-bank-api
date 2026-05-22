package io.github.loois.mojave_bank_api.dto;

import lombok.Data;

@Data
public class AccountRequestDTO {
    private String number;
    private String name;
    private String password;
    private double initialCaps;
}