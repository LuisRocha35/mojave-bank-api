package io.github.loois.mojave_bank_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice // Spring vai usar esta classe para intercetar todos os erros dos Controllers
public class GlobalExceptionHandler {

    // Sempre que um IllegalArgumentException for lançado em QUALQUER lugar do código, cai aqui!
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {

        // Criamos um corpo de resposta JSON amigável e bonito
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());

        // Status 400 em vez do 500
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}