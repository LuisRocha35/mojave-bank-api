package io.github.loois.mojave_bank_api.repository;

import io.github.loois.mojave_bank_api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Como a PK agora é o 'id', o JpaRepository herda métodos como findById(Long id).
    // Mas e se precisarmos buscar pelo número da conta ("0000") que o cliente digita?
    // Usamos um Query Method do Spring! O framework cria a query automaticamente:

    Optional<Account> findByNumber(String number);
}
