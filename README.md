
# URL Shortener API
*Esse projeto é um [desafio da backend-br](https://github.com/backend-br/desafios/blob/master/url-shortener/PROBLEM.md), e ainda está em desenvolvimento.*

API simples construída com Spring Boot que permite criar URLs curtas e redirecionar para a URL original a partir da URL curta.

## Funcionalidades

-   **Encurtamento de URLs:** Envia uma URL longa para a API e recebe uma URL curta como resposta.
-   **Redirecionamento:** Redireciona automaticamente qualquer URL curta gerada para a URL original.

## Endpoints

### 1. Encurtar uma URL

-   **Método:** `POST`
    
-   **Endpoint:** `/newurl`
    
-   **Descrição:** Recebe uma URL e retorna uma versão encurtada da URL.
    
-   **Exemplo de Request:**
    
    `{
      "url": "https://www.exemplo.com/artigo-interessante"
    }` 
    
-   **Exemplo de Response:**
    `{
      "shortUrl": "http://localhost:8080/r/abc123"
    }` 
    

### 2. Redirecionar para a URL Original

-   **Método:** `GET`
-   **Endpoint:** `/r/{shortUrl}`
-   **Descrição:** Redireciona a URL curta fornecida para a URL original associada.
-   **Exemplo de Uso:** Acessar `http://localhost:8080/r/abc123` redirecionará para `https://www.exemplo.com/artigo-interessante`.

