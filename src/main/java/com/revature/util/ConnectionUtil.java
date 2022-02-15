package com.revature.util;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static Connection conn;
    public ConnectionUtil() {

    }
    public static Connection getConnection() {


        try {


            FileInputStream propertiesInput = new FileInputStream("C:\\Revature\\ProjectStructure\\src\\main\\resources\\sql.properties");

            Properties props = new Properties();
            props.load(propertiesInput);

            String url = (String) props.get("url");
            String username = (String) props.get("username");
            String password = (String) props.get("password");

            if (conn == null) {
                try {
                    conn = DriverManager.getConnection(url, username, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
