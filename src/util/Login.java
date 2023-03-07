/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @ayachizakaria
 */
public class Login {

    // Create a private static instance of Login class
    private static Login instance;

    // Declare instance variables for username and password
    private int id_u;
    private String username;
    private String password;

    // Create a private constructor to prevent external instantiation
    private Login() {
    }

    // Create a public static method to get the single instance of Login class
    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    // Setters and getters for username and password
    public void setUserid(int id_u) {
        this.id_u = id_u;
    }
    public int getUserid() {
        return this.id_u;
    }
    
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    // Method to validate login credentials
    public boolean isValidLogin() {
        // Add your login validation code here
        return false;
    }
}