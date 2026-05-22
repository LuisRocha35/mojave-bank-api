package io.github.loois.mojave_bank_api.controller;

import io.github.loois.mojave_bank_api.dto.ExchangeRequestDTO;
import io.github.loois.mojave_bank_api.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @PostMapping("/{accountNumber}")
    public ResponseEntity<Void> exchange(
            @PathVariable String accountNumber,
            @RequestBody ExchangeRequestDTO request) {

        exchangeService.exchange(accountNumber, request);
        return ResponseEntity.ok().build();
    }
}