[🇧🇷 PT-BR](README.pt-br.md)
___
# Unit Tests Assignment
> University assignment about unit tests using JUnit.  
> Setting for the tests is a simple Bank application that has 3 primary clases: Banco (Bank), Cliente (Client/Customer) and Conta (Account).

## Introduction
> Tests referring to the class [`Banco`](src/main/java/br/com/aula/Banco.java) made in the class [`BancoTest`](src/test/java/br/com/aula/BancoTest.java)  
> Assignment requirements:  
> A. Account Register  
> &emsp;a. it should register an account.  
> &emsp;b. it shouldn't register an account with existing number.  
> &emsp;c. it shouldn't register an account with invalid number (Change the implementation).  
> &emsp;d. it shouldn't register an account with existing client name.  
> B. Transfer between accounts  
> &emsp;a. it should make transfers between Checking Account and Savings Account.  
> &emsp;b. it should verify if the origin account exists in the bank.  
> &emsp;c. it shouldn't allow a savings account to have negative balance.  
> &emsp;d. it should verify if the destination account exists in the bank.  
> &emsp;e. it shouldn't allow a transfer with negative value (Change the implementation).

## Running the tests
> There's no main class, to run the tests install maven dependencies compile the Test classes in src/test/java/br/com/aula with the JUnit jar file or run tests using your favorite IDE.