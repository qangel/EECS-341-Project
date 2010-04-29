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
    makeSchedule();
    File file=new File("start.html");
    PrintWriter output=new PrintWriter(file);
    output.print("");//put html code here and if you wish put the output.print() as more as possible
    
    output.close();
     con.close();
  }
  
 /* public static void loginPage(userLogin e)throws Exception{
  
    if(e.existUser){
    //goto the user page
    }
    else {
      //goto other fault page
    }
      
  }*/
  
  public static void randomSick()throws Exception{
  
    
  }
  
  public static boolean makeSchedule()throws Exception{//make the schedule for our schedule table

    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT u.username from user u order by u.lossdata");
    resultat.next();
    ArrayList<String> userResult=new ArrayList<String>();
    while(resultat.next()){
      userResult.add(resultat.getString(1));}
    int n=userResult.size();
    
    if(n==0)
      return false;
    
    int [][] matchTable;
    if(userResult.size()%2!=0){
      n++;
      matchTable=doSchedule(n);
    }
    else
      matchTable=doSchedule(n);
    
    instruction.executeQuery("DROP TABLE IF EXISTS schedule");
    String excuteString="CREATE TABLE schedule ( username CHAR(20) NOT NULL, ";
    for(int i=0;i<n;++i)
        excuteString+="week"+(i+1)+" CHAR(20), ";
    
    excuteString+="PRIMARY KEY (username), FOREIGN KEY (username) REFERENCES user (username))";
    instruction.executeQuery(excuteString);
    
    if(userResult.size()!=n){
      
      for(int i=0;i<n;++i){
        String theString="INSERT INTO schedule VALUES ("+userResult.get(i);
        for(int j=0;j<n-1;++j){
           if(matchTable[i][j]==userResult.size())
             theString+=",break";
           else
             theString+=","+userResult.get(matchTable[i][j]);
         }
         theString+=")";
         instruction.executeQuery(theString);
        }
      }
    else {
      
      for(int i=0;i<n;++i){
        String theString="INSERT INTO schedule VALUES ("+userResult.get(i);
        for(int j=0;j<n-1;++j){
             theString+=","+userResult.get(matchTable[i][j]);
         }
         theString+=")";
         instruction.executeQuery(theString);
        }
    }
    con.close();
    return true;
  }
 
  private static int[][] doSchedule(int n){
    
    if(n==2){
      int[][] theTable=new int[2][1];
      theTable[0][0]=1;
      theTable[1][0]=0;
       return theTable;
    }
    
    int[][] matchSet=new int[n-1][n];
    for(int i=0;i<n-1;++i)
      matchSet[i][0]=n-1;
    matchSet[0][1]=0;
    
    int factor=1;
    for(int i=2;i<n;i=i+2){
      matchSet[0][i]=factor;
      factor++;
    }
    for(int i=n-1;i>=3;i=i-2){
      matchSet[0][i]=factor;
        factor++;
    }
    
    ArrayList<Factor> mySet=new ArrayList<Factor>();

    for(int k=1;k<n-1;++k){
    for(int i=n-2;i>1;i=i-2){
       mySet.add(new Factor(matchSet[k-1][i-1],matchSet[k-1][i]));
    }
     matchSet[k][1]=matchSet[k-1][n-1];
     for(int i=2;i<n;i=i+2){
       matchSet[k][i]=mySet.get(0).one;
       matchSet[k][i+1]=mySet.get(0).two;
       mySet.remove(0);
     }
     mySet.clear();
    }
    
    int[][] matchTable=new int[n][n-1];
    
    for(int i=0;i<n-1;++i)
      for(int j=0;j<n;j=j+2){
         matchTable[matchSet[i][j]][i]=matchSet[i][j+1];
         matchTable[matchSet[i][j+1]][i]=matchSet[i][j];
      }

    return matchTable;
  }

  public static ResultSet showAllSchedule()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * from schedule");
    con.close();
    return resultat;
  }
  
  public static ResultSet showAvaPlayers()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * from players p where p.availability=0");// change all the user exist now to the modes 1 which is play mode
    con.close();
    return resultat;
  }
  
  
  public static ResultSet showAllPlayers()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * from players");
    con.close();
    return resultat;
  }
  
  public static ResultSet updateWeekStats(String name,double passTD,double passyards,double interceptions,double rushTD,double rushyards,double fumbles,double receivingTD,double receivingyards,double pointsallowed,double turnovers,double sacks,double defensiveTD,double fl40,double fg40,double ml40,double mg40,double pat,double mpat)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    double total=passTD+passyards+interceptions+rushTD+rushyards+fumbles+receivingTD+receivingyards+pointsallowed+turnovers+sacks+defensiveTD+fl40+fg40+ml40+mg40+pat+mpat;
    ResultSet resultat = instruction.executeQuery("UPDATE weeklystats w SET passTD="+passTD+",passyards="+passyards+",interceptions="+interceptions+",rushTD="+rushTD+",rushyards="+rushyards+",fumbles="+fumbles+",receivingTD="+receivingTD+",receivingyards="+receivingyards+",pointsallowed="+pointsallowed+",turnovers="+turnovers+",sacks="+sacks+",defensiveTD="+defensiveTD+",fieldgoalless40="+fl40+",fieldgoalgreater40="+fg40+",missedfieldgoaless40="+ml40+",missedfieldgoalgreater40="+mg40+",PAT="+pat+",missedPAT="+mpat+",calpoints="+total+" where w.name='"+name+"'");//
    con.close();
    return resultat;
  }
  
  public static ResultSet showRank()throws Exception{
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT u.teamname,u.windata FROM user u order by u.lossdata");
    con.close();
    return resultat;
  }
  
  public static ResultSet showWin(String user1,String user2)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat =instruction.executeQuery("SELECT u.username,u.windata from user u order by u.lossdata");
    con.close();
    return resultat;
  }
  
  public static void startNextWeek(int thisWeek)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    instruction.executeQuery("update totalstats t,weeklystats w set t.passTD=t.passTD+w.passTD,t.passyards=t.passyards+w.passyards,t.interceptions=t.interceptions+w.interceptions,t.rushTD=t.rushTD+w.rushTD,t.rushyards=t.rushyards+w.rushyards,t.fumbles=t.fumbles+w.fumbles,t.receivingTD=t.receivingTD+w.receivingTD,t.receivingyards=t.receivingyards+w.receivingyards,t.pointsallowed=t.pointsallowed+w.pointsallowed,t.turnovers=t.turnovers+w.turnovers,t.sacks=t.sacks+w.sacks,t.defensiveTD=t.defensiveTD+w.defensiveTD,t.fieldgoalless40=t.fieldgoalless40+w.fieldgoalless40,t.fieldgoalgreater40=t.fieldgoalgreater40+w.fieldgoalgreater40,t.missedfieldgoaless40=t.missedfieldgoaless40+w.missedfieldgoaless40,t.missedfieldgoalgreater40=t.missedfieldgoalgreater40+w.missedfieldgoalgreater40,t.PAT=t.PAT+w.PAT,t.missedPAT=t.missedPAT+w.missedPAT,t.calpoints=t.calpoints+w.calpoints where t.name=w.name");
    calWin(thisWeek);
    calRank();
    con.close();
  }
  
  public static void setInjury(String name,String types)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("update players p set p.injury='"+types+"' where p.name='"+name+"'");
    con.close();
  }
  
  //to let the system to determine which people will go first in the selection
  public static ResultSet determineSelection()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select u.username from user u oreder by lossdata");
    con.close();
    return resultat;
  }
  
  //calculate all the winner in each week.
  public static void calWin(int thisWeek)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultS = instruction.executeQuery("select s.username,s.week"+thisWeek+" from schedule");
    ResultSet resultT = instruction.executeQuery("select * from schedule");
    int totalTeam=(resultT.getMetaData().getColumnCount()-1)/2;
    while(resultS.next()){
    
      String first=resultS.getString(1);
      String second=resultS.getString(2);
      ResultSet resultA = instruction.executeQuery("select distinct w.name,w.calpoints from weeklystats w where w.name IN (select p.name from players p where p.owner='"+first+"'");
      ResultSet resultB = instruction.executeQuery("select distinct w.name,w.calpoints from weeklystats w where w.name IN (select p.name from players p where p.owner='"+second+"'");
      double calpointA=0;
      double calpointB=0;
      while(resultA.next()){
        
         calpointA+=resultA.getDouble(2);
      }
       
      while(resultB.next()){
        
        calpointB+=resultB.getDouble(2);
      }
      
      if(thisWeek>totalTeam){
      
        if(calpointA>=calpointB){
        
          instruction.executeQuery("update user u SET u.windata=u.windata+1 where u.username='"+first+"'");
          instruction.executeQuery("update user u SET u.lossdata=u.lossdata+1 where u.username='"+second+"'");
        }
        else {
        
          instruction.executeQuery("update user u SET u.windata=u.windata+1 where u.username='"+second+"'");
          instruction.executeQuery("update user u SET u.lossdata=u.lossdata+1 where u.username='"+first+"'");
        }
      }
      else {
      
        if(calpointA>calpointB){
        
          instruction.executeQuery("update user u SET u.windata=u.windata+1 where u.username='"+first+"'");
          instruction.executeQuery("update user u SET u.lossdata=u.lossdata+1 where u.username='"+second+"'");
        }
        else {
        
          instruction.executeQuery("update user u SET u.windata=u.windata+1 where u.username='"+second+"'");
          instruction.executeQuery("update user u SET u.lossdata=u.lossdata+1 where u.username='"+first+"'");
        }
      }
    }
    
    con.close();
  }//calculate the winner of specific roster
  
  public static void calRank()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultAt = instruction.executeQuery("select u.username from user u order by u.windata,u.rank");
    int rankIndex=1;
    instruction.executeQuery("update user u SET u.rank=null");
    while(resultAt.next()){
      String temp=resultAt.getString(1);
      instruction.executeQuery("update user u SET u.rank="+rankIndex+" where u.username='"+temp+"'");
      rankIndex++;
    }
    con.close();
  }
  
  public static ResultSet rankTeams()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select u.username,u.teamname,u.windata,u.lossdata from user u order by u.rank");
    con.close();
    return resultat;
    
  }//* all of the team names in the league with their respective win/los record, and rank them in order
  
  public static ResultSet weeklyMatchUp(int currentWeek)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    String getWeek="week"+currentWeek;
    ResultSet resultat = instruction.executeQuery("select s."+getWeek+" from schedule s");
    con.close();
    return resultat;
    
  }//* the weekly matchups for the current week
  
  public static ResultSet showAllPlayerSimple()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select p.name,p.position,p.nflteam from player p where p.availability=1");
    con.close();
    return resultat;
    
  }//* player name, position and team for all available players, I just put their nflteeam for all available players
    
  public static void clearTeamroster(int totalTeam)throws Exception{
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select * from teamroster");
    String halfValue="";
    for(int i=0;i<(totalTeam-1)*2;++i)
       halfValue+=",N";
    halfValue=halfValue+")";
    while(resultat.next()){
    
      String theName=resultat.getString(1);
      String wholeValue="values ("+theName+halfValue;
      instruction.executeQuery("DELETE FROM teamroster where teamname='"+theName+"'");
      instruction.executeQuery("INSERT INTO teamroster "+wholeValue);
    }
    con.close();
    }// a new function to help clear all the Teamroster in the new session to insert all posion as N
  
  public static ResultSet draftOrder()throws Exception{
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select u.username,u.teamname from user u order by u.lossdata");
    con.close();
    return resultat;
  }//* the draft order of teams in the league, and which team's turn it is
  
  public static void initialWeeklystats()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("update weeklystats set passTD=0,passyards=0,interceptions=0,rushTD=0,rushyards=0,fumbles=0,receivingTD=0,receivingyards=0,pointsallowed=0,turnovers=0,sacks=0,defensiveTD=0,fieldgoalless40=0,fieldgoalgreater40=0,missedfieldgoaless40=0,missedfieldgoalgreater40=0,PAT=0,missedPAT=0,calpoints=0");
    con.close();
  }//put all weeklystats to zero
  
  public static void initialtotalstats()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("update totalstats set passTD=0,passyards=0,interceptions=0,rushTD=0,rushyards=0,fumbles=0,receivingTD=0,receivingyards=0,pointsallowed=0,turnovers=0,sacks=0,defensiveTD=0,fieldgoalless40=0,fieldgoalgreater40=0,missedfieldgoaless40=0,missedfieldgoalgreater40=0,PAT=0,missedPAT=0,calpoints=0");
    con.close();
  }
  
  public static void randomInjuryStatus()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT name from player");
    while(resultat.next()){
    
      String temp=resultat.getString(1);
      double dick=Math.random()*10;
      if(dick<1){
      
        ResultSet tempResult=instruction.executeQuery("SELECT p.name,p.injurystate from player p where p.name='"+temp+"'");
        tempResult.next();
        if(tempResult.getString(2).equals("Q"))
          instruction.executeQuery("update player p SET p.injurystate='O' where p.name='"+temp+"'");
        else 
          instruction.executeQuery("update player p SET p.injurystate='Q' where p.name='"+temp+"'");
      }
    }
    
    con.close();
  }//random assign injury to each people
}