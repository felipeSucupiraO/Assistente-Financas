# Assistente de finanças - Backend

O backend do projeto é feito em spring boot com um banco de dados usando arquivos csv.

## Usando a api

Para usar a api, siga os seguintes passos:
### Copie o repositório
Abra o terminal e digite o comando:
```
git clone https://github.com/felipeSucupiraO/Assistente-Financas
cd Assistente-Financas
```

### Instale as dependências
Em seguida, instale as dependencias necessárias:
```
cd backend/assistentefinancas
mvn clean install
```

### Rode a api
Você pode rodar a api utilizando o maven:
```
mvn spring-boot:run
```
Ou criar o arquivo JAR e rodar a api
```
mvn clean package
java -jar target/your-project-name.jar
```

## Endpoints
Esses são os endpoints que a api fornece.

GET /getUsuarioById/{id} - Pega um objeto usuário pelo id.

POST /adicionarUsuario - Cria um novo usuário.

POST /deletarUsuario/{idUsuario} - Deleta um usuário.


GET /getListaTransacoesDoUsuario/{idUsuario} - Pega uma lista com todas as transações do usuário.

POST /adicionarReceitaAoUsuario/{idUsuario} - Cria uma transação do tipo receita. O objeto receita deve estar no corpo do request.

POST /adicionarDespesaAoUsuario/{idUsuario} - Cria uma transação do tipo despesa. O objeto despesa deve estar no corpo do request.

POST /adicionarTransferenciaAoUsuario/{idUsuario} - Cria uma transação do tipo transferência. O objeto transferência deve estar no corpo do request.


POST /adicionarContaAoUsuario/{idUsuario} - Adiciona uma conta ao usuário. O objeto conta deve estar no corpo do request.

POST /deletarContaDoUsuario/{idConta}/{idUsuario} - Deleta uma conta do usuário.


GET /getRelatorioAnoDoUsuario/{ano}/{idUsuario} - Pega o relatório de um ano de um usuário.

GET /getRelatorioMesDoUsuario/{ano}/{mes}/{idUsuario} - Pega o relatório de um mês de um usuário.
