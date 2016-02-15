package ru.magnit.db;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 
public class DBConnection {
 
    public static Connection getConnection() {
        Properties props = new Properties();
        FileInputStream fis = null;
        Connection con = null;
        try {
            fis = new FileInputStream("./src/main/resources/db.properties");
            props.load(fis);                        
 
            // load the Driver Class
            Class.forName(props.getProperty("driver"));
 
            // create the connection now
            con = DriverManager.getConnection(props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}