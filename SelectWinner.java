import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.*;


public class SelectWinner
{

    public static void winner()
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        ResultSet result = instruction.executeQuery("SELECT teamname FROM user u");
        
        //NOT SURE HOW TO DO THIS
        while(u.teamname)
        {
            teampnts(u.teamname);
        }
    }
    
    
    public static void teampnts(String team)
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        ResultSet result1 = instruction.executeQuery("SELECT calpoints FROM players p WHERE owner="+team+"");
        
        //HOW DO YOU TAKE VALUE FROM EACH ROW AND ADD TOGETHER?????
        //sum of "calpoints" of players owned by "team" = "userpoints"
        
        ResultSet result2 = intruction.executeQuery("UPDATE user u SET weekpoints="+userpoints+"");
    }
        
}
