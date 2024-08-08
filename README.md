# API de Pagamentos

Este é um projeto de uma API de Pagamentos desenvolvida em Java utilizando Spring Boot. A API permite realizar operações de criação, consulta, remoção e estorno de pagamentos.

---
## Tecnologias Utilizadas

- Java 11+
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Test
- H2 Database
- Lombok
- ModelMapper
- Jackson
- JUnit 5
- Mockito
- Postman (para testes e documentação da API)
---
## Requisitos

- JDK 11+
- Maven 3.6+
- Git
- Postman (opcional, mas recomendado para testes de API)
---
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
---
## Endpoints

### Criar Pagamento
- URL: http://localhost:8080/transacao

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
- URL: http://localhost:8080/transacao
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
- URL: http://localhost:8080/transacao/{id}
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


### Estornar Pagamento
- URL: http://localhost:8080/transacao/estornar/{id}
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

### Remover Pagamento
- URL: http://localhost:8080/transacao/{id}
- Método: DELETE
- Response: 204 No Content
---
## Testes com Postman
Para facilitar o uso e teste da API, recomendo utilizar o Postman. Aqui está como você pode configurar o Postman para trabalhar com esta API:

1. Abra o Postman.
2. Crie uma nova coleção (Collection) para agrupar suas requisições.
3. Adicione as requisições para cada endpoint listado acima, configurando o método HTTP e o corpo da requisição (quando aplicável).
4. Teste os endpoints para garantir que a API está funcionando conforme esperado.
