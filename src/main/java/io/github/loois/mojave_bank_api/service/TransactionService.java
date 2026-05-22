package io.github.loois.mojave_bank_api.service;

import io.github.loois.mojave_bank_api.dto.TransactionRequestDTO;
import io.github.loois.mojave_bank_api.dto.TransferRequestDTO;
import io.github.loois.mojave_bank_api.model.Account;
import io.github.loois.mojave_bank_api.model.enums.Coin;
import io.github.loois.mojave_bank_api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;

    @Transactional
    public void deposit(String accountNumber, TransactionRequestDTO request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        // Adiciona o saldo dependendo da moeda
        addBalance(account, request.getCoin(), request.getAmount());

        // commit da transação
        accountRepository.save(account);
    }

    @Transactional // Rollback automático
    public void transfer(String sourceAccountNumber, TransferRequestDTO request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        Account source = accountRepository.findByNumber(sourceAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found."));

        Account target = accountRepository.findByNumber(request.getTargetAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Target account not found."));

        // Valida se tem saldo
        double currentBalance = getBalanceForCoin(source, request.getCoin());
        if (currentBalance < request.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in " + request.getCoin() + ".");
        }

        // subtrai da origem e adiciona no destino
        subBalance(source, request.getCoin(), request.getAmount());
        addBalance(target, request.getCoin(), request.getAmount());

        accountRepository.save(source);
        accountRepository.save(target);
    }

    // Métodos auxiliares
    private void addBalance(Account account, Coin coin, double amount) {
        switch (coin) {
            case CAPS -> account.setCaps(account.getCaps() + amount);
            case NCR -> account.setDolarsNCR(account.getDolarsNCR() + amount);
            case LEGION -> account.setDenariuslegions(account.getDenariuslegions() + amount);
            case CASINO -> account.setCasino(account.getCasino() + amount);
        }
    }

    private void subBalance(Account account, Coin coin, double amount) {
        switch (coin) {
            case CAPS -> account.setCaps(account.getCaps() - amount);
            case NCR -> account.setDolarsNCR(account.getDolarsNCR() - amount);
            case LEGION -> account.setDenariuslegions(account.getDenariuslegions() - amount);
            case CASINO -> account.setCasino(account.getCasino() - amount);
        }
    }

    private double getBalanceForCoin(Account account, Coin coin) {
        return switch (coin) {
            case CAPS -> account.getCaps();
            case NCR -> account.getDolarsNCR();
            case LEGION -> account.getDenariuslegions();
            case CASINO -> account.getCasino();
        };
    }
}