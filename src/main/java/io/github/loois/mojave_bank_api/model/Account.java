package io.github.loois.mojave_bank_api.model;

import io.github.loois.mojave_bank_api.model.enums.Coin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Data // gera os getters, setters, toString e tals automaticamente.
@NoArgsConstructor // criar um constructor vazio (do lombok)
@AllArgsConstructor // criar um constructtor cheia tbm do lombok
@Entity // do jpa para essa classe representar uma tabela no db
@Table(name="tb_accounts_mojave") // jpa mantem esse nome na table do db

public class Account {
    @Id // Definir o proximo atributo (number) como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    private double caps;

    @Column(name = "dolarsNCR")
    private double dolarsNCR;

    private double denariuslegions;
    private double casino;
}