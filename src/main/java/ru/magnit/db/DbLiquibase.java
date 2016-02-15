package ru.magnit.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;


public class DbLiquibase {
    private static Connection con;
    private static Liquibase liquibase;

    public static void createTestData() throws SQLException, ClassNotFoundException, 
            LiquibaseException, FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream("./src/main/resources/db.properties");
        ResourceBundle rb = new PropertyResourceBundle(fis);
        Database database = null;
        Class.forName(rb.getString("driver"));
        con = DriverManager.getConnection(rb.getString("url"), rb.getString("username"), rb.getString("password"));
        try {     
            database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(con));                
            liquibase = new Liquibase("src/main/resources/changelog.xml", new FileSystemResourceAccessor(), database);
            liquibase.update(new Contexts("liquibase.contexts"), new LabelExpression("liquibase.labels")); 
        }
        finally {
            if (database != null) {
                database.close();
            } else if (con != null) {
                con.close();
            }
        }    
    }
}
