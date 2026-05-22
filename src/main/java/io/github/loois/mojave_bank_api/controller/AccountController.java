package io.github.loois.mojave_bank_api.controller;

import io.github.loois.mojave_bank_api.dto.AccountRequestDTO;
import io.github.loois.mojave_bank_api.dto.AccountResponseDTO;
import io.github.loois.mojave_bank_api.dto.AccountUpdateRequestDTO;
import io.github.loois.mojave_bank_api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que é um controlador REST
@RequestMapping("/api/accounts") // Define a rota base dos endpoints
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping // Responde POST para /api/accounts
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO request) {
        AccountResponseDTO response = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping // Responde GET para /api/accounts
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts() {
        List<AccountResponseDTO> accounts = accountService.listAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{number}") // Responde GET para /api/accounts/{number}
    public ResponseEntity<AccountResponseDTO> getAccountByNumber(@PathVariable String number) {
        AccountResponseDTO response = accountService.findByNumber(number);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{number}") // Responde PUT para /api/accounts/{number}
    public ResponseEntity<AccountResponseDTO> updateAccount(
            @PathVariable String number,
            @RequestBody AccountUpdateRequestDTO request) {

        AccountResponseDTO response = accountService.updateAccount(number, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{number}") // Responde DELETE para /api/accounts/{number}
    public ResponseEntity<Void> deleteAccount(@PathVariable String number, @RequestParam String adminNumber) {
        accountService.deleteAccount(number, adminNumber);
        return ResponseEntity.noContent().build();
    }
}