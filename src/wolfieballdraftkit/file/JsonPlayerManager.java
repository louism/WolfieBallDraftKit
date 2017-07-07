/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit.file;

import java.io.InputStream;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import woflieballdraftkit.data.Player;

/**
 *
 * @author Louis
 */
public class JsonPlayerManager
{
    public ObservableList<Player> loadHitters()
    {
        ArrayList<Player> listOfHitters = new ArrayList<Player>();
        InputStream is = getClass().getResourceAsStream("/Data/Hitters.json");
        JsonReader jr = Json.createReader(is);
        JsonObject hitters= jr.readObject();
        JsonArray hittersArray = hitters.getJsonArray("Hitters");
        
        for(int i = 0; i<hittersArray.size();i++)
        {
            Player h = createHitter(hittersArray.getJsonObject(i));
            listOfHitters.add(h);
        }
        return FXCollections.observableArrayList(listOfHitters);
    }
    
        public ObservableList<Player> loadPitchers()
    {
        ArrayList<Player> listOfPitchers = new ArrayList<Player>();
        InputStream is = getClass().getResourceAsStream("/Data/Pitchers.json");
        JsonReader jr = Json.createReader(is);
        JsonObject pitchers= jr.readObject();
        JsonArray hittersArray = pitchers.getJsonArray("Pitchers");
        
        for(int i = 0; i<hittersArray.size();i++)
        {
            Player p = createPitcher(hittersArray.getJsonObject(i));
            listOfPitchers.add(p);
        }
        return FXCollections.observableArrayList(listOfPitchers);
    }

    private Player createHitter(JsonObject hitter)
    {
        String team = hitter.getString("TEAM");
        String firstName = hitter.getString("FIRST_NAME");
        String lastName = hitter.getString("LAST_NAME");
        String position = hitter.getString("QP");
        int atBats = Integer.parseInt(hitter.getString("AB"));
        int runs = Integer.parseInt(hitter.getString("R"));
        int hits = Integer.parseInt(hitter.getString("H"));
        int homeruns = Integer.parseInt(hitter.getString("HR"));
        int rbi = Integer.parseInt(hitter.getString("RBI"));
        int stolenbases = Integer.parseInt(hitter.getString("SB"));
        String notes = hitter.getString("NOTES");
        int dateOfBirth = Integer.parseInt(hitter.getString("YEAR_OF_BIRTH"));
        String nationOfBirth = hitter.getString("NATION_OF_BIRTH");
        
        //        public Hitter(String team, String firstName, String lastName, String position, int ab, int r, int hr, int rbi, int sb, String notes, int dob, String nation)
         return new Player(team, firstName, lastName, position, atBats, runs, hits, homeruns, rbi, stolenbases, notes, dateOfBirth, nationOfBirth);
    }
    
        private Player createPitcher(JsonObject pitcher)
    {
        String team = pitcher.getString("TEAM");
        String firstName = pitcher.getString("FIRST_NAME");
        String lastName = pitcher.getString("LAST_NAME");
        String position = "P";
        double ip = Double.parseDouble(pitcher.getString("IP"));
        int er = Integer.parseInt(pitcher.getString("ER"));
        int wins = Integer.parseInt(pitcher.getString("W"));
        int walks = Integer.parseInt(pitcher.getString("BB"));
        int saves = Integer.parseInt(pitcher.getString("SV"));
        int hits = Integer.parseInt(pitcher.getString("H"));
        int k = Integer.parseInt(pitcher.getString("K"));
        String notes = pitcher.getString("NOTES");
        int dob = Integer.parseInt(pitcher.getString("YEAR_OF_BIRTH"));
        String nation = pitcher.getString("NATION_OF_BIRTH");
        
        // public Player (String team, String firstName, String lastName, String position, double ip, int er, int walks, int hits, int wins, int saves, int k, int sb, String notes, int dob, String nation)
         return new Player(team, firstName, lastName, position, ip, er, walks, hits, wins, saves, k, notes, dob, nation);
    }
    
   
}
