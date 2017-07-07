/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woflieballdraftkit.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wolfieballdraftkit.DecimalFormatter;

import wolfieballdraftkit.gui.PlayersScreen;
import wolfieballdraftkit.gui.TeamsScreen;

/**
 *
 * @author Louis
 */
public class Team
{
    
    public String name;
    public StringProperty teamName;
    public String owner;
    public ArrayList<String> positions;
    public ObservableList <Player> players;

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ObservableList<Player> players) {
        this.players = players;
    }
    //taxi squad is 8 players
    public ObservableList <Player> taxiSquad;
    public int money = 260;

    public ObservableList<Player> getTaxiSquad() {
        return taxiSquad;
    }

    public void setTaxiSquad(ObservableList<Player> taxiSquad) {
        this.taxiSquad = taxiSquad;
    }
    
    //Stats
    public IntegerProperty playersNeeded;
    public IntegerProperty moneyLeft;
    public IntegerProperty moneyPp;
    public IntegerProperty r;
    public IntegerProperty hr;
    public IntegerProperty rbi;
    public IntegerProperty sb;
    public IntegerProperty totalPoints;
    public DoubleProperty ba;
    public IntegerProperty w;
    public IntegerProperty sv;
    public IntegerProperty k;
    public DoubleProperty era;
    public DoubleProperty whip;
    
    
    public Team()
    {
        String[] defPos = {"C", "C", "1B", "CI", "2B", "3B", "MI", "SS", "U", "OF", "OF", "OF", "OF", "OF", "P",  "P",  "P",  "P",  "P",  "P",  "P",  "P", "P"};
       positions = new ArrayList<String>(Arrays.asList(defPos));
       players = FXCollections.observableArrayList();
       taxiSquad = FXCollections.observableArrayList();
       totalPoints = new SimpleIntegerProperty();    
       
       teamName = new SimpleStringProperty();
       playersNeeded = new SimpleIntegerProperty(0);
       moneyLeft = new SimpleIntegerProperty(0);
       moneyPp = new SimpleIntegerProperty(0);
       r = new SimpleIntegerProperty(0);
       hr = new SimpleIntegerProperty(0);
       rbi = new SimpleIntegerProperty(0);
       sb = new SimpleIntegerProperty(0);
       ba = new SimpleDoubleProperty(0);
       w = new SimpleIntegerProperty(0);
       sv = new SimpleIntegerProperty(0);
       k = new SimpleIntegerProperty(0);
       era = new SimpleDoubleProperty(0);
       whip = new SimpleDoubleProperty(0);
       
       k = new SimpleIntegerProperty();
    }
    
        public int getMoneyLeft()
    {
        return moneyLeft.get();
    }
        
      public void setMoneyLeft(int moneyLeft)
    {
        this.moneyLeft.set(moneyLeft);
    }
      
              public int getMoneyPp()
    {
        return moneyPp.get();
    }
        
      public void setMoneyPp(int moneyPp)
    {
        this.moneyPp.set(moneyPp);
    }
    
    
        public int getPlayersNeeded()
    {
        return playersNeeded.get();
    }
        
        public void setPlayersNeeded(int playersNeeded)
        {
            this.playersNeeded.set(playersNeeded);
        }

    public int getR()
    {
        return r.get();
    }
    
            public void setR(int r)
    {
       this.r.set(r);
    }
            
    public int getSB()
    {
        return sb.get();
    }
    
            public void setSB(int sb)
    {
       this.sb.set(sb);
    }
            
    public double getBa()
    {
        return ba.get();
    }
    
            public void setBa(double ba)
    {
       this.ba.set(ba);
    }
            
       public int getHr()
    {
        return hr.get();
    }
        
    
       public void setHr(int hr)
    {
       this.hr.set(hr);
    }

        
            public int getRbi()
    {
        return rbi.get();
    }
            
            public void setRbi(int rbi)
            {
                this.rbi.set(rbi);
            }
            
        public int getSb()
    {
        return sb.get();
    }
        

          public int getW()
    {
        return w.get();
    }
          
          public void setW(int w)
            {
                this.w.set(w);
            }
          
               public int getSv()
    {
        return sv.get();
    }
               
            public void setSv(int sv)
            {
                this.sv.set(sv);
            }
               
        public int getK()
    {
        return k.get();
    }
            public void setK(int k)
            {
                this.rbi.set(k);
            }
                             
                    public int getTotalPoints()
    {
        return totalPoints.get();
    }
            public void setTotalPoints(int totalPoints)
            {
                this.totalPoints.set(totalPoints);
            }
            
     public double getEra()
    {
        return era.get();
    }
         public double getWhip()
    {
        return whip.get();
    }
         
              public void setEra(double era)
    {
        this.era.set(era);
    }
         public void setWhip(double whip)
    {
        this.whip.set(whip);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        teamName.set(name);
    }
    
    public String getTeamName()
    {
        return teamName.get();
    }
    
    public void setTeamName(String teamName)
    {
        this.teamName.set(teamName);
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }
    
    public String toString()
    {
        return this.name;
    }
    
    public List <String> getAvailiblePositions(String positionString)
    {
        List<String> possiblePositions = new ArrayList<String>();
        String[] poses = positionString.split("_");
        
        for(String s: poses)
        {
            if(positions.contains(s))
            {
                possiblePositions.add(s);
                continue;
            }
        }
        
        if(players.size()==23)
        {
            boolean taxiAvailable = true;
            for(Team team: TeamsScreen.teamsList)
            {
                if(team.getPlayers().size()<23)
                {
                    taxiAvailable = false;
                }
            }
            if(taxiAvailable)
            {
                if(this.getTaxiSquad().size()<8)
                {
            possiblePositions.add("Taxi Squad");
                }
            }
        }
        return possiblePositions;
    }
    
    public List<String> getTotalPositions()
    {
        System.out.println(positions);
        return positions;
    }

    public void addPlayer(Player playerToEdit, String position) 
    {
      players.add(playerToEdit);
      positions.remove(position);
      players.sort(new PlayerPositionComparator());
      money -= playerToEdit.getSalary();
      updateStats();
      System.out.println(positions);
    }
    
    public void addTaxiPlayer(Player player)
    {
        player.setChosenPosition("Taxi");
        taxiSquad.add(player);
    }

    public void removePlayer(Player playerToEdit, String position) 
    {
        players.remove(playerToEdit);
        positions.add(position);
        money += playerToEdit.getSalary();
        updateStats();
        System.out.println(positions);
    }

    public static Player lookUpPlayer(String player, ObservableList<Player> masterList) 
    {
        
      for(Player p : masterList)
      {
          String pName = p.getFirstName().toLowerCase() + p.getLastName().toLowerCase();
          if(player.equals(pName))
          {
              return p;
          }
      }
      System.out.println("Player not found?");
      return null;
    }

    private void updateStats() 
    {
      updatePlayersNeeded();
      updateMoneyLeft();
      updateMoneyPP();
      updatePitcherStats();
      updateHitterStats();
    }

    private void updatePlayersNeeded() 
    {
        int currentPlayersNeeded = 23 - (players.size());
      setPlayersNeeded(currentPlayersNeeded);
    }

    private void updateHitterStats() 
    {
        int totalR = 0;
        int totalHR = 0;
        int totalRBI = 0;
        int totalSB = 0;
        double totalBA = 0;
        double avgBA = 0;
        int hitterAmount = 0;
        
        for(Player p: players)
        {
            if(!p.getChosenPosition().equals("P"))
            {
                totalR += p.getRuns();
                totalHR += p.getHomeruns();
                totalRBI += p.getRbi();
                totalSB += p.getStolenbases();
                totalBA += p.getBa();
                hitterAmount++;
            }
        }
        avgBA = totalBA/hitterAmount;
        
        r.set(totalR);
        hr.set(totalHR);
        rbi.set(totalRBI);
        sb.set(totalSB);
        ba.set(DecimalFormatter.format3(avgBA));

    }
    
        private void updatePitcherStats()
    {
              int totalW = 0;
              int totalSV = 0;
              int totalK = 0;
              double totalERA = 0;
              double totalWHIP = 0;
              double avgERA;
              double avgWHIP;
              int pitcherAmount = 0;
        for(Player p: players)
        {
            if(p.getChosenPosition().equals("P"))
            {
                totalW += p.getRuns();
                totalSV += p.getHomeruns();
                totalK += p.getRbi();
                totalERA += p.getStolenbases();
                totalWHIP += p.getBa();
                pitcherAmount++;
            }
        }
        w.set(totalW);
        sv.set(totalSV);
        k.set(totalK);
        avgERA = totalERA/pitcherAmount;
        avgWHIP =totalWHIP/pitcherAmount;
        

        era.set(DecimalFormatter.format(avgERA));
        whip.set(DecimalFormatter.format(avgWHIP));
    }

    private void updateMoneyLeft() 
    {
      setMoneyLeft(money);
    }

    private void updateMoneyPP() 
    {
        if(playersNeeded.get()==0)
        {
            setMoneyPp(0);
            return;
        }
        int moneyPerPlayer = (money/playersNeeded.get());

        setMoneyPp(moneyPerPlayer);
    }

    public void resetPoints() {
        totalPoints.set(0);
    }

    public void addPoints(int i) {
        totalPoints.set(totalPoints.get()+i);
    }



    
}
