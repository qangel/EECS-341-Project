//a user login class to help the user to login  the system
import java.sql.*;
import java.util.*;
class userLogin{
  private String username;
  private String password;
  private String teamname;
  
  public userLogin(String a,String b){

    username=a;
    password=b;
    teamname="";
  }

  public boolean existUser()throws Exception{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM user u where u.username='"+username+"' AND u.password='"+password+"'");
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
    ResultSet resultat = instruction.executeQuery("SELECT * FROM schedule s where s.teamname IN (SELECT u.teamname from user u where u.username='"+username+"')");
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
  
}
  