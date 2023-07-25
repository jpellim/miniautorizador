# miniautorizador

Microserviço responsável pelas funcionalidades de um miniautorizador

#Dependêcias

1 - Java Versão 17
2 - Maven 3
3 - Docker
4 - Mysql

# Intalação e Execução com o Docker

1 - Baixe o [repositório do GitHub](git@github.com:jpellim/miniautorizador.git).

2 - Buildar o projeto

    mvn clean install

3 - Para criação do banco de dados. Caso necessário, executar: docker-compose up 

# Documentação da API

Após a instalação e execução do projeto, a documentação está disponível via Swagger, 
no seguinte link: http://localhost:8080/swagger-ui/index.html#/
