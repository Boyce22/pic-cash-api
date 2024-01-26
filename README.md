# Pic-Cash - API
### Transferência de dinheiro entre dois tipos de usuários: comuns e lojistas.

Bem-vindo ao repositório do Pic-Cash, uma solução para o Desafio PicPay. Este projeto concentra-se no desenvolvimento do backend de um sistema de transferência de dinheiro entre dois tipos de usuários: clientes e comerciantes.

## Funcionalidades

1. **Cadastro de Usuários:**
   - Ambos os tipos de usuários (clientes e comerciantes) devem fornecer Nome Completo, CPF, E-mail e Senha durante o cadastro.
   - CPF/CNPJ e e-mails devem ser únicos no sistema, evitando duplicatas.

2. **Transferência de Dinheiro:**
   - Usuários podem efetuar transferências de dinheiro entre si e para comerciantes.
   - Comerciantes só recebem transferências, não realizam envios de dinheiro.
   - Antes de concluir a transferência, é validado se o usuário possui saldo suficiente.

3. **Consulta de Serviço Autorizador Externo:**
   - Antes de finalizar a transferência, o sistema consulta um serviço autorizador externo para garantir a legitimidade da transação.
   - Mock: [link](https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc) para simular o serviço autorizador.

4. **Transação Reversível:**
   - A operação de transferência é tratada como uma transação, permitindo reversão em caso de inconsistências.
   - Em qualquer cenário de falha, o dinheiro é reembolsado para a carteira do usuário que realizou a transferência.

5. **Notificações de Pagamento:**
   - Ao receber um pagamento, tanto usuários quanto comerciantes devem ser notificados.
   - A notificação é "enviada" via e-mail,
   - Mock [link](https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6) usado para simular o envio das notificações.
   - Considerado a possibilidade de instabilidade no serviço de notificação e implementado mecanismos de tratamento de falhas.

## Tecnologias

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) - Um das versões mais recente da linguagem de programação Java, proporcionando recursos avançados e melhorias de desempenho.

- [Spring Framework](https://spring.io/) - Um framework de desenvolvimento para Java que oferece suporte abrangente para o desenvolvimento de aplicativos empresariais modernos.

- [PostgreSQL](https://www.postgresql.org/) - Um sistema de gerenciamento de banco de dados relacional de código aberto, conhecido por sua confiabilidade e recursos avançados.

- [Maven](https://maven.apache.org/) - Uma ferramenta de automação de construção e gerenciamento de dependências amplamente utilizada para projetos Java. O Maven simplifica o processo de construção e gerenciamento de projetos, facilitando a integração de bibliotecas e o gerenciamento de dependências.


## Dependências

| Dependências | Link |
| ------ | ------ |
| Lombok | [https://projectlombok.org/setup/maven] |
| PostgreSQL | [https://mvnrepository.com/artifact/org.postgresql/postgresql] |
| Spring Validation | [https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation]|


## Instalação

### Configurações do Banco de Dados:

1. Abra o arquivo `application.properties`.
2. Modifique as seguintes propriedades com as informações do seu PostgreSQL:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/piccash
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```
3. Certifique-se de que o driver PostgreSQL está corretamente configurado:
    ```properties
    spring.datasource.driverClassName=org.postgresql.Driver
    ```
4. Certifique-se de que o driver PostgreSQL está corretamente configurado:
    ```properties
    spring.datasource.driverClassName=org.postgresql.Driver
    ```
5. Configure o Hibernate para atualizar automaticamente o esquema do banco de dados:
    ```properties
    spring.jpa.hibernate.ddl-auto=update
    ```
6. Defina o dialeto do Hibernate para PostgreSQL:
    ```properties
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```
### Configurações de Autorização e Notificação:

1. Configure as URLs dos serviços de autorização e notificação no arquivo:

   ```properties
    piccash.autorization.url=https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc
    piccash.notification.url=https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6
   ```
 ## Inicie o Pic-Cash:
 
- Após configurar as propriedades, você está pronto para iniciar o Pic-Cash em seu ambiente local.
Certifique-se de substituir seu_usuario e sua_senha pelas credenciais corretas do seu PostgreSQL. Este é um guia básico para configurar o ambiente. Certifique-se de adaptar as configurações conforme necessário para o seu ambiente específico.
