package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_SP23 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        //JsonArray line = new JsonArray();
        
        
        try {
        
            if (rs != null) {

                // INSERT YOUR CODE HERE
                ResultSetMetaData header = rs.getMetaData();
                
                while(rs.next()) {
                    JsonObject line = new JsonObject();
                    for(int i = 0; i < header.getColumnCount(); i++) {
                        String column = header.getColumnName( i+1);
                        //Object value = rs.getObject(column);
                        
                        //line.put(column, value);
                        
                        
                        if(column.endsWith("termid")) {
                            line.put(column,rs.getInt(column));
                        }
                        /*
                        else if((column.endsWith("start")) || (column.endsWith("end"))) {
                            line.put(column, rs.getTime(column));
                        }
                        */
                        else
                            line.put(column, rs.getString(column));
                        
                    }
                    records.add(line);
                }
                System.out.println(records);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
