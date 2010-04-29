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
    ResultSet resultat = instruction.executeQuery("SELECT * FROM teamroster t where t.teamname='"+teamname+"'");
    con.close();
    return resultat;
  }
  
  public ResultSet showMySchedule()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet resultat = instruction.executeQuery("SELECT * FROM schedule s where s.teamname ='"+teamname+"')");
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
    ResultSet resultat = instruction.executeQuery("SELECT * FROM schedule s where s.teamname ='"+teamname+"')");
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
    ResultSet resultat = instruction.executeQuery("update teamroster t set t."+position+"='"+name+"' where t.teamname='"+teamname+"')");
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
  
  public void randomStats()throws Exception{
  
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost/jlj?user=root");
    Statement instruction = con.createStatement();
    ResultSet temp= instruction.executeQuery("select * from teamroster t where t.teamname='"+teamname+"'");
    temp.next();
    for(int i=2;i<16;++i){
    String getName=temp.getString(i);
    determineRandom(getName,i);
    }
    con.close();
  }
  
  private void determineRandom(String name,int i)throws Exception{
  
  if(i==2)
    qbstats(name);
  else if(i==3 || i==4)
    rbstats(name);
  else if(i==5||i==6||i==7)
    wrstats(name);
  else if(i==8)
    testats(name);
  else if(i==9)
    defstats(name);
  else if(i==10)
    kstats(name);
  }
  
 //all below is RANDOMIZE STATS

  private void qbstats(String name)throws Exception
    {
      
        //Stats for a QB
        int qbTD;
        int qbYards;
        int qbInt;
        
        //Points for QB
        int qbTDpnts;
        int qbYardspnts;
        int qbIntpnts;
        
        //Total points for QB
        int qbtotal;
        
        
         Random generator = new Random();
        
        //Determine stats for all QBs and the points for those stats
         
            qbTD = generator.nextInt(5);
            qbYards = generator.nextInt(325);
            qbInt = generator.nextInt(3);
            
            qbTDpnts = qbTD * 6;
            qbYardspnts = (qbYards/25) * 1;
            qbIntpnts = qbInt * (-2);
            
            qbtotal = qbTDpnts + qbYardspnts + qbIntpnts;
 
            updateWeekStatsQB(qbTD, qbYards, qbInt, qbtotal,name);
    }
    
    
    
    private void rbstats(String name)throws Exception
    {

        //Stats for a RB
        int rbTD;
        int rbYards;
        int rbFumbles;
        
        //Points for RB
        int rbTDpnts;
        int rbYardspnts;
        int rbFumblespnts;
        
        //Total points for RB
        int rbtotal;
        
        
         Random generator = new Random();
        
        //Determine stats for all RBs and the points for those stats

            rbTD = generator.nextInt(5);
            rbYards = generator.nextInt(120);
            rbFumbles = generator.nextInt(1);
            
            rbTDpnts = rbTD * 6;
            rbYardspnts = (rbYards/10) * 1;
            rbFumblespnts = rbFumbles * (-2);
            
            rbtotal = rbTDpnts + rbYardspnts + rbFumblespnts;

            updateWeekStatsRB(rbTD, rbYards, rbFumbles, rbtotal,name);
    }
    
    
    
    private void wrstats(String name)throws Exception
    {

        //Stats for a WR
        int wrTD;
        int wrYards;
        
        //Points for WR
        int wrTDpnts;
        int wrYardspnts;
        
        //Total points for WR
        int wrtotal;
        
        
        Random generator = new Random();
        
        //Determine stats for all WRs and the points for those stats

            wrTD = generator.nextInt(3);
            wrYards = generator.nextInt(100);
            
            wrTDpnts = wrTD * 6;
            wrYardspnts = (wrYards/10) * 1;
            
            wrtotal = wrTDpnts + wrYardspnts;

            updateWeekStatsWR(wrTD, wrYards, wrtotal,name);
    }
    
    
    
    private void testats(String name)throws Exception
    {

        //Stats for a TE
        int teTD;
        int teYards;
        
        //Points for TE
        int teTDpnts;
        int teYardspnts;
        
        //Total points for TE
        int tetotal;
        
        
        Random generator = new Random();
        

            teTD = generator.nextInt(2);
            teYards = generator.nextInt(80);
            
            teTDpnts = teTD * 6;
            teYardspnts = (teYards/10) * 1;
            
            tetotal = teTDpnts + teYardspnts;

            updateWeekStatsTE(teTD, teYards, tetotal,name);
 
    }
    
    
    
    private void defstats(String name)throws Exception
    {

        //Stats for a DEF
        int pntsallowed;
        int turnovers;
        int sacks;
        int defTD;
        
        //Points for DEF
        int pntsallowedpnts=0;
        int turnoverspnts;
        int sackspnts;
        int defTDpnts;
        
        //Total points for DEF
        int deftotal;
        
        
        Random generator = new Random();
        
        //Determine stats for all DEFs and the points for those stats
            turnovers = generator.nextInt(3);
            sacks = generator.nextInt(3);
            defTD = generator.nextInt(1);
            pntsallowed = generator.nextInt(43) + 2;
            
            turnoverspnts = turnovers * 2;
            sackspnts = sacks * 2;
            defTDpnts = defTD * 6;
            
            if(pntsallowed < 10)
            {
                pntsallowedpnts = 4;
            }
            if(pntsallowed >= 10 && pntsallowed < 20)
            {
                pntsallowedpnts = 2;
            }
            if(pntsallowed >= 20 && pntsallowed < 30)
            {
                pntsallowedpnts = 0;
            }
            if(pntsallowed >= 30 && pntsallowed < 40)
            {
                pntsallowedpnts = -2;
            }
            if(pntsallowed >= 40)
            {
                pntsallowedpnts = -4;
            }
       
            deftotal = turnoverspnts + sackspnts + defTDpnts + pntsallowedpnts;

            updateWeekStatsDEF(turnovers, sacks, defTD, pntsallowed, deftotal,name);

    }
    
    
    
    private void kstats(String name)throws Exception
    {

        //Stats for K
        int lessfourtyFG;
        int greatfourtyFG;
        int lessFGmiss;
        int greatFGmiss;
        int PAT;
        int missPAT;
        
        //Points for K
        int lessfourtyFGpnts;
        int greatfourtyFGpnts;
        int lessFGmisspnts;
        int greatFGmisspnts;
        int PATpnts;
        int missPATpnts;
        
        //Total points for K
        int ktotal;
        
        
        Random generator = new Random();

            lessfourtyFG = generator.nextInt(2);
            greatfourtyFG = generator.nextInt(1);
            lessFGmiss = generator.nextInt(1);
            greatFGmiss = generator.nextInt(1);
            PAT = generator.nextInt(4);
            missPAT = generator.nextInt(1);
            
            lessfourtyFGpnts = lessfourtyFG * 3;
            greatfourtyFGpnts = greatfourtyFG * 4;
            lessFGmisspnts = lessFGmiss * (-2);
            greatFGmisspnts = greatFGmiss * (-1);
            PATpnts = PAT * 1;
            missPATpnts = missPAT * (-1);
            
            ktotal = lessfourtyFGpnts+ greatfourtyFGpnts + lessFGmisspnts + greatFGmisspnts + PATpnts + missPATpnts;

            updateWeekStatsK(lessfourtyFG, greatfourtyFG, lessFGmiss, greatFGmiss, PAT, missPAT, ktotal,name);

    }
    
    
    
    
    //UPDATE WEEKLY STATS FOR PLAYERS
    
    public void updateWeekStatsQB(int td, int yds, int intcp, int points,String playerName)throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET w.passTD="+td+",w.passYards="+yds+",w.interceptions="+intcp+",w.calpoints="+points+" WHERE w.name='"+playerName+"'" );
        conn.close();
    }
    
    public void updateWeekStatsRB(int td, int yds, int fmbl, int points,String playerName)throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET w.rushTD="+td+",w.rushYards="+yds+",w.fumbles="+fmbl+",w.calpoints="+points+" WHERE w.name='"+playerName+"'");
        conn.close();
    }
    
    public void updateWeekStatsWR(int td, int yds, int points,String playerName)throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET w.receivingTD="+td+",w.receivingYards="+yds+",w.calpoints="+points+" WHERE w.name='"+playerName+"'");
        conn.close();
    }
    
    public void updateWeekStatsTE(int td, int yds, int points,String playerName)throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET w.receivingTD="+td+",w.receivingYards="+yds+",w.calpoints="+points+" WHERE w.name='"+playerName+"'");
        conn.close();
    }
    
    public void updateWeekStatsDEF(int to, int sack, int td, int allow, int points,String playerName)throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET w.turnovers="+to+",w.sacks="+sack+",w.defensiveTD="+td+",w.pointsallowed="+allow+", calpoints="+points+" w.name='"+playerName+"'");
        conn.close();
    }
    
    public void updateWeekStatsK(int l40, int g40, int ml40, int mg40, int pat, int mpat, int points,String playerName)throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET w.fieldgoalless40="+l40+",w.fieldgoalgreater40="+g40+",w.missedfieldgoaless40="+ml40+",w.missedfieldgoalgreater40="+mg40+",w.PAT="+pat+",w.missPAT="+mpat+",w.calpoints="+points+" WHERE w.name='"+playerName+"'");
        conn.close();
    }
    
    public boolean compareWin(String otherUserName)throws Exception{
    
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet resultA = instruction.executeQuery("select w.name,w.calpoints from weeklystats w where w.name IN (select p.name from players p where p.owner='"+username+"'");
        ResultSet resultB = instruction.executeQuery("select w.name,w.calpoints from weeklystats w where w.name IN (select p.name from players p where p.owner='"+otherUserName+"'");
        double calpointA=0;
        double calpointB=0;
        while(resultA.next()){
        
          calpointA+=resultA.getDouble(2);
        }
        
        while(resultB.next()){
        
          calpointB+=resultB.getDouble(2);
        }
        
        
        conn.close();
        if(calpointA<calpointB)
          return true;//true means this user wins
        else 
          return false;//false means other wins
    }//a new method to compare the winner between this user and the other user, remember the other input is username instead of teamname
  
  
}
  