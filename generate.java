//put all the method to generate the HTML for user
import java.sql.*;
import java.util.*;
import java.io.*;
class generate{

  public static void startGame()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("update user u set u.modes=1 where u.modes=0");// change all the user exist now to the modes 1 which is play mode
    setSchedule();
    File file=new File("start.html");
    PrintWriter output=new PrintWriter(file);
    output.print("");//put html code here and if you wish put the output.print() as more as possible
    
    output.close();
  }
  
 /* public static void loginPage(userLogin e)throws Exception{
  
    if(e.existUser){
    //goto the user page
    }
    else {
      //goto other fault page
    }
      
  }*/
  
  public static void setSchedule()throws Exception{
  
    
  }//insert the schedule data to mysql
  
  public static void makeSchedule(int n,int[][] a){

   doSchedule(n,a);
    
  }
 
  public static void doSchedule(int n,int[][] a){
  
    if(n==1){
    
      a[0][0]=0;
      return;
    }
    if(n%2==1){
    
      doSchedule(n+1,a);
      return;
    }

    doSchedule(n/2,a);
    
    copySchedule(n,a);
  }
  
  public static void copySchedule(int n,int[][] a){
  
    if(n/2>1 && (n/2)%2==1)
      copyOdd(n,a);
    else
      copy(n,a);
    
  }
  public static void copy(int n,int[][] a){

   int m=n/2;

   for(int i=0;i<m;++i)
      for(int j=0;j<m;++j){

      a[i][j+m]=a[i][j]+m;
 
      a[i+m][j]=a[i][j+m];

      a[i+m][j+m]=a[i][j];
    }
  }
  public static void copyOdd(int n,int[][] a){
  
      int m=n/2;
      int b[]=new int[n];

      for(int i=0;i<m;++i){

         b[i]=m+i;
         b[m+i]=b[i];
      }
       

      for(int i=0;i<m;++i){
         for(int j=0;j<m+1;++j){
         if(a[i][j]>m){
            a[i][j]=b[i];
            a[m+i][j]=(b[i]+m)%n;
         }
         else{
           a[m+i][j]=a[i][j]+m;}
       }
       for(int j=1;j<m;++j){

          a[i][m+j]=b[i+j];

           a[b[i+j]][m+j]=i;
       }
      }
  }

  public static ResultSet showAllSchedule()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * from schedule");
    return resultat;
  }
  
  public static ResultSet showAvaPlayers()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * from players p where p.availability=0");// change all the user exist now to the modes 1 which is play mode
    return resultat;
  }
  
  
  public static ResultSet showAllPlayers()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * from players");
    return resultat;
  }
  
  public static ResultSet updateWeekStats(String name,double passTD,double passyards,double interceptions,double rushTD,double rushyards,double fumbles,double receivingTD,double receivingyards,double pointsallowed,double turnovers,double sacks,double defensiveTD,double fl40,double fg40,double ml40,double mg40,double pat,double mpat)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    double total=passTD+passyards+interceptions+rushTD+rushyards+fumbles+receivingTD+receivingyards+pointsallowed+turnovers+sacks+defensiveTD+fl40+fg40+ml40+mg40+pat+mpat;
    ResultSet resultat = instruction.executeQuery("UPDATE weeklystats w SET passTD="+passTD+",passyards="+passyards+",interceptions="+interceptions+",rushTD="+rushTD+",rushyards="+rushyards+",fumbles="+fumbles+",receivingTD="+receivingTD+",receivingyards="+receivingyards+",pointsallowed="+pointsallowed+",turnovers="+turnovers+",sacks="+sacks+",defensiveTD="+defensiveTD+",fieldgoalless40="+fl40+",fieldgoalgreater40="+fg40+",missedfieldgoaless40="+ml40+",missedfieldgoalgreater40="+mg40+",PAT="+pat+",missedPAT="+mpat+",calpoints="+total+" where w.name='"+name+"'");//
    return resultat;
  }
  
  public static ResultSet showRank()throws Exception{
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT u.teamname,u.windata FROM user u order by u.lossdata");
    return resultat;
  }
  
  public static ResultSet showWin(String user1,String user2)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT c.teamname,u.windata FROM user u order by u.lossdata");
    return resultat;
  }
  
  public static void startNextWeek()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    instruction.executeQuery("update totalstats t,weeklystats w set t.passTD=t.passTD+w.passTD,t.passyards=t.passyards+w.passyards,t.interceptions=t.interceptions+w.interceptions,t.rushTD=t.rushTD+w.rushTD,t.rushyards=t.rushyards+w.rushyards,t.fumbles=t.fumbles+w.fumbles,t.receivingTD=t.receivingTD+w.receivingTD,t.receivingyards=t.receivingyards+w.receivingyards,t.pointsallowed=t.pointsallowed+w.pointsallowed,t.turnovers=t.turnovers+w.turnovers,t.sacks=t.sacks+w.sacks,t.defensiveTD=t.defensiveTD+w.defensiveTD,t.fieldgoalless40=t.fieldgoalless40+w.fieldgoalless40,t.fieldgoalgreater40=t.fieldgoalgreater40+w.fieldgoalgreater40,t.missedfieldgoaless40=t.missedfieldgoaless40+w.missedfieldgoaless40,t.missedfieldgoalgreater40=t.missedfieldgoalgreater40+w.missedfieldgoalgreater40,t.PAT=t.PAT+w.PAT,t.missedPAT=t.missedPAT+w.missedPAT,t.calpoints=t.calpoints+w.calpoints where t.name=w.name");
    calWin();
  }
  
  public static void setInjury(String name,String types)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("update players p set p.injury='"+types+"' where p.name='"+name+"'");
  }
  
  //to let the system to determine which people will go first in the selection
  public static ResultSet determineSelection()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select u.username from user u oreder by lossdata");
    return resultat;
  }
  
  public static void calWin()throws Exception{}
  
  
}