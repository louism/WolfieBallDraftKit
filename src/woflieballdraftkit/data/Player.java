/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woflieballdraftkit.data;

import java.text.DecimalFormat;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import wolfieballdraftkit.gui.TeamsScreen;

/**
 *
 * @author Louis
 */
public class Player implements Comparable
{
        StringProperty team;
        StringProperty firstName;
        StringProperty lastName;
        StringProperty position;
        StringProperty chosenPosition;
        //Runs for Hitters, Wins for Pitchers
        IntegerProperty runsWins;
        //HomeRuns for Hitters, Saves for Pitchers
        IntegerProperty homerunsSaves;
        //Runs batted in for Hitters, Strikeouts(K) for Pitchers
        IntegerProperty rbiK;
        //Stolen Bases for Hitters, Earned Run Average for Pitchers
        DoubleProperty stolenbasesERA;
        //Batting Average for Hitters, WHIP for Pitchers
        DoubleProperty baWhip;
        StringProperty notes;
        IntegerProperty dateOfBirth;
        StringProperty nationOfBirth;
        IntegerProperty estimatedValue;
        
        StringProperty contract;
        DoubleProperty salary;
        StringProperty fantasyName;
        
        //Blank Player
        public Player(String firstName, String lastName, String position, String proTeam)
        {
            this.team = new SimpleStringProperty(proTeam);
            this.fantasyName = new SimpleStringProperty();
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.runsWins = new SimpleIntegerProperty(0);
            this.homerunsSaves = new SimpleIntegerProperty(0);
            this.rbiK = new SimpleIntegerProperty(0);
            this.stolenbasesERA = new SimpleDoubleProperty(0);
            this.notes = new SimpleStringProperty("No Notes");
            this.dateOfBirth = new SimpleIntegerProperty(0);
            this.nationOfBirth = new SimpleStringProperty("unknown");
            this.baWhip = new SimpleDoubleProperty(0);
            this.contract = new SimpleStringProperty();
            this.estimatedValue = new SimpleIntegerProperty();
            this.salary = new SimpleDoubleProperty();
                        this.chosenPosition = new SimpleStringProperty();
                        
                                    //Add MI And CI if needed
            if(position.contains("2B") || position.contains("SS"))
            {
               position +=("_MI");
            }
                        if(position.contains("1B") || position.contains("3B"))
            {
               position +=("_CI");
            }
                        this.position = new SimpleStringProperty(position);
        }
        //Constructor for Hitter
        public Player (String team, String firstName, String lastName, String position, int ab, int r, int h, int hr, int rbi, int sb, String notes, int dob, String nation)
        {
            this.team = new SimpleStringProperty(team);
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
                        this.fantasyName = new SimpleStringProperty();
            this.runsWins = new SimpleIntegerProperty(r);
            this.homerunsSaves = new SimpleIntegerProperty(hr);
            this.rbiK = new SimpleIntegerProperty(rbi);
            this.stolenbasesERA = new SimpleDoubleProperty(sb);
            this.notes = new SimpleStringProperty(notes);
            this.estimatedValue = new SimpleIntegerProperty();
            this.dateOfBirth = new SimpleIntegerProperty(dob);
            this.nationOfBirth = new SimpleStringProperty(nation);
            this.chosenPosition = new SimpleStringProperty();
                        this.contract = new SimpleStringProperty();
            this.salary = new SimpleDoubleProperty();
            
            //BA needs to be calculated.
            if(ab !=0)
            {
                DecimalFormat df = new DecimalFormat("#.##");
                String baString = df.format((double)h/ab);
            this.baWhip = new SimpleDoubleProperty(Double.parseDouble(baString));
            }
            else
            {
               this.baWhip = new SimpleDoubleProperty(0);
            }
            
            //Add MI And CI if needed
            if(position.contains("2B") || position.contains("SS"))
            {
               position +=("_MI");
            }
                        if(position.contains("1B") || position.contains("3B"))
            {
               position +=("_CI");
            }
                        position +=("_U");
                        this.position = new SimpleStringProperty(position);
        }
        //Constructor for Pitcher
                public Player (String team, String firstName, String lastName, String position, double ip, int er, int walks, int hits, int wins, int saves, int k, String notes, int dob, String nation)
        {
            this.team = new SimpleStringProperty(team);
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.position = new SimpleStringProperty(position);
            this.runsWins = new SimpleIntegerProperty(wins);
            this.homerunsSaves = new SimpleIntegerProperty(saves);
            this.rbiK = new SimpleIntegerProperty(k);
            this.estimatedValue = new SimpleIntegerProperty();
                       this.fantasyName = new SimpleStringProperty();
            this.notes = new SimpleStringProperty(notes);
            this.dateOfBirth = new SimpleIntegerProperty(dob);
            this.nationOfBirth = new SimpleStringProperty(nation);
                        this.contract = new SimpleStringProperty();
            this.salary = new SimpleDoubleProperty();
                        this.chosenPosition = new SimpleStringProperty();
            //ERA and WHIP must be calculated.
            if(ip !=0)
            {
                DecimalFormat df = new DecimalFormat("#.##");
                String baString = df.format((double)(er*9)/(ip));
            this.stolenbasesERA = new SimpleDoubleProperty(Double.parseDouble(baString));
            }
            else
            {
               this.stolenbasesERA = new SimpleDoubleProperty(0);
            }
            //WHIP calculation
                        if(ip !=0)
            {
                DecimalFormat df = new DecimalFormat("#.##");
                String baString = df.format((double)(walks + hits)/(ip));
            this.baWhip = new SimpleDoubleProperty(Double.parseDouble(baString));
            }
            else
            {
               this.baWhip = new SimpleDoubleProperty(0);
            }
        }

    public String getTeam()
    {
        return team.get();
    }

    public void setTeam(String team)
    {
        this.team.set(team);
    }
    
        public String getFantasyName()
    {
        return fantasyName.get();
    }

    public void setFantasyName(String fantasyName)
    {
        this.fantasyName.set(fantasyName);
    }
    
        public double getBa()
    {
        return this.baWhip.get();
    }
        
                public int getEstimatedValue()
    {
        return this.estimatedValue.get();
    }

    public void setBa(double ba)
    {
        this.baWhip.set(ba);
    }

    public String getFirstName()
    {
        return firstName.get();
    }

    public void setFirstName(String firstName)
    {
        this.firstName.set(firstName);
    }

    public String getLastName()
    {
        return lastName.get();
    }

    public void setLastName(String lastName)
    {
        this.lastName.set(lastName);
    }

    public String getPosition()
    {
        return position.get();
    }

    public void setPosition(String position)
    {
        this.position.set(position);
    }

    public int getRuns()
    {
        return runsWins.get();
    }

    public void setRuns(int runs)
    {
        this.runsWins.set(runs);
    }


    public int getHomeruns()
    {
        return homerunsSaves.get();
    }

    public void setHomeruns(int homeruns)
    {
        this.homerunsSaves.set(homeruns);
    }

    public int getRbi()
    {
        return rbiK.get();
    }

    public void setRbi(int rbi)
    {
        this.rbiK.set(rbi);
    }

    public double getStolenbases()
    {
        return stolenbasesERA.get();
    }

    public void setStolenbases(int stolenbases)
    {
        this.stolenbasesERA.set(stolenbases);
    }

    public String getNotes()
    {
        return notes.get();
    }

    public void setNotes(String notes)
    {
        this.notes.set(notes);
    }

    public int getDateOfBirth()
    {
        return dateOfBirth.get();
    }

    public void setDateOfBirth(int dateOfBirth)
    {
        this.dateOfBirth.set(dateOfBirth);
    }

    public String getNationOfBirth()
    {
        return nationOfBirth.get();
    }

    public void setNationOfBirth(String nationOfBirth)
    {
        this.nationOfBirth.set(nationOfBirth);
    }
    
        public String getContract()
    {
        return contract.get();
    }

    public void setContract(String contract)
    {
        this.contract.set(contract);
    }
    
        
        public String getChosenPosition()
    {
        return chosenPosition.get();
    }

    public void setChosenPosition(String chosenPosition)
    {
        this.chosenPosition.set(chosenPosition);
    }
    
        public Double getSalary()
    {
        return salary.get();
    }

    public void setSalary(Double salary)
    {
        this.salary.set(salary);
    }
    
    public void calculateEstimatedValue()
    {
        int money = TeamsScreen.getMoneyRemaining();
        
        try{
        if(this.getPosition().contains("P"))
        {

            
            int total = (int) (rbiK.get() + runsWins.get() + homerunsSaves.get());
            
            this.estimatedValue.set((total/30)+1);
            
        }
        else
        {
            int total = (int) ( this.getStolenbases() + rbiK.get() + runsWins.get() + homerunsSaves.get());
            
            this.estimatedValue.set((total/30)+1);
        }
        }
        catch(Exception e)
        {
            
        }
    }

    @Override
    public int compareTo(Object o)
    {
        if (o instanceof Player)
        {
            Player s = (Player) o;
            return this.getLastName().compareTo(s.getLastName());
        }
        return -1;
    }

   public int getValue()
   {
       int val;
       
       switch(chosenPosition.get())
       {
           case "C":
           val = 0;
           break;
           case "1B":
           val = 1;
           break;
           case "CI":
           val = 2;
           break;
           case "3B":
           val = 3;
           break;
           case "2B":
           val = 4;
           break;
          case "MI":
           val = 5;
           break;
           case "SS":
           val = 6;
           break;
           case "OF":
           val = 7;
           break;
           case "U":
           val = 8;
           break;
          case "P":
           val = 9;
           break;
           default:
           val = -1;
               
       }
       return val;
    }
        

        
}
