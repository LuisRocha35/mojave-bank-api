# Mojave Bank API 🏦🌵

API RESTful desenvolvida em Spring Boot (Java 21) para o sistema bancário de Mojave. Integra-se com um banco de dados MariaDB executado em um container Docker e utiliza o Hibernate/JPA para mapeamento objeto-relacional.

## 🛠️ Como Executar

1. **Subir o Banco de Dados (MariaDB):**
   No terminal, na raiz do projeto, execute o comando para subir o container:
   \`\`\`bash
   docker-compose up -d
   \`\`\`
2. **Iniciar a API:**
   No IntelliJ, execute a classe principal \`MojaveBankApiApplication\`. A API ficará disponível em \`http://localhost:8080\`.

---

## Guia de Testes no Postman

URL Base para todas as requisições: \`http://localhost:8080\`

### 1. Gerenciamento de Contas (Accounts)

#### Criar uma Conta (POST)
* **URL:** \`/api/accounts\`
* **Método:** \`POST\`
* **Body (JSON):**
  \`\`\`json
  {
  "number": "0001",
  "name": "Courier Six",
  "password": "platinumchip",
  "initialCaps": 500.0
  }
  \`\`\`

#### Listar Todas as Contas (GET)
* **URL:** \`/api/accounts\`
* **Método:** \`GET\`
* **Body:** *Nenhum*

#### Buscar Conta Específica (GET)
* **URL:** \`/api/accounts/0001\` *(Substitua "0001" pelo número da conta)*
* **Método:** \`GET\`
* **Body:** *Nenhum*

#### Atualizar Perfil da Conta (PUT)
* **URL:** \`/api/accounts/0001\`
* **Método:** \`PUT\`
* **Body (JSON):**
  \`\`\`json
  {
  "name": "Senhor House",
  "password": "newpassword123"
  }
  \`\`\`

#### Deletar Conta (DELETE)
* **URL:** \`/api/accounts/0001?adminNumber=0000\` *(Exige a passagem da conta admin via Query Parameter)*
* **Método:** \`DELETE\`
* **Body:** *Nenhum*

---

### 2. Transações (Transactions)

*Nota: As moedas aceitas são \`CAPS\`, \`NCR\`, \`LEGION\` e \`CASINO\`.*

#### Fazer um Depósito (POST)
* **URL:** \`/api/transactions/0001/deposit\` *(Onde "0001" é a conta que receberá o depósito)*
* **Método:** \`POST\`
* **Body (JSON):**
  \`\`\`json
  {
  "coin": "CAPS",
  "amount": 250.0
  }
  \`\`\`

#### Fazer uma Transferência (POST)
* **URL:** \`/api/transactions/0001/transfer\` *(Onde "0001" é a conta de ORIGEM que vai enviar o dinheiro)*
* **Método:** \`POST\`
* **Body (JSON):**
  \`\`\`json
  {
  "targetAccountNumber": "0002",
  "coin": "NCR",
  "amount": 100.0
  }
  \`\`\`

---

### 3. Casa de Câmbio (Exchange)

#### Converter Moedas (POST)
* **URL:** \`/api/exchange/0001\` *(Onde "0001" é a conta que fará o câmbio)*
* **Método:** \`POST\`
* **Body (JSON):**
  \`\`\`json
  {
  "fromCoin": "CAPS",
  "toCoin": "NCR",
  "amount": 50.0
  }
  \`\`\`