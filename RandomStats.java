import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.*;

public class RandomStats
{
    //RANDOMIZE STATS

    public static void qbstats()
    {
        //Number of QB's
        int numqb = 32;
        
        //Stats for a QB
        int []qbTD;
        int []qbYards;
        int []qbInt;
        
        //Points for QB
        int []qbTDpnts;
        int []qbYardspnts;
        int []qbIntpnts;
        
        //Total points for QB
        int []qbtotal;
        
        
         Random generator = new Random();
        
        //Determine stats for all QBs and the points for those stats
        int q;
        for(q = 0; q <= numqb; q++)
        {
            qbTD[q] = generator.nextInt(5);
            qbYards[q] = generator.nextInt(325);
            qbInt[q] = generator.nextInt(3);
            
            qbTDpnts[q] = qbTD[q] * 6;
            qbYardspnts[q] = (qbYards[q]/25) * 1;
            qbIntpnts[q] = qbInt[q] * (-2);
            
            qbtotal[q] = qbTDpnts[q] + qbYardspnts[q] + qbIntpnts[q];
            
        }
        
        q=0;
        while(q <= numqb)
        {
            updateWeekStatsQB(qbTD[q], qbYards[q], qbInt[q], qbtotal[q]);
            q++;
        }
    }
    
    
    
    public static void rbstats()
    {
        //Number of RB's
        int numrb = 32;
        
        //Stats for a RB
        int []rbTD;
        int []rbYards;
        int []rbFumbles;
        
        //Points for RB
        int []rbTDpnts;
        int []rbYardspnts;
        int []rbFumblespnts;
        
        //Total points for RB
        int []rbtotal;
        
        
         Random generator = new Random();
        
        //Determine stats for all RBs and the points for those stats
        int r;
        for(r = 0; r <= numrb; r++)
        {
            rbTD[r] = generator.nextInt(5);
            rbYards[r] = generator.nextInt(120);
            rbFumbles[r] = generator.nextInt(1);
            
            rbTDpnts[r] = rbTD[r] * 6;
            rbYardspnts[r] = (rbYards[r]/10) * 1;
            rbFumblespnts[r] = rbFumbles[r] * (-2);
            
            rbtotal[r] = rbTDpnts[r] + rbYardspnts[r] + rbFumblespnts[r];
            
        }
        
        r=0;
        while(r <= numrb)
        {
            updateWeekStatsRB(rbTD[r], rbYards[r], rbFumbles[r], rbtotal[r]);
            r++;
        }
    }
    
    
    
    public static void wrstats()
    {
        //Number of WR's
        int numwr = 32;
        
        //Stats for a WR
        int []wrTD;
        int []wrYards;
        
        //Points for WR
        int []wrTDpnts;
        int []wrYardspnts;
        
        //Total points for WR
        int []wrtotal;
        
        
        Random generator = new Random();
        
        //Determine stats for all WRs and the points for those stats
        int w;
        for(w = 0; w <= numwr; w++)
        {
            wrTD[w] = generator.nextInt(3);
            wrYards[w] = generator.nextInt(100);
            
            wrTDpnts[w] = wrTD[w] * 6;
            wrYardspnts[w] = (wrYards[w]/10) * 1;
            
            wrtotal[w] = wrTDpnts[w] + wrYardspnts[w];
            
        }
        
        w=0;
        while(w <= numwr)
        {
            updateWeekStatsWR(wrTD[w], wrYards[w], wrtotal[w]);
            w++;
        }
    }
    
    
    
    public static void testats()
    {
        //Number of TE's
        int numte = 32;
        
        //Stats for a TE
        int []teTD;
        int []teYards;
        
        //Points for TE
        int []teTDpnts;
        int []teYardspnts;
        
        //Total points for TE
        int []tetotal;
        
        
        Random generator = new Random();
        
        //Determine stats for all TE's and the points for those stats
        int t;
        for(t = 0; t <= numte; t++)
        {
            teTD[t] = generator.nextInt(2);
            teYards[t] = generator.nextInt(80);
            
            teTDpnts[t] = teTD[t] * 6;
            teYardspnts[t] = (teYards[t]/10) * 1;
            
            tetotal[t] = teTDpnts[t] + teYardspnts[t];
            
        }
        
        t=0;
        while(t <= numte)
        {
            updateWeekStatsTE(teTD[t], teYards[t], tetotal[t]);
            t++;
        }
    }
    
    
    
    public static void defstats()
    {
        //Number of DEF's
        int numdef = 32;
        
        //Stats for a DEF
        int []pntsallowed;
        int []turnovers;
        int []sacks;
        int []defTD;
        
        //Points for DEF
        int []pntsallowedpnts;
        int []turnoverspnts;
        int []sackspnts;
        int []defTDpnts;
        
        //Total points for DEF
        int []deftotal;
        
        
        Random generator = new Random();
        
        //Determine stats for all DEFs and the points for those stats
        int d;
        for(d = 0; d <= numdef; d++)
        {
            turnovers[d] = generator.nextInt(3);
            sacks[d] = generator.nextInt(3);
            defTD[d] = generator.nextInt(1);
            pntsallowed[d] = generator.nextInt(43) + 2;
            
            turnoverspnts[d] = turnovers[d] * 2;
            sackspnts[d] = sacks[d] * 2;
            defTDpnts[d] = defTD[d] * 6;
            
            if(pntsallowed[d] < 10)
            {
                pntsallowedpnts[d] = 4;
            }
            if(pntsallowed[d] >= 10 && pntsallowed[d] < 20)
            {
                pntsallowedpnts[d] = 2;
            }
            if(pntsallowed[d] >= 20 && pntsallowed[d] < 30)
            {
                pntsallowedpnts[d] = 0;
            }
            if(pntsallowed[d] >= 30 && pntsallowed[d] < 40)
            {
                pntsallowedpnts[d] = -2;
            }
            if(pntsallowed[d] >= 40)
            {
                pntsallowedpnts[d] = -4;
            }
            
            
            deftotal[d] = turnoverspnts[d] + sackspnts[d] + defTDpnts[d] + pntsallowedpnts[d];
            
        }
        
        d=0;
        while(d <= numdef)
        {
            updateWeekStatsDEF(turnovers[d], sacks[d], defTD[d], pntsallowed[d], deftotal[d]);
            d++;
        }
    }
    
    
    
    public static void kstats()
    {
        //Number of K's
        int numk = 32;
        
        //Stats for K
        int []lessfourtyFG;
        int []greatfourtyFG;
        int []lessFGmiss;
        int []greatFGmiss;
        int []PAT;
        int []missPAT;
        
        //Points for K
        int []lessfourtyFGpnts;
        int []greatfourtyFGpnts;
        int []lessFGmisspnts;
        int []greatFGmisspnts;
        int []PATpnts;
        int []missPATpnts;
        
        //Total points for K
        int []ktotal;
        
        
        Random generator = new Random();
        
        //Determine stats for all Ks and the points for those stats
        int k;
        for(k = 0; k <= numk; k++)
        {
            lessfourtyFG[k] = generator.nextInt(2);
            greatfourtyFG[k] = generator.nextInt(1);
            lessFGmiss[k] = generator.nextInt(1);
            greatFGmiss[k] = generator.nextInt(1);
            PAT[k] = generator.nextInt(4);
            missPAT[k] = generator.nextInt(1);
            
            lessfourtyFGpnts[k] = lessfourtyFG[k] * 3;
            greatfourtyFGpnts[k] = greatfourtyFG[k] * 4;
            lessFGmisspnts[k] = lessFGmiss[k] * (-2);
            greatFGmisspnts[k] = greatFGmiss[k] * (-1);
            PATpnts[k] = PAT[k] * 1;
            missPATpnts[k] = missPAT[k] * (-1);
            
            ktotal[k] = lessfourtyFGpnts[k] + greatfourtyFGpnts[k] + lessFGmisspnts[k] + greatFGmisspnts[k] + PATpnts[k] + missPATpnts[k];
        }
        
        k=0;
        while(k <= numk)
        {
            updateWeekStatsK(lessfourtyFG[k], greatfourtyFG[k], lessFGmiss[k], greatFGmiss[k], PAT[k], missPAT[k], ktotal[k]);
            k++;
        }
    }
    
    
    
    
    //UPDATE WEEKLY STATS FOR PLAYERS
    
    public static void updateWeekStatsQB(int td, int yds, int intcp, int points)
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET passTD="+td+",passYards="+yds+",interceptions="+intcp+", calpoints="+points+" WHERE (SELECT QB FROM weeklystats)" );
    }
    
    public static void updateWeekStatsRB(int td, int yds, int fmbl, int points)
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET rushTD="+td+",rushYards="+yds+",fumbles="+fmbl+", calpoints="+points+" WHERE (SELECT RB FROM weeklystats)" );
    }
    
    public static void updateWeekStatsWR(int td, int yds, int points)
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET recTD="+td+",recYards="+yds+", calpoints="+points+" WHERE (SELECT WR FROM weeklystats)" );
    }
    
    public static void updateWeekStatsTE(int td, int yds, int points)
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET recTD="+td+",recYards="+yds+", calpoints="+points+" WHERE (SELECT TE FROM weeklystats)" );
    }
    
    public static void updateWeekStatsDEF(int to, int sack, int td, int allow, int points)
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET turnovers="+to+",sacks="+sack+", defTD="+td+", pntsallowed="+allow+", calpoints="+points+" WHERE (SELECT DEF FROM weeklystats)" );
    }
    
    public static void updateWeekStatsK(int l40, int g40, int ml40, int mg40, int pat, int mpat, int points)
    {
        Class.forName("com.mysql.jdbc.Driver");
        //DON'T FORGET TO PUT THE USERNAME AND PASS FOR YOUR DRIVER CONNECTION TO THE DATABASE BELOW
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/");
        Statement instruction = conn.createStatement();
        //NEED TO FIGURE OUT HOW TO UPDATE ROW BY ROW
        ResultSet result = instruction.executeQuery("UPDATE weeklystats w SET fgless40="+l40+", fggreat40="+g40+", missfgless40="+ml40+", missfggreat40="+mg40+", PAT="+pat+", missPAT="+mpat+", calpoints="+points+" WHERE (SELECT K FROM weeklystats)" );
    }

}
