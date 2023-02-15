package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_SP23 = 1;
    
    //This method takes result set as input and converts it to Json Array.
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();

        try {
            if (rs != null) {
                ResultSetMetaData header = rs.getMetaData();
                
                //Iterate through result set and add values to map.
                while(rs.next()) {
                    JsonObject line = new JsonObject();
                    
                    int columnNumber = header.getColumnCount();
                    for(int i = 0; i < columnNumber; i++) {
                        String column = header.getColumnName(i + 1);

                        //Selection is needed because termid needs to retain int type.
                        if(column.endsWith("termid")) {
                            line.put(column,rs.getInt(column));
                        }

                        else
                            line.put(column, rs.getString(column));                        
                    }
                    records.add(line);  //Each index is Json Object.
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
