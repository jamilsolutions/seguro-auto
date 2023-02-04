# seguro-auto
Projeto desafio para seguro de veículo com API REST em Spring Boot (Graddle).

Requisitos:

1. Possuir o banco de dados MongoDB instalado na máquina local ou servidor:
   https://docs.mongodb.com/manual/administration/install-community/
   Nota: Manter habilidado a instalação do MongoDBCompass.
   
2. Acessar o banco de dados

3. Criar banco de dados seguro-auto se necessário.

4. Ter instalado o Java 8 ou superior no computador ou laptop:
    https://www.java.com/pt-BR/download/manual.jsp

5. Clonar o projeto rodando o comando em uma pasta de sua preferência:
   git clone https://github.com/jamilsolutions/seguro-auto.git

6. Entrar no diretório onde foi clonado o projeto e abrir um pronpt de comand com o Git Bash:   
   Nota: 1. Caso não tenha o git instalado use esse link para instalar: https://git-scm.com/downloads
         2. Tutorial Git em https://git-scm.com/book/pt-br/v2

7. Editar o arquivo <PATH DO PROJETO>/seguro-auto/src/main/resources/application.properties com as configurações do banco de dados:
   Exemplo:
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27017
   spring.data.mongodb.database:seguro-auto
 
8. Rodar o comando no projeto <PATH DO PROJETO>/seguro-auto com o comando:
   ./gradlew bootJar  
   
9. Rodar o projeto com o comando
   cd /build/libs/
   java -jar seguro-auto-0.0.1-SNAPSHOT.jar
   
10. Abrir o Postman e importar a coleção Seguro Auto.postman_collection.json:
    Postman -> File -> Import -> Select folder or Upload the file -> Confirm import.
    
11. Run the collection Seguro Auto -> Run Collection Runner -> Run Seguro Auto.

12. Pronto!


