package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        if (isDevMode(args)) {
            DevDatabaseInitializer.start();
        }
        new Main().run();
    }

    public void run() {
        // Resolve DB settings with precedence: System properties -> Environment variables
        String jdbcUrl = resolveConfig("APP_JDBC_URL", "APP_JDBC_URL");
        String dbUser = resolveConfig("APP_DB_USER", "APP_DB_USER");
        String dbPass = resolveConfig("APP_DB_PASS", "APP_DB_PASS");

        if (jdbcUrl == null || dbUser == null || dbPass == null) {
            throw new IllegalStateException(
                    "Missing DB configuration. Provide APP_JDBC_URL, APP_DB_USER, APP_DB_PASS " +
                            "as system properties (-Dkey=value) or environment variables.");
        }

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*          THE WORKFLOW
         * Implementation of CLI logic
         *       Recieve Username
         *       Recieve Password
         *       Options:
         *           1 - List moon missions (prints spacecraft names from `moon_mission`).
         *           2 - Get a moon mission by mission_id (prints details for that mission).
         *           3 - Count missions for a given year (prompts: year; prints the number of missions launched that year).
         *           4 - Create an account (prompts: first name, last name, ssn, password; prints confirmation).
         *           5 - Update an account password (prompts: user_id, new password; prints confirmation).
         *           6 - Delete an account (prompts: user_id; prints confirmation).
         *           0 - Exit
         * Prompt user for Username
         * Store Username in String
         * Prompt user for Password
         * Store Password in String
         * checkIfValidAccount(Username, Password) return boolean
         * if checkFail -> Invalid Username or Password, please try again, or exit with typing: 0
         * else
         * getOptions()
         */

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your Username: ");
        String username = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

//        checkIfValidAccount(Username, Password) return boolean
//           if checkFail -> Invalid Username or Password, please try again, or exit with typing: 0

        System.out.println("Welcome / Välkommen: " + username);
        getOptions();
    }

    /**
     * Determines if the application is running in development mode based on system properties,
     * environment variables, or command-line arguments.
     *
     * @param args an array of command-line arguments
     * @return {@code true} if the application is in development mode; {@code false} otherwise
     */
    private static boolean isDevMode(String[] args) {
        if (Boolean.getBoolean("devMode"))  //Add VM option -DdevMode=true
            return true;
        if ("true".equalsIgnoreCase(System.getenv("DEV_MODE")))  //Environment variable DEV_MODE=true
            return true;
        return Arrays.asList(args).contains("--dev"); //Argument --dev
    }

    /**
     * Reads configuration with precedence: Java system property first, then environment variable.
     * Returns trimmed value or null if neither source provides a non-empty value.
     */
    private static String resolveConfig(String propertyKey, String envKey) {
        String v = System.getProperty(propertyKey);
        if (v == null || v.trim().isEmpty()) {
            v = System.getenv(envKey);
        }
        return (v == null || v.trim().isEmpty()) ? null : v.trim();
    }


    public void getOptions() {
        System.out.println(
                """
                        You can choose from the following options:
                        1 - List moon missions (prints spacecraft names from `moon_mission`).
                        2 - Get a moon mission by mission_id (prints details for that mission).
                        3 - Count missions for a given year (prompts: year; prints the number of missions launched that year).
                        4 - Create an account (prompts: first name, last name, ssn, password; prints confirmation).
                        5 - Update an account password (prompts: user_id, new password; prints confirmation).
                        6 - Delete an account (prompts: user_id; prints confirmation).
                        0 - Exit.
                """
            );
    }


}
