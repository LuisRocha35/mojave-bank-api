package io.github.loois.mojave_bank_api.service;

import io.github.loois.mojave_bank_api.dto.ExchangeRequestDTO;
import io.github.loois.mojave_bank_api.model.Account;
import io.github.loois.mojave_bank_api.model.enums.Coin;
import io.github.loois.mojave_bank_api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final AccountRepository accountRepository;

    @Transactional
    public void exchange(String accountNumber, ExchangeRequestDTO request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (request.getFromCoin() == request.getToCoin()) {
            throw new IllegalArgumentException("Same coin, no exchange needed.");
        }

        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        double currentBalance = getBalanceForCoin(account, request.getFromCoin());
        if (currentBalance < request.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in " + request.getFromCoin() + ".");
        }

        // Tira de uma moeda e coloca na outra
        subBalance(account, request.getFromCoin(), request.getAmount());
        addBalance(account, request.getToCoin(), request.getAmount());

        accountRepository.save(account);
    }

    // Recriamos as regras aqui. (por conta que é um projeto pequeno)
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