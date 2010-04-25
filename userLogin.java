//a user login class to help the user to login  the system
import java.sql.*;
import java.util.*;
class userLogin{
  private String username;
  private String password;
  private String teamname;
  
  public userLogin(String a,String b)throws Exception{

    username=a;
    password=b;
    teamname="";
    if(correctUser()){
      Class.forName("com.mysql.jdbc.Driver");
      Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
      Statement instruction = con.createStatement();
      ResultSet resultat = instruction.executeQuery("SELECT u.teamname FROM user u where u.username='"+username+"'");
      resultat.next();
      teamname=resultat.getString(1);
      con.close();
    }
  }//if exist user, the program will automatically insert the teamname of the user, therefore, the teamname will be unique for each user
  
  public boolean correctUser()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM user u where u.username='"+username+"' AND u.password='"+password+"'");
    con.close();
    return resultat.next();
  }//check if the user's password is correct

  public boolean existUser()throws Exception{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM user u where u.username='"+username+"'");
    con.close();
    return resultat.next();
  }//check if there exist this user
  
  public boolean register(String u, String p,String t)throws Exception{
    
    userLogin temp=new userLogin(u,p);
    
    if(temp.existUser()){
      return false;
    }
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("INSERT INTO user(username,teamname,password,totalpoints,weekpoints,rank,windata,lossdata) VALUES ('"+u+"','"+t+"','"+p+"',0,0,0,0,0,0)");
    con.close();
    return true;
  }
  
  public String getUser(){
  
    return username;
  }
  
  public String getPass(){
  
    return password;
  }
  
  public ResultSet showMyINF()throws Exception{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM user u where u.username='"+username+"'");
    con.close();
    return resultat;
  }
  
  public ResultSet showMyTeam()throws Exception{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM teamroster t where t.teamname IN (select u.teamname from user u where u.username='"+username+"'");
    con.close();
    return resultat;
  }
  
  public ResultSet showMySchedule()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM schedule s where s.teamname IN (SELECT u.teamname from user u where u.username='"+username+"')");
    con.close();
    return resultat;
  }
  
  public String showMyMatchNow(int time)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM schedule s where s.teamname='"+teamname+"'");
    resultat.next();
    con.close();
    return resultat.getString(time+1);
  }//the time here to be insert is the current week the user state
  
  public ResultSet showMyMatchAll()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM schedule s where s.teamname IN (SELECT u.teamname from user u where u.username='"+username+"')");
    resultat.next();
    con.close();
    return resultat;
  }
  
  public ResultSet showMyPlayersINF()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM players p where p.owner='"+username+"'");
    resultat.next();
    con.close();
    return resultat;
  }
  
  public ResultSet showMyPlayersTTP()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM totalstats t where t.name IN (select p.name from players p where p.owner='"+username+"')");
    resultat.next();
   con.close();
    return resultat;
  }
  
  public ResultSet showMyPlyaersWSP()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM weeklystats w where w.name IN (select p.name from players p where p.owner='"+username+"')");
    resultat.next();
    con.close();
    return resultat;
  }
  
  public void setPosition(String name,String position)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("update teamroster t set t."+position+"='"+name+"' where t.teamname IN (select u.teamname from user u where u.username='"+username+"')");
    con.close();
  }
  
  public void selectPlayers(String name)throws Exception{
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("update players p set p.availability=1,p.owner='"+username+"' where p.name='"+name+"'");
    con.close();
  }
   
  public ResultSet showTeam()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select u.teamname from user u where u.username='"+username+"'");
    con.close();
    return resultat;
  }
  
  public ResultSet showTeamLineUp()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select t.teamname,t.QB,t.RB1,t.RB2,t.WR1,t.WR2,t.WR3,t.TE,t.DEF,t.K from teamroster t where t.teamname='"+teamname+"'");
    con.close();
    return resultat;
  }//* user's starting lineup and bench along with their position, player name, total stats, and total points
  
  public ResultSet showTeamBench()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select t.teamname,t.BN1,t.BN2,t.BN3,t.BN4,t.BN5 from teamroster t where t.teamname='"+teamname+"'");
    con.close();
    return resultat;
  }//* user's starting lineup and bench along with their position, player name, total stats, and total points
  
  public ResultSet showTeamAllPlayer()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("select p.name,p.position,t.* from players p,totalstats t where p.name=t.name AND p.owner='"+username+"'");
    con.close();
    return resultat;
  }//* user's starting c and bench along with their position, player name, total stats, and total points
  
  public ResultSet showOpposingTeam(int currentWeek)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet temp= instruction.executeQuery("select t.week"+currentWeek+" from teamroster t where t.teamname='"+teamname+"'");
    temp.next();
    String opposeTeam=temp.getString(1);
    ResultSet resultat = instruction.executeQuery("select p.name,p.position,t.* from players p,totalstats t where p.name=t.name AND p.owner='"+opposeTeam+"'");
    con.close();
    return resultat;
  }//* the same for the opposing team
  
  public void updateTeamroster(String playerName)throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    instruction.executeQuery("UPDATE players p SET p.availability=0 AND p.owner='"+username+"' WHERE name='"+playerName+"'");
    ResultSet resultat = instruction.executeQuery("SELECT t.* from teamroster t,user u where u.username='"+username+"' AND u.teamname=t.teamname");
    resultat.next();
    String insertValue="INSERT INTO teamroster VAULES ('"+teamname+"'";
    int index;
    for(index=2;index<16;index++){
      if(resultat.getString(index)!="N")
        insertValue+=",'"+resultat.getString(index)+"'";
      else 
        break;
    }
    if(index!=16){
      for(;index<16;++index)
        insertValue+=",'N'";
    }
    insertValue+=")";
    instruction.executeQuery("DELETE FROM teamroster WHERE teamname='"+teamname+"'");
    instruction.executeQuery(insertValue);
    con.close();
  }//* a query to move a player from 'available players' to a team's roster
  
  
}
  