# Projeto Event

### Acesse abaixo a URL  para ver o site Event 1.0

### https://projeto-integrador-projeto-implementacao-event.netlify.app/

### Descrição do projeto

Este projeto tem como objetivo demonstrar a aplicabilidade das seguintes tecnologias: Spring boot, Spring Rest, Spring data JPA, Spring MVC, Spring Security, OAuth2 entre outros projetos do ecossistema Spring.
![mc2](https://github.com/TalissonMelo/project-event-backend/blob/master/mc2.png)


## Modelo Conceitual 2.0
![mc](https://github.com/TalissonMelo/project-event-backend/blob/master/mc.png)

## Informações relacionadas a IDE utilizada.
Neste projeto usei a IDE "Spring Tool Suite" - versão (4.4.4.0RELESE).

Podendo usar também IDE "Eclipse IDE for Enterprise Java Developers." - versão (4.15.0). 

Podendo usar também IDE "IntelliJ" Versão: 2020.2.2 Build: 202.7319.50

## Como replicar o projeto em meu ambiente de execução?
Faça o download do projeto e abra-o na IDE Spring Tool Suite, eclipse ou IntelliJ.

A seguir, com o banco Mysql rodando, ou simplesmente utilize o database H2 que gera um banco em memoria. Caso queira alterar as configurações de acesso ao banco isso pode ser feito alterando o arquivo application.properties do projeto project-event.

##### spring.profiles.active=test -> Utiliza o banco H2 
##### Acesso ao banco H2 http://localhost:8080/h2-console

##### spring.profiles.active=dev -> Utiliza o Mysql 

Algumas funcionalidades como o envio de email em texto plano e Html so funcionam com o o profile de desenvolvimento ou seja: 

###### spring.profiles.active=dev -> Utiliza o banco de dados Mysql .

A execução do projeto pode ser feita clicando com o botão direito do mouse sobre o arquivo "ProjectEventApplication", e escolhendo a opção "Run as > Java application". 

Neste instante o projeto será construído e o servidor será inicializado na porta padrão 8080.

Para realizar os testes na aplicação pode-se utilizar uma extensão do navegador (Chrome, firefox etc...). A sugestão da extensão a ser utilizada é o "Postman", "Insomnia" ou "Rester".

# Spring Security e OAuth2
![mc](https://github.com/TalissonMelo/project-event-backend/blob/master/security.jpg)

<b> Resource Owner (Usuários) : </b> É a pessoa entidade que concede o acesso aos seus dados. Literalmente o dono do recurso.

<b> Client (Cliente) : </b> É a aplicação que interage com o Resource Owner. No caso de uma App Web, seria a aplicação do Browser.

<b> Authorization Server : </b> Responsável por autenticação e emitir tokens de acesso (Access Token). Detém informações dos Resource Owner (Usuários) e expõe no formato de Claims através do Bearer Token. Autentica e interage com o usuário após identificar e autorizar o cliente.

<b> Resource Server : </b> É a API. Exposta na internet e contém os dados do Usuário. Para conseguir acesso ao seu conteúdo é necessário um token emitido pelo Authorization Server.


# Front-End

O desenvolvimento da parte do front-end (Uma página WEB nativa com Javascript utilizando as tecnologias react, https://projeto-integrador-projeto-implementacao-event.netlify.app/) juntamente com validações de campos, filtros de pesquisa, implementações para melhorar a experiência do cliente que irá consumir o serviço.


## Cliente Java

##### https://github.com/TalissonMelo/client-rest-api-java-spring
