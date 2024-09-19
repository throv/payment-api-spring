## Como acessar a Documentação da API com Springdoc-OpenAPI

Este projeto utiliza o **Springdoc-OpenAPI** para gerar e expor a documentação da API.

### Passos para Acessar a Documentação da API

1. **Executar a Aplicação**

   Certifique-se de que a aplicação Spring Boot esteja em execução. Se estiver utilizando Maven ou Gradle, você pode usar os seguintes comandos para iniciar a aplicação:

   Para **Maven**:

   ```bash
   mvn spring-boot:run
   ```

   Para **Gradle**:

   ```bash
   ./gradlew bootRun
   ```

2. **Acessar a Documentação OpenAPI**

   Assim que a aplicação estiver em execução, navegue até um dos URLs abaixo em seu navegador para visualizar a documentação da API gerada automaticamente:

    - **Swagger UI** (Documentação interativa da API):
      ```
      http://localhost:8080/swagger-ui.html
      ```

    - **OpenAPI JSON** (Especificação OpenAPI no formato JSON):
      ```
      http://localhost:8080/v3/api-docs
      ```

    Para mais informações, consulte a documentação oficial do [Springdoc-OpenAPI](https://springdoc.org/).

---
