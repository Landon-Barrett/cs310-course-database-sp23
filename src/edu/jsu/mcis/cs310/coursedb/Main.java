/*
Submitted by: Landon Barrett
Date: 02/16/23
CS: 310-001
Problem Summary: Complete a program that involves interacting with a SQL
database. 
*/
package edu.jsu.mcis.cs310.coursedb;

import edu.jsu.mcis.cs310.coursedb.dao.*;

public class Main {

    public static void main(String[] args) {
        
        DAOFactory daoFactory = new DAOFactory("coursedb");
        
        if ( !daoFactory.isClosed() ) {
            System.out.println("Connected Successfully!");
        }
        
    }
    
}