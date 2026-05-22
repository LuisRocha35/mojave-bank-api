package io.github.loois.mojave_bank_api.controller;

import io.github.loois.mojave_bank_api.dto.TransactionRequestDTO;
import io.github.loois.mojave_bank_api.dto.TransferRequestDTO;
import io.github.loois.mojave_bank_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions") // Nova rota
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<Void> deposit(
            @PathVariable String accountNumber,
            @RequestBody TransactionRequestDTO request) {
        transactionService.deposit(accountNumber, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{sourceNumber}/transfer")
    public ResponseEntity<Void> transfer(
            @PathVariable String sourceNumber,
            @RequestBody TransferRequestDTO request) {

        transactionService.transfer(sourceNumber, request);
        return ResponseEntity.ok().build();
    }
}