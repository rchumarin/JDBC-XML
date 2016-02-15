package ru.magnit.main;

import java.io.IOException;
import java.sql.SQLException;
import liquibase.exception.LiquibaseException;
import ru.magnit.db.DbLiquibase;
import ru.magnit.processor.ResourceProcessor;
import ru.magnit.processor.ResourceProcessorImpl;

/**
 *
 * @author rchumarin
 */
public class Main {

    public static void main(String[] args) throws SQLException, 
            ClassNotFoundException, LiquibaseException, IOException {
        
        //creating tables with liquibase
//        DbLiquibase.createTestData();
        
        long timeStart = System.currentTimeMillis() / 1000L;                
        ResourceProcessor resourceProcessor = new ResourceProcessorImpl();
        resourceProcessor.resourceProcessorImpl(1_000_000);        
        long timeFinish = System.currentTimeMillis() / 1000L;
        System.out.println("Elapsed time, seconds: " + (timeFinish-timeStart));
        
        
  
    }
    
}
