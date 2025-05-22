
# Sistema de Transações Bancárias com Concorrência de Saldo

Esta API permite:

- Criar novas contas,
- Mesmo não passando um valor inicial, a conta é aberta com saldo zero,
- Cada conta tem um número único, gerado randomicamente,
- Após a conta criada, você é capaz de fazer depósitos, saques e transferências,
- Em caso de contas com saldo zerado, não é possível realizar saques e transferências, apenas depósitos,
- A aplicação é capaz de lidar com múltiplas transações ocorrendo simultaneamente.

## Tecnologias utilizadas
- Kotlin
- Spring Boot
- Gradle
- MySQL
- Docker compose
- Slf4
- Flyway
- MockK
- Junit
- Dbeaver

## Rodando Local

🚨Atenção: Para seguir os comandos abaixo é necessário excluir o arquivo "application-oracle.yml" e a "V6" na pasta de migrations.

Clone o projeto

```bash
  git clone https://github.com/Lua-programmer/Bank_Transaction_System.git
```

Entre na pasta do projeto

```bash
  cd Bank_Transaction_System
```

Instale o banco de dados com o docker-compose

```bash
  docker compose up -d
```

Inicie o servidor

```bash
  ./gradlew bootRun
```
Acessando o banco de dados com o Dbeaver
```bash
    url: jdbc:mysql://localhost:3306/digital_bank
    usuário: root
    senha: root_password
```
Após o servidor iniciado você pode seguir a documentação utilizando o Postman para fazer as requisições ou utilizar a linha de comando.

## Documentação

[Postman](https://www.postman.com/red-robot-505129/sistema-bancrio/collection/91zn228/tabela-verdade)

[Swagger](http://localhost:8080/swagger-ui/index.html)


Ao clicar no link abaixo, você terá acesso a uma coleção montada para testar concorrência de transações, bem como as funcionalidades de depósito, saque e transferência.
Com a ajuda do flyway, algumas contas já são adicionadas ao subir a aplicação local, facilitando nos testes, mas sugiro também criar novas contas e "brincar" com a funcionalidades da aplicação.
[Sistema_Bancário.json](sistema_bancario.json)

## Implementações para PR:
✅ Criar conta de forma assíncrona usando o Apache Kafka.
- [ ] Salvar transação de forma assíncrona usando o Apache Kafka, após cada ação de saque, depósito ou transferência. Atualmente está de forma síncrona.
- [ ] Trazer transações passando o número da conta como parâmetro, usando paginação.


## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/luanamelissaprogrammer/)

## Autora

- [@lua-programmer](https://github.com/Lua-programmer)


