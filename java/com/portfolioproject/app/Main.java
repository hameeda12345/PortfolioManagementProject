package com.portfolioproject.app;

import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.portfolioproject.concurrent.PriceUpdateTask;
import com.portfolioproject.execption.InvalidStockException;
import com.portfolioproject.execption.UserNotFoundException;
import com.portfolioproject.model.Asset;
import com.portfolioproject.model.Holding;
import com.portfolioproject.model.MutualFund;
import com.portfolioproject.model.Stock;
import com.portfolioproject.model.User;
import com.portfolioproject.service.PortfolioService;
import com.portfolioproject.util.JVMInfo;
import com.portfolioproject.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {
	
	private static final Logger logger =
	        LoggerFactory.getLogger(Main.class);
	
 


public static void main(String[] args) {

	logger.info("Portfolio Management System started");

    Scanner sc = new Scanner(System.in);
    PortfolioService portfolioService = new PortfolioService();

    int choice;

    System.out.println("=====================================");
    System.out.println("   STOCK PORTFOLIO MANAGEMENT SYSTEM");
    System.out.println("=====================================");

    do {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Create User");
        System.out.println("2. Add Stock Holding");
        System.out.println("3. Add Mutual Fund Holding");
        System.out.println("4. Display User");
        System.out.println("5. Display Holdings");
        System.out.println("6. Sort Holdings");
        System.out.println("7. Save Data");
        System.out.println("8. Load Data");
        System.out.println("9. Update Stock Prices Concurrently");
        System.out.println("10. JVM Information");
        System.out.println("11. Exit");
        System.out.println("----------------------------");
        System.out.print("Enter your choice: ");

        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1: {
            	
            	System.out.println("\n--- Create User ---");

				System.out.print("Enter User ID: ");
				String userid = sc.nextLine();

				System.out.print("Enter User Name: ");
				String name = sc.nextLine();

				System.out.print("Enter Email: ");
				String email = sc.nextLine();

				// Check whether user already exists
				if (portfolioService.userExists(userid)) 
				{
					System.out.println("User already exists!");
					// Log a warning
					logger.warn("Attempt to create an existing user: {}", userid);

				} 
				else 
				{
					User user = new User(userid, name, email);
					portfolioService.addUser(user);
					System.out.println("User created successfully!");
					// Log successful user creation
					logger.info("User created successfully: {}", userid);
				}
				break;

            	
            }
                
            case 2: {
                System.out.println("\n--- Add Stock Holding ---");

                System.out.print("Enter User ID: ");
                String stockUserId = sc.nextLine();

                User stockUser;

                try {
                    stockUser = portfolioService.getUser(stockUserId);
                } catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                if (stockUser == null) {

                    System.out.println("User not found.");
                    break;
                }


                System.out.print("Enter Holding ID: ");
                String stockHoldingId = sc.nextLine();

                System.out.print("Enter Stock ID: ");
                String stockId = sc.nextLine();

                System.out.print("Enter Stock Name: ");
                String stockName = sc.nextLine();

                System.out.print("Enter Purchase Price: ");
                double purchasePrice = sc.nextDouble();

                System.out.print("Enter Current Price: ");
                double currentPrice = sc.nextDouble();

                System.out.print("Enter Quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine();

                
                boolean valid = true;

                try {
                    // Validate quantity and purchase price
                    portfolioService.validateStock(quantity, purchasePrice);

                    // Validate current price
                    if (currentPrice <= 0) {
                        throw new InvalidStockException(
                            "Current price must be greater than zero."
                        );
                    }

                } catch (InvalidStockException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                    valid = false;

                } finally {
                    System.out.println("Stock validation completed.");
                }

                if (!valid) {
                    break;
                }

                Stock stock = new Stock(
                        stockId,
                        stockName,
                        purchasePrice,
                        currentPrice
                );
                

                Holding stockHolding = new Holding(
                        stockHoldingId,
                        stock,
                        quantity
                );

                stockUser.addHolding(stockHolding);

                System.out.println("Stock holding added successfully!");
                break;
            }

            case 3: {
                System.out.println("\n--- Add Mutual Fund Holding ---");

                System.out.print("Enter User ID: ");
                String mfUserId = sc.nextLine();

                User mfUser;

                try {
                    mfUser = portfolioService.getUser(mfUserId);
                } catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                    break;
                }

                System.out.print("Enter Holding ID: ");
                String mfHoldingId = sc.nextLine();

                System.out.print("Enter Mutual Fund ID: ");
                String mfId = sc.nextLine();

                System.out.print("Enter Mutual Fund Name: ");
                String mfName = sc.nextLine();

                System.out.print("Enter Purchase Price: ");
                double mfPurchasePrice = sc.nextDouble();

                System.out.print("Enter NAV: ");
                double nav = sc.nextDouble();

                System.out.print("Enter Quantity: ");
                int mfQuantity = sc.nextInt();
                sc.nextLine();

                boolean valid = true;

                try {
                    portfolioService.validateStock(
                            mfQuantity,
                            mfPurchasePrice
                    );

                    if (nav <= 0) {
                        throw new InvalidStockException(
                                "NAV must be greater than zero."
                        );
                    }

                } catch (InvalidStockException e) {
                    System.out.println(
                            "Validation Error: " + e.getMessage()
                    );
                    valid = false;

                } finally {
                    System.out.println(
                            "Mutual fund validation completed."
                    );
                }

                if (!valid) {
                    break;
                }

                MutualFund mutualFund = new MutualFund(
                        mfId,
                        mfName,
                        mfPurchasePrice,
                        nav
                );

                Holding mfHolding = new Holding(
                        mfHoldingId,
                        mutualFund,
                        mfQuantity
                );

                mfUser.addHolding(mfHolding);

                System.out.println(
                        "Mutual fund holding added successfully!"
                );
                break;
            }

            case 4: {
                System.out.println("\n--- Display All Users ---");

                boolean found = false;

                for (User user : portfolioService.getAllUsers()) {
                    user.display();
                    System.out.println("----------------------------");
                    found = true;
                }

                if (!found) {
                    System.out.println("No users created.");
                }
                break;
            }

            case 5: {
                System.out.println("\n--- Holdings ---");

                boolean userFound = false;

                for (User user : portfolioService.getAllUsers()) {
                    userFound = true;

                    System.out.println("\nUser ID: " + user.getUserid());
                    System.out.println("User Name: " + user.getName());

                    if (user.getHoldings().isEmpty()) {
                        System.out.println("No holdings available.");
                    } else {
                        for (Holding holding : user.getHoldings()) {
                            System.out.println(holding);
                        }
                    }

                    System.out.println("----------------------------");
                }

                if (!userFound) {
                    System.out.println("No users created.");
                }
                break;
            }

            case 6: {
                System.out.println("\n--- Sort Holdings ---");
                System.out.println("1. Sort by Holding ID");
                System.out.println("2. Sort by Quantity");
                System.out.print("Enter your choice: ");

                int sortChoice = sc.nextInt();
                sc.nextLine();

                if (sortChoice != 1 && sortChoice != 2) {
                    System.out.println("Invalid sorting choice.");
                    break;
                }

                boolean sorted = false;

                for (User user : portfolioService.getAllUsers()) {

                    if (user.getHoldings().isEmpty()) {
                        continue;
                    }

                    if (sortChoice == 1) {
                        user.getHoldings().sort(
                                Comparator.comparing(
                                        Holding::getHoldingId
                                )
                        );
                    } else {
                        user.getHoldings().sort(
                                Comparator.comparingInt(
                                        Holding::getQuantity
                                )
                        );
                    }

                    sorted = true;
                }

                if (sorted) {
                    System.out.println("Holdings sorted successfully.");
                } else {
                    System.out.println("No holdings available to sort.");
                }
                break;
            }

            case 7: {
                System.out.println("\n--- Save Data ---");

                JsonUtil.saveUsers(
                        portfolioService.getAllUsers()
                );
                break;
            }

            case 8: {
                System.out.println("\n--- Load Data ---");

                User[] loadedUsers = JsonUtil.loadUsers();

                portfolioService.loadUsers(
                        java.util.Arrays.asList(loadedUsers)
                );

                System.out.println("Data loaded successfully.");
                break;
            }

            case 9: {
                System.out.println(
                        "\n--- Update Stock Prices Concurrently ---"
                );

                ExecutorService executor =
                        Executors.newFixedThreadPool(4);

                for (User user : portfolioService.getAllUsers()) {
                    for (Holding holding : user.getHoldings()) {

                        Asset asset = holding.getAsset();

                        if (asset instanceof Stock) {
                            Stock stockForUpdate = (Stock) asset;

                            PriceUpdateTask task =
                                    new PriceUpdateTask(stockForUpdate);

                            executor.submit(task);

                            System.out.println(
                                    "Task submitted for: "
                                    + stockForUpdate.getAssetName()
                            );
                        }
                    }
                }

                executor.shutdown();
                break;
            }

            case 10: {
                JVMInfo.displayJVMInfo();
                break;
            }

            case 11: {
                System.out.println(
                        "\nThank you for using "
                        + "Stock Portfolio Management System."
                );
                break;
            }

            default:
                System.out.println(
                        "Invalid choice. Please enter 1 to 11."
                );
        }

    } while (choice != 11);

    sc.close();
}


}