# WalletService
## Description
A service that manages credit/debit transactions on behalf of players. The cash account contains the player's current balance.
The balance can be changed by registering transactions on the account, either debit transactions (removing funds) or credit transactions (adding funds).
## API requirements and running instructions
1. Java 17.0.2
2. Maven to build the application.

To build a project, you need to open the console, change the current directory to the project directory and write in the console:
```
`mvn compile`
```
To run the compiled code, you need to type in the console from the same directory:
```
`mvn exec:java -Dexec.mainClass="org.example.Main"`
