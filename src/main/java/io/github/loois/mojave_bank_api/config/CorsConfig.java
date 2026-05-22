package io.github.loois.mojave_bank_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Spring identifica se é uma classe de configuração global
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a permissão a TODAS as rotas (/api/accounts, /api/transactions e tals.)
                .allowedOrigins("http://localhost:3000", "http://localhost:5173", "http://127.0.0.1:5173") // Portas padrão do React e tambem do Vite
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Verbos HTTP
                .allowedHeaders("*") // Permite o envio de qualquer cabeçalho extra
                .allowCredentials(true); // Permite o envio de cookies ou credenciais de autenticação
    }
}