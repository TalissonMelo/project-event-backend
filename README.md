# project-event-backend

### Descrição do projeto

Este projeto tem como objetivo demonstrar a aplicabilidade das seguintes tecnologias: Spring boot, Spring Rest, Spring data JPA, Spring MVC entre outros projetos do ecossistema Spring.
![mc2](https://github.com/TalissonMelo/project-event-backend/blob/master/mc2.png)


## Modelo Conceitual
![mc](https://github.com/TalissonMelo/project-event-backend/blob/master/mc.png)

## Informações relacionadas a IDE utilizada.
Neste projeto usei a IDE "Spring Tool Suite" - versão (4.4.4.0RELESE).
Podendo usar também  IDE "Eclipse IDE for Enterprise Java Developers." - versão (4.15.0). 

## Como replicar o projeto em meu ambiente de execução?
Faça o download do projeto e abra-o na IDE Spring Tool Suite ou eclipse.

A seguir, com o banco Mysql rodando, ou simplesmente utilize o database H2 que gera um banco em memoria. Caso queira alterar as configurações de acesso ao banco isso pode ser feito alterando o arquivo application.properties.

##### spring.profiles.active=test -> Utiliza o banco H2 
##### Acesso ao banco H2 http://localhost:8080/h2-console

##### spring.profiles.active=dev -> Utiliza o Mysql 

Algumas funcionalidades como o envio de email em texto plano e Html so funcionam com o o profile de desenvolvimento ou seja: 

###### spring.profiles.active=dev -> Utiliza o banco de dados Mysql .

A execução do projeto pode ser feita clicando com o botão direito do mouse sobre o arquivo "ProjectEventApplication", e escolhendo a opção "Run as > Java application". 

Neste instante o projeto será construído e o servidor será inicializado na porta padrão 8080.

Para realizar os testes na aplicação pode-se utilizar uma extensão do navegador (Chrome, firefox etc...). A sugestão da extensão a ser utilizada é o "Postman", "Insomnia" ou "Rester".

# Front-End

O desenvolvimento da parte do front-end (Uma página WEB nativa com Javascript utilizando as tecnologias react, https://github.com/TalissonMelo/event-frontend-react) juntamente com validações de campos, filtros de pesquisa, implementações para melhorar a experiência do cliente que irá consumir o serviço.
