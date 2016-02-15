package ru.magnit.dao;

import java.sql.SQLException;
import java.util.List;
import ru.magnit.model.Field;

/**
 *
 * @author rchumarin
 */
public interface FieldDao {    
    public void addField(int maxValue);
    public void deleteFields() throws SQLException;    
    public List<Field> getFields() throws SQLException;
    public Boolean getFieldsEmpty() throws SQLException;
}
