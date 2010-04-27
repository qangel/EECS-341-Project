import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.*;

public class InjuryStatus
{
    public static void injury()
    {
        int numplayers = 192;
        int status;
        int play;
        char injuryarray[numplayers];
        
        Random generator = new Random();
        
        int i = 0;
        while(i <= numplayers)
        {
            status = generator.nextInt(100);
            
            if(status < 75)
            {
                injuryarray[i] = "A";
            }
            else if(status >= 75 && status < 85)
            {
                injuryarray[i] = "P";
                play = generator.nextINT(10);
                if(play > 8)
                {
                    if(p.position = "QB")
                    {
                        updateWeekStatsQB(0, 0, 0, 0);
                    }
                    if(p.position = "RB")
                    {
                        updateWeekStatsRB(0, 0, 0, 0);
                    }
                    //etc......
            }
            else if(status >= 85 && < 95)
            {
                injuryarray[i] = "Q";
                play = generator.nextINT(10);
                if(play > 4)
                {
                    if(p.position = "QB")
                    {
                        updateWeekStatsQB(0,0,0,0);
                    }
                    //etc...
                }   
            }
            else
            {
                injuryarray[i] = "O";
                if(p.position = "QB")
                {
                    updateWeekStatsQB(0,0,0,0);
                }
                //etc....
            }
        }
        
        updateInjury(injuryarray);
        
    }
    
    public static void updateInjury(char inj[])
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DONT FORGET TO PUT THE USERNAME AND PASS FOR DRIVER CONNECTION TO DATABASE
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOT TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE players p SET injury="+inj+"");
    }
        
        
        
            
        
        
}
