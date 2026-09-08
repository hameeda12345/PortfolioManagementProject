package com.portfolioproject.app;

import com.portfolioproject.model.User;

public class Main {

    public static void main(String[] args) {

        System.out.println("=====================");
        System.out.println("Portfolio Management Project");
        System.out.println("=====================");

        User user = new User("101", "Rishi", "Rishi@gmail.com");

        System.out.println("User ID: " + user.getUserid());
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }
}