package org.example;

import org.example.domain.Player;
import org.example.domain.Transaction;
import org.example.exceptions.*;
import org.example.in.MenuInput;
import org.example.in.PlayerInput;
import org.example.in.TransactionInput;
import org.example.out.MenuOutput;
import org.example.out.PlayerOutput;
import org.example.out.TransactionOutput;
import org.example.repository.PlayerRepository;
import org.example.repository.PlayerRepositoryImpl;
import org.example.repository.TransactionRepository;
import org.example.repository.TransactionRepositoryImpl;
import org.example.service.PlayerService;
import org.example.service.TransactionService;

import java.util.Scanner;

/**
 * The Application class is the entry point of the Wallet Service application.
 * It handles user interactions and initiates the corresponding services.
 */
public class Application {
    /**
     * The main method that runs the Wallet Service application.
     */
    public static void run() {
        PlayerRepository playerRepository = new PlayerRepositoryImpl();
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();

        PlayerService playerService = new PlayerService(playerRepository);
        TransactionService transactionService = new TransactionService(transactionRepository);

        Scanner scanner = new Scanner(System.in);

        MenuInput menuInput = new MenuInput(scanner);
        MenuOutput menuOutput = new MenuOutput();

        PlayerInput playerInput = new PlayerInput(scanner);
        PlayerOutput playerOutput = new PlayerOutput(playerService);

        TransactionInput transactionInput = new TransactionInput(scanner);
        TransactionOutput transactionOutput = new TransactionOutput(transactionService);

        System.out.println("Welcome to Wallet Service");

        while (true) {
            menuOutput.printEnterMenu();
            String menuAction = menuInput.chooseMenuAction();
            if (menuAction.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            else if (menuAction.equalsIgnoreCase("authenticate")) {
                //cycle proceed working
            }
            else {
                //cycle starts again
                System.out.println("Unknown menu action");
                System.out.println("Please enter right menu action");
                continue;
            }

            Player player = null;

            while (player == null) {
                String mode = playerInput.enterAuthenticationMode();
                String username = playerInput.enterUsername();
                String password = playerInput.enterPassword();

                try {
                    player = playerService.authenticate(mode, username, password);
                    Audit.log(mode, username);
                    playerOutput.showPlayerAccount(username);
                } catch (PlayerAlreadyExistsException | PlayerNotFoundException e) {
                    System.out.println(e.getMessage() + "\n");
                }
                if (player == null) {
                    System.out.println();
                }
            }

            while (true) {
                menuOutput.printMainMenu();

                menuAction = menuInput.chooseMenuAction();
                if (menuAction.equalsIgnoreCase("log out")) {
                    Audit.log("log out", player.getName());
                    break;
                } else if (menuAction.equalsIgnoreCase("balance")) {
                    playerOutput.showPlayerAccount(player.getName());
                    Audit.log("check balance", player.getName());
                } else if (menuAction.equalsIgnoreCase("transactions")) {
                    transactionOutput.showTransactionHistoryForPlayer(player);
                    Audit.log("check transactions", player.getName());
                } else if (menuAction.equalsIgnoreCase("make transaction")) {
                    while (true) {
                        try {
                            Transaction.Type type = transactionInput.enterTransactionType();
                            double amount = transactionInput.enterAmount();
                            long transactionId = transactionInput.enterTransactionId();

                            transactionService.makeTransaction(player, amount, transactionId, type);
                            Audit.log(player.getName(), type.toString(), String.valueOf(amount), String.valueOf(transactionId));
                        } catch (NotUniqueTransactionIdException | InsufficientFundsException |
                                 UnknownTransactionException e) {
                            System.out.println(e.getMessage() + "\n");
                            continue;
                        }
                        break;
                    }
                }
            }
        }
    }
}
