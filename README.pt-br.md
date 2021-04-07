[🇺🇸 EN-US](README.md)
___
# Trabalho de Testes Unitários
> Trabalho referente à disciplina de Teste e Validação de Sistemas.  
> Cenário dos testes é uma aplicação simples de um banco que possui 3 classes primárias: Banco, Cliente e Conta.

## Introduction
> Testes referentes à classe [`Banco`](src/main/java/br/com/aula/Banco.java) realizados na classe [`BancoTest`](src/test/java/br/com/aula/BancoTest.java)  
> Requerimentos do trabalho:  
> A. Cadastrar Contas  
> &emsp;a. Deve cadastrar uma conta.  
> &emsp;b. Não deve cadastrar conta com número de conta já existente.  
> &emsp;c. Não deve cadastrar conta com número de conta inválido (Altere a implementação).  
> &emsp;d. Não deve cadastrar conta com nome de cliente já existente.  
> B. Efetuar transferência entre contas  
> &emsp;a. Deve realizar transferência entre contas Corrente e Poupança.  
> &emsp;b. Deve-se verificar a existência da conta de origem no banco.  
> &emsp;c. Não deve permitir que uma conta de origem do tipo Poupança fique com saldo negativo.  
> &emsp;d. Deve verificar existência da conta de destino no banco  
> &emsp;e. Não deve permitir transferir um valor negativo (Altere a implementação).  

## Executando os testes
> Não existe uma classe "main", para executar os testes instale as dependências maven compile as classes de teste em src/test/java/br/com/aula com o arquivo jar do JUnit ou execute os testes utilizando sua IDE favorita.