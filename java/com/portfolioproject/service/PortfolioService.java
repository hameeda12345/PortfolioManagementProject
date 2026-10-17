
package com.portfolioproject.service;

import com.portfolioproject.model.User;

import com.portfolioproject.execption.UserNotFoundException;
import com.portfolioproject.execption.InvalidStockException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PortfolioService {

    private Map<String, User> users = new HashMap<>();

    // Add User
    public void addUser(User user) {
        users.put(user.getUserid(), user);
    }

    // Check whether user exists
    public boolean userExists(String userid) {
        return users.containsKey(userid);
    }

    // Get User by ID
    public User getUser(String userid)
            throws UserNotFoundException {

        User user = users.get(userid);

        if (user == null) {
            throw new UserNotFoundException(
                "User not found: " + userid
            );
        }

        return user;
    }

    // Get all users
    public Collection<User> getAllUsers() {
        return users.values();
    }

    // Load users from saved data
    public void loadUsers(Collection<User> loadedUsers) {
        for (User user : loadedUsers) {
            users.put(user.getUserid(), user);
        }
    }

    // Validate stock or mutual fund quantity and price
    public void validateStock(int quantity, double price)
            throws InvalidStockException {

        if (quantity <= 0) {
            throw new InvalidStockException(
                "Quantity must be greater than zero."
            );
        }

        if (price <= 0) {
            throw new InvalidStockException(
                "Purchase price must be greater than zero."
            );
        }
        
        
    }
}