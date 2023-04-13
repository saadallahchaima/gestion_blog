/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class MyDB {
     final String url="jdbc:mysql://localhost:3306/sportify";
     public static final String ARTICLE_IMAGE_URL = "http://127.0.0.1:8000/sportify/article/image/";
    
    final String Username ="root";
    
    final String Password ="";
    
    private Connection connection;
    
    private static MyDB insatnce;  //2- Declare une variable static de type class
    
    private MyDB() {   // 1-Singleton Constructeur Private
        try {
            connection = DriverManager.getConnection(url,Username,Password);
            System.out.println("Connection etablie avec Succ");
                    } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }

    public static MyDB getInsatnce() { // 3-Implemetation la getter de la variable static
        if(insatnce==null)
        {
            insatnce= new MyDB();
        }
        return insatnce;
    }

    public Connection getConnection() {
        return connection;
    }
}
