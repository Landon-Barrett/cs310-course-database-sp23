package edu.jsu.mcis.cs310.coursedb;

import edu.jsu.mcis.cs310.coursedb.dao.*;

public class Main {

    public static void main(String[] args) {
        
        DAOFactory daoFactory = new DAOFactory("coursedb");
        
        if ( !daoFactory.isClosed() ) {
            System.out.println("Connected Successfully!");
        }
        SectionDAO test = daoFactory.getSectionDAO();
        RegistrationDAO test2 = daoFactory.getRegistrationDAO();
        //test.find(1, "MU", "113");
        //test2.create(1, 1, 20001);
        test2.delete(1, 1, 20001);
        
    }
    
}