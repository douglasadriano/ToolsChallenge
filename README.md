# API de Pagamentos

Este é um projeto de uma API de Pagamentos desenvolvida em Java utilizando Spring Boot. A API permite realizar operações de criação, consulta, remoção e estorno de pagamentos.

## Tecnologias Utilizadas

- Java 11+
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok
- ModelMapper
- Jackson

## Requisitos

- JDK 11+
- Maven 3.6+
- Git

## Configuração do Projeto

1. Clone o repositório:

   ```sh
   git clone https://github.com/douglasadriano/ToolsChallenge.git

2. Navegue até o diretório do projeto:

   ```sh
   cd ToolsChallenge

3. Compile e instale as dependências:

   ```sh
   mvn clean install

4. Execute o projeto:

   ```sh
   mvn spring-boot:run

## Endpoints

### Criar Pagamento
- URL: /pagamento

- Método: POST

- Request Body:
    ```sh
   {
    "transacao": {
    "cartao": "4444********1234",
    "id": "100023568900001",
    "descricao": {
      "valor": "500.50",
      "dataHora": "01/05/2021 18:30:00",
      "estabelecimento": "PetShop Mundo Cão"
    },
    "formaPagamento": {
      "tipo": "AVISTA",
      "parcelas": "1"
    }
        }
    }
  
- Response:
  ```sh
  {
  "id": 1,
  "cartao": "4444********1234",
  "descricao": {
    "id": 1,
    "valor": "500.50",
    "dataHora": "01/05/2021 18:30:00",
    "estabelecimento": "PetShop Mundo Cão",
    "nsu": 1234567890,
    "codigoAutorizacao": 147258369,
    "status": "AUTORIZADO"
  },
  "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": 1
  }
  }

### Listar Pagamentos
- URL: /pagamento
- Método: GET
- Response:
- ```sh
  [
  {
    "id": 1,
    "cartao": "4444********1234",
    "descricao": {
      "id": 1,
      "valor": "500.50",
      "dataHora": "01/05/2021 18:30:00",
      "estabelecimento": "PetShop Mundo Cão",
      "nsu": 1234567890,
      "codigoAutorizacao": 147258369,
      "status": "AUTORIZADO"
    },
    "formaPagamento": {
      "tipo": "AVISTA",
      "parcelas": 1
    }
  }
  ]

### Buscar Pagamento por ID
- URL: /pagamento/{id}
- Método: GET
- Response:
  ```sh
    {
  "id": 1,
  "cartao": "4444********1234",
  "descricao": {
    "id": 1,
    "valor": "500.50",
    "dataHora": "01/05/2021 18:30:00",
    "estabelecimento": "PetShop Mundo Cão",
    "nsu": 1234567890,
    "codigoAutorizacao": 147258369,
    "status": "AUTORIZADO"
  },
  "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": 1
  }

### Remover Pagamento
- URL: /pagamento/{id}
- Método: DELETE
- Response: 204 No Content

### Estornar Pagamento
- URL: /pagamento/{id}
- Método: PUT
- Response:
    ```sh
  {
  "id": 1,
  "cartao": "4444********1234",
  "descricao": {
    "id": 1,
    "valor": "500.50",
    "dataHora": "01/05/2021 18:30:00",
    "estabelecimento": "PetShop Mundo Cão",
    "nsu": 1234567890,
    "codigoAutorizacao": 147258369,
    "status": "CANCELADO"
  },
  "formaPagamento": {
    "tipo": "AVISTA",
    "parcelas": 1
  }
  }
  



