package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_SP23 = 1;
    
    //This method takes result set as input and converts it to JsonArray of JsonObjects.
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();

        try {
            
            if (rs != null) {
                                
                //Iterate through result set and add values to map.
                while(rs.next()) {  //Gets Row
                    
                    JsonObject line = new JsonObject();
                    
                    ResultSetMetaData header = rs.getMetaData();
                    int columnNumber = header.getColumnCount();
                    
                    for(int i = 0; i < columnNumber; i++) {  //Gets column.
                        
                        String column = header.getColumnName(i + 1);  //Header starts at index 1 hence i + 1.
                        String value = rs.getString(column);

                        line.put(column, value);
                        
                    }
                    
                    records.add(line);  //Each index is Json Object.
                    
                }

            }
            
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);  
        
    }
    
}
