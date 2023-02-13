package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class SectionDAO {
    
    // INSERT YOUR CODE HERE
    private static final String QUERY_FIND = "SELECT * FROM section s WHERE termid = ? AND subjectid = ? AND num = ?";
    //private static final String QUERY_FIND = "SELECT * FROM section WHERE termid = ?";

    
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        
        String result = "[]";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        DAOUtility util = new DAOUtility();
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, termid);
                ps.setString(2, subjectid);
                ps.setString(3, num);
                //System.out.println("Test");
                
                boolean hasresults = ps.execute();
                
                if (hasresults) {

                    rs = ps.getResultSet();
                    ResultSetMetaData mData = rs.getMetaData();
                    int length = mData.getColumnCount();
                    String line = mData.getColumnName(1);

                    for(int i = 1; i < length + 1; i++) {
                        line = mData.getColumnName(i);
                        //System.out.println(line);
                    }
                    while(rs.next())
                        //System.out.println(rs.getString("crn"));
                    result = util.getResultSetAsJson(rs);
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}