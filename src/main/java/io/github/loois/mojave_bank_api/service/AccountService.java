package io.github.loois.mojave_bank_api.service;

import io.github.loois.mojave_bank_api.dto.AccountRequestDTO;
import io.github.loois.mojave_bank_api.dto.AccountResponseDTO;
import io.github.loois.mojave_bank_api.dto.AccountUpdateRequestDTO;
import io.github.loois.mojave_bank_api.model.Account;
import io.github.loois.mojave_bank_api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountResponseDTO createAccount(AccountRequestDTO dto) {
        if (accountRepository.findByNumber(dto.getNumber()).isPresent()) {
            throw new IllegalArgumentException("Account number already exists!!");
        }
        if (dto.getInitialCaps() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        // Convertendo DTO -> Entidade
        Account account = new Account();
        account.setNumber(dto.getNumber());
        account.setName(dto.getName());
        account.setPassword(dto.getPassword());
        account.setAdmin(false);
        account.setCaps(dto.getInitialCaps());

        Account savedAccount = accountRepository.save(account);

        // Retornando o DTO de resposta
        return convertToResponseDTO(savedAccount);
    }

    public AccountResponseDTO findByNumber(String number) {
        Account account = accountRepository.findByNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("Account " + number + " not found."));
        return convertToResponseDTO(account);
    }

    public List<AccountResponseDTO> listAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public AccountResponseDTO updateAccount(String number, AccountUpdateRequestDTO dto) {
        // 1. busca uma conta existente
        Account account = accountRepository.findByNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("Account " + number + " not found."));

        // 2. Atualiza aqueles dados seguros (atributos no dto de update)
        account.setName(dto.getName());
        account.setPassword(dto.getPassword());

        Account updatedAccount = accountRepository.save(account);

        // 4. DTO seguro
        return convertToResponseDTO(updatedAccount);
    }

    public void deleteAccount(String number, String adminNumber) {
        if (number.equals(adminNumber)) {
            throw new IllegalArgumentException("Cannot delete the admin account.");
        }
        Account account = accountRepository.findByNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));
        accountRepository.delete(account);
    }

    // Auxiliar para mapear Entidade -> DTO
    private AccountResponseDTO convertToResponseDTO(Account account) {
        AccountResponseDTO response = new AccountResponseDTO();
        response.setId(account.getId());
        response.setNumber(account.getNumber());
        response.setName(account.getName());
        response.setAdmin(account.isAdmin());
        response.setCaps(account.getCaps());
        response.setDolarsNCR(account.getDolarsNCR());
        response.setDenariuslegions(account.getDenariuslegions());
        response.setCasino(account.getCasino());
        return response;
    }
}