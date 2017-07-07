/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import woflieballdraftkit.data.Team;

/**
 *
 * @author Louis
 */
class FantasyStandingsScreen extends ScreenPane
{
    VBox standingsBox;
    Label standingsHeaderLabel;
    TableView<Team> standingsTable;
    ObservableList <Team> teams = FXCollections.observableArrayList();
    
    public FantasyStandingsScreen()
    {
               standingsBox = new VBox();
       standingsBox.setPadding(new Insets(10, 10, 10, 10));
       standingsBox.setSpacing(10);
       
       standingsHeaderLabel = initHeaderLabel(standingsHeaderLabel);
       standingsHeaderLabel.setText("Fantasy Standings Estimate");
       standingsBox.getChildren().add(standingsHeaderLabel);
       
       standingsTable = new TableView<>();
       TableColumn teamName = new TableColumn("Team Name");
       TableColumn playersNeeded = new TableColumn("Players Needed");
       TableColumn moneyLeft = new TableColumn("$ Left");
       TableColumn moneyPP = new TableColumn("$ PP");
       TableColumn stat1 = new TableColumn("R");
       TableColumn stat2 = new TableColumn("HR");
       TableColumn stat3 = new TableColumn("RBI");
       TableColumn stat4 = new TableColumn("SB");
       TableColumn stat5 = new TableColumn("BA");
       TableColumn stat6 = new TableColumn("W");
       TableColumn stat7 = new TableColumn("SV");
       TableColumn stat8 = new TableColumn("K");
       TableColumn stat9 = new TableColumn("ERA");
       TableColumn stat10 = new TableColumn("WHIP");
       TableColumn totalPoints = new TableColumn("Total Poitns");
       
       teamName.setCellValueFactory(new PropertyValueFactory<String, String>("teamName"));
       playersNeeded.setCellValueFactory(new PropertyValueFactory<String, String>("playersNeeded"));
       moneyLeft.setCellValueFactory(new PropertyValueFactory<String, String>("moneyLeft"));
       moneyPP.setCellValueFactory(new PropertyValueFactory<String, String>("moneyPp"));
       stat1.setCellValueFactory(new PropertyValueFactory<String, String>("r"));
       stat2.setCellValueFactory(new PropertyValueFactory<String, String>("hr"));
       stat3.setCellValueFactory(new PropertyValueFactory<String, String>("rbi"));
       stat4.setCellValueFactory(new PropertyValueFactory<String, String>("sb"));
       stat5.setCellValueFactory(new PropertyValueFactory<String, String>("ba"));
       stat6.setCellValueFactory(new PropertyValueFactory<String, String>("w"));
       stat7.setCellValueFactory(new PropertyValueFactory<String, String>("sv"));
       stat8.setCellValueFactory(new PropertyValueFactory<String, String>("k"));
       stat9.setCellValueFactory(new PropertyValueFactory<String, String>("era"));
       stat10.setCellValueFactory(new PropertyValueFactory<String, String>("whip"));
       totalPoints.setCellValueFactory(new PropertyValueFactory<String, String>("totalPoints"));
       
       standingsTable.getColumns().addAll(teamName, playersNeeded, moneyLeft, moneyPP, stat1, stat2, stat3, stat4, stat5, stat6, stat7, stat8, stat9, stat10, totalPoints);
       standingsTable.setItems(teams);
       
       
       standingsBox.getChildren().add(standingsTable);
       standingsBox.setStyle("-fx-background-color: white");

       
       this.getChildren().add(standingsBox);
    }
    
                public void setColor(Pane pane)
    {
      pane.setStyle("-fx-border-color: black; -fx-border-insets: 5, 5, 5, 5; -fx-background-color: lightcoral;");
    }
                
                public void addTeam(Team team)
                {
                    standingsTable.getItems().add(team);
                }
                
                public void removeTeam(Team team)
                {
                    this.teams.remove(team);
                }

    public void setTeams(ObservableList<Team> observableArrayList) 
    {
      standingsTable.setItems(observableArrayList);
    }
    
    @Override
    void onShow()
    {
        updatePoints();
        refreshTable();
    }

    void refreshTable() 
    {
      for (int i = 0; i < standingsTable.getColumns().size(); i++) 
      {
        ((TableColumn)(standingsTable.getColumns().get(i))).setVisible(false);
        ((TableColumn)(standingsTable.getColumns().get(i))).setVisible(true);
     }
    }

    private void updatePoints() 
    {
        ObservableList<Team> theTeams = standingsTable.getItems();
        
        TreeMap<Integer, Team> tm1 = new TreeMap<>();
        TreeMap<Integer, Team> tm2 = new TreeMap<>();
        TreeMap<Integer, Team> tm3 = new TreeMap<>();
        TreeMap<Integer, Team> tm4 = new TreeMap<>();
        TreeMap<Double, Team> tm5 = new TreeMap<>();
        TreeMap<Integer, Team> tm6 = new TreeMap<>();
        TreeMap<Integer, Team> tm7 = new TreeMap<>();
        TreeMap<Integer, Team> tm8 = new TreeMap<>();
        TreeMap<Double, Team> tm9 = new TreeMap<>();
        TreeMap<Double, Team> tm10 = new TreeMap<>();
        for(Team team: theTeams)
        {
            tm1.put(team.getR(), team);
            tm2.put(team.getHr(), team);
            tm3.put(team.getRbi(), team);
            tm4.put(team.getSb(), team);
            tm5.put(team.getBa(), team);
            tm6.put(team.getW(), team);
            tm7.put(team.getSv(), team);
            tm8.put(team.getK(), team);
            tm9.put(team.getEra(), team);
            tm10.put(team.getWhip(), team);
            team.resetPoints();
        }
        
        for(int i=theTeams.size();i>0;i--)
        {
           int top1 = tm1.lastKey();
           int top2 = tm2.lastKey();
           int top3 = tm3.lastKey();
           int top4 = tm4.lastKey();
           double top5 = tm5.lastKey();
           int top6 = tm6.lastKey();
           int top7 = tm7.lastKey();
           int top8 = tm8.lastKey();
           double top9 = tm9.lastKey();
           double top10 = tm10.lastKey();

           
           tm1.get(top1).addPoints(i);
           tm1.remove(top1);
           
           tm2.get(top2).addPoints(i);
           tm2.remove(top2);
           
           tm3.get(top3).addPoints(i);
           tm3.remove(top3);
           
           tm4.get(top4).addPoints(i);
           tm4.remove(top4);
           
           tm5.get(top5).addPoints(i);
           tm5.remove(top5);
           
           tm6.get(top6).addPoints(i);
           tm6.remove(top6);
           
           tm7.get(top7).addPoints(i);
           tm7.remove(top7);
           
           tm8.get(top8).addPoints(i);
           tm8.remove(top8);
           
           tm9.get(top9).addPoints(i);
           tm9.remove(top9);
           
           tm10.get(top10).addPoints(i);
           tm10.remove(top10);
           
           
        }
    }
}
