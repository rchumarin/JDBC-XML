package ru.magnit.dao;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ru.magnit.db.DBConnection;
import ru.magnit.db.DbUtil;
import ru.magnit.model.Field;

/**
 *
 * @author rchumarin
 */
public class FieldDaoImpl implements FieldDao{
    private Connection con;
    private Statement st; 
    private PreparedStatement ps;    

    @Override
    public void addField(int maxValue) {
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            con.setAutoCommit(false);
            for(int i =1; i<=maxValue; i++){    
                String query = "insert into test values ("+i+")";
                st.addBatch(query);                 
            }
            
            try {                
                st.executeBatch();
            } catch (SQLException e) {
               e.printStackTrace();
                return; // Exit if there was an error
            }            
            System.out.println("Inserted");            
            st.executeBatch();            
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            DbUtil.close(st);
            DbUtil.close(con);
        }       
    }
    
//    @Override
//    public void addField(int field) throws SQLException {
//        String query = "INSERT INTO test (field) VALUES (?)";
//        try {
//            con = ConnectionFactory.getConnection();            
//            ps = con.prepareStatement(query);
//            ps.setInt(1, field);    
//            int i = ps.executeUpdate();
//            if (i != 0) {
//                System.out.println("Inserted");
//            } else {
//                System.out.println("not Inserted");
//            }
//        } finally {
//            DbUtil.close(ps);            
//            DbUtil.close(con);            
//        }   
//    }

    @Override
    public void deleteFields()throws SQLException {
        String query = "TRUNCATE TABLE test;";
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(query);
            int i = ps.executeUpdate();
            if (i != 0) {
                System.out.println("not deleted");
            } else {
                System.out.println("deleted");
            }
        } finally{
            DbUtil.close(ps);
            DbUtil.close(con);
        }               
    }

    @Override
    public List<Field> getFields() throws SQLException {
        String query = "SELECT field FROM test;";
        ResultSet rs = null;                   
        ArrayList<Field> fields = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
                int id = rs.getInt(1);  
                fields.add(new Field(id));            
            }            
        } finally{
            DbUtil.close(rs);
            DbUtil.close(ps);
            DbUtil.close(con);
        }       
        return fields;
    }
    
    @Override
    public Boolean getFieldsEmpty() throws SQLException {
        String query = "select exists (select * from test)";
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                result = rs.getBoolean(1);                                
            }    
        } finally {
            DbUtil.close(rs);
            DbUtil.close(ps);
            DbUtil.close(con);            
        }        
        return result;
    }   
}
