# Food Facts API
>  This is a challenge by [Coodesh](https://coodesh.com/)
## Descrição

Este projeto é uma REST API desenvolvida para integrar dados do Open Food Facts, um banco de dados aberto de informações nutricionais.

## Tecnologias Utilizadas

- **Linguagem**: Java
- **Framework**: Spring Boot
- **Banco de Dados**: MongoDB
- **Testes**: JUnit, Mockito

## Funcionalidades

- **CRUD de produtos**: A API permite criar, ler, atualizar e excluir produtos da base de dados, com integração ao Open Food Facts.
- **Importação via CRON**: Um sistema de CRON diário importa novos dados do Open Food Facts e atualiza a base de dados. Configurações como horário do Cron e número máximo de imports podem ser configuradas em application.properties.
- **Paginação**: O endpoint de listagem de produtos suporta paginação para otimizar o desempenho.

## Endpoints

- **GET /**: Retorna o status da API, incluindo o estado da conexão com o banco de dados, o horário da última execução do CRON, tempo de execução da aplicação e uso de memória.
- **PUT /products/:code**: Atualiza os dados de um produto específico.
- **DELETE /products/:code**: Marca um produto como "trash" (descartado).
- **GET /products/:code**: Obtém as informações de um produto específico.
- **GET /products**: Lista todos os produtos com paginação.



## Instalação e Uso

1. Clone o repositório:
    ```bash
    git clone https://github.com/almeidagianluca/food-facts-api.git
    ```

2. Instale as dependências do projeto:
    ```bash
    mvn clean install
    ```

3. Configure as variáveis de ambiente necessárias, como as credenciais do MongoDB Atlas no arquivo env.properties.


4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

5. A API estará disponível em `http://localhost:8080`.

## Testes

Para rodar os testes unitários, utilize o comando:
```bash
mvn test
```


