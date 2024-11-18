/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uilog;

/**
 *
 * @author DMHUNG
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountUtils {
    private static final String ACCOUNT_FILE = "acc.txt";

    public static boolean isUsernameExists(String username) throws IOException {
        List<String> accounts = getAccounts();
        for (String account : accounts) {
            if (account.startsWith("Username: " + username)) {
                System.out.println("Username: " + username + " is already taken");
                return true;
            }
        }
        return false;
    }

    public static void saveAccount(String firstName, String lastName, String username, String password, String email, String mobile) throws IOException {
        if (isUsernameExists(username)) {
            System.out.println("Cannot save account. Username: " + username + " already exists.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true))) {
            writer.write("First Name: " + firstName);
            writer.newLine();
            writer.write("Last Name: " + lastName);
            writer.newLine();
            writer.write("Username: " + username);
            writer.newLine();
            writer.write("Password: " + password);
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            writer.write("Mobile: " + mobile);
            writer.newLine();
            writer.newLine();
        }
    }

    public static boolean checkCredentials(String username, String password) throws IOException {
        List<String> accounts = getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals("Username: " + username) &&
                accounts.get(i + 1).equals("Password: " + password)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> getAccounts() throws IOException {
        List<String> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                accounts.add(line.trim());
            }
        }
        return accounts;
    }
}
