package com.hospitalmanagement.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogginTest {

    private static final Logger logger =
            LoggerFactory.getLogger(LogginTest.class);

    public static void main(String[] args) {

        logger.info("Hospital Management Logging Test Started");

        logger.warn("This is a warning test");

        logger.error("This is an error test");
    }
}


