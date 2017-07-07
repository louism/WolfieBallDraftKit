/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woflieballdraftkit.data;

import java.util.Collections;
import javafx.collections.ObservableList;
import wolfieballdraftkit.file.JsonPlayerManager;

/**
 *
 * @author Louis
 */
public class WolfieBallDraftKitDataManager
{
    JsonPlayerManager jpm;
    ObservableList<Player> players;
    
    public WolfieBallDraftKitDataManager(JsonPlayerManager jpm)
    {
        this.jpm = jpm;
    }
    
    public void loadPlayers()
    {
        players = jpm.loadHitters();
        players.addAll(0, jpm.loadPitchers());
        Collections.sort(players);
    }
    
    public ObservableList<Player> getHitters()
    {
        return players;
    }
    
}
