# 💸 Pic-Cash - API  

### Uma solução eficiente para transferências financeiras entre **usuários comuns** e **lojistas**. 🚀  

Bem-vindo ao repositório do **Pic-Cash**, criado para o **Desafio PicPay**. Este projeto concentra-se no backend de um sistema de transferência de dinheiro com funcionalidades avançadas e segurança robusta.  

---

## ⚙️ Funcionalidades  

### 🧑‍💻 Cadastro de Usuários  
- Cadastro de **clientes** e **lojistas** com:
  - **Nome Completo**, **CPF**, **E-mail** e **Senha**.  
  - **CPF/CNPJ** e **E-mails** únicos no sistema.  

### 💸 Transferência de Dinheiro  
- Usuários podem enviar dinheiro para outros clientes ou lojistas.  
- **Lojistas** só podem **receber** transferências, mas não enviá-las.  
- Validação de saldo disponível antes de cada transferência.  

### ✅ Consulta de Serviço Autorizador Externo  
- Garantia de legitimidade através de um serviço autorizador.  
- Simulação com mock: [Mock de Autorização](https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc).  

### 🔄 Transação Reversível  
- Transferências são tratadas como transações.  
- Em caso de falha, os valores são automaticamente reembolsados.  

### 📧 Notificações de Pagamento  
- Envio de notificações para **clientes** e **lojistas** ao receberem pagamentos.  
- Notificação simulada via mock: [Mock de Notificação](https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6).  
- Implementação de mecanismos para lidar com instabilidades no serviço de notificação.  

---

## 🛠️ Tecnologias  

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) ☕ - Linguagem principal.
- [Spring Framework](https://spring.io/) 💚 - Framework robusto para desenvolvimento de aplicações empresariais.
- [PostgreSQL](https://www.postgresql.org/) 📓 - Banco de dados relacional de alta performance.
- [Maven](https://maven.apache.org/) ⚖️ - Ferramenta para automação e gestão de dependências.

---

## 📦 Dependências  

| Dependência          | Link                                                                                 |  
|----------------------|--------------------------------------------------------------------------------------|  
| **Lombok**           | [Lombok Setup](https://projectlombok.org/setup/maven)                               |  
| **PostgreSQL**       | [PostgreSQL Driver](https://mvnrepository.com/artifact/org.postgresql/postgresql)   |  
| **Spring Validation**| [Spring Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)|  

---

## 🚀 Instalação  

### 🗄️ Configurações do Banco de Dados  

1. **Abra o arquivo** `application.properties` na pasta do projeto.  
2. Substitua as configurações padrão com as informações do seu banco de dados PostgreSQL:  

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/piccash
spring.datasource.username=seu_usuario  
spring.datasource.password=sua_senha
```

3. Verifique o driver do banco de dados:  

```properties
spring.datasource.driverClassName=org.postgresql.Driver
```

4. Configure o Hibernate para gerenciar o esquema automaticamente:  

```properties
spring.jpa.hibernate.ddl-auto=update
```

5. Defina o **dialeto do Hibernate** para PostgreSQL:  

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 🔒 Configurações de Autorização e Notificação  

1. Adicione as URLs dos serviços no arquivo `application.properties`:  

```properties  
piccash.autorization.url=https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc  
piccash.notification.url=https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6  
```  

---

### ▶️ Inicializando o Sistema  

1. Certifique-se de que o PostgreSQL está rodando no ambiente local.  
2. Compile e execute a aplicação utilizando sua IDE ou os comandos do Maven:  

```shell script
mvn spring-boot:run
```

3. Utilize ferramentas como **Postman** ou **Insomnia** para testar os endpoints disponibilizados.

## 📝 Notas Adicionais  

Certifique-se de adaptar as configurações conforme necessário para o seu ambiente. Este guia fornece os passos básicos para iniciar o **Pic-Cash API** de forma eficiente.  
