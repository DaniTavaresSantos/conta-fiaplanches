# Projeto de conta

Este é o repositório do Projeto conta fiaplanches, uma aplicação incrível desenvolvida pela equipe Grupo 48. Esse projeto consiste em uma aplicação web que fornece serviços relacionados a lanchonete FiapLanches que está informatizando seus processos onde este microServiço é responsável especificamente pelo contexto de Conta dessa Lanchonete

## Instruções de Instalação

Para executar o projeto localmente, siga as instruções abaixo:

1. Clone este repositório em sua máquina local:

   ```shell
   git clone https://github.com/DaniTavaresSantos/conta-fiaplanches.git
   ```

2. Rode o comando de dockerCompose na raíz do projeto para subir os serviços e a infra relacionados a este microserviço:

   ```shell
   docker-compose up -d
   ```   

Isso iniciará os containers necessários para executar a aplicação.

## Para testar a funcionalidade da aplicação, basta utilizar a collection do Postman localizada na pasta: Collection-Postman, na raíz do projeto.

## Importe também o Environment também localizado na pasta: Collection-Postman.

## Ordem do caminho feliz a partir dos endpoints do postman:

## Utilizar os seguintes requests, onde o body informado ja é funcional.
1. client/createClient
2. client/findCpf
3. client/updateClient
4. client/health
