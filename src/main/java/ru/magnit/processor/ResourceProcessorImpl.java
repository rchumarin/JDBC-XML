package ru.magnit.processor;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.magnit.dao.FieldDao;
import ru.magnit.dao.FieldDaoImpl;
import ru.magnit.model.Field;
import ru.magnit.resource.CreateXmlFile;
import ru.magnit.resource.CreateXmlFileImpl;
import ru.magnit.resource.ModifyXmlFile;
import ru.magnit.resource.ModifyXmlFileImpl;
import ru.magnit.resource.ParserXmlFile;
import ru.magnit.resource.ParserXmlFileImpl;

/**
 *
 * @author rchumarin
 */
public class ResourceProcessorImpl implements ResourceProcessor{

    @Override
    public void resourceProcessorImpl(int maxValue) {

        FieldDao fieldDao = new FieldDaoImpl();  
        try {
            //delete and insert values in table
            if(fieldDao.getFieldsEmpty()) {
                fieldDao.deleteFields();                
                fieldDao.addField(maxValue);                                
            }
            else {
                fieldDao.addField(maxValue);                                       
            }
            
            CreateXmlFile createXmlFile = new CreateXmlFileImpl();
            List<Field> listField = null;
            listField = fieldDao.getFields();          
            createXmlFile.createXmlFile(listField);
            
            ModifyXmlFile  modifyXmlFile = new ModifyXmlFileImpl();
            modifyXmlFile.modifyXmlFile();
        
            ParserXmlFile parseXmlFile = new ParserXmlFileImpl();
            parseXmlFile.parserXmlFile();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    
}
