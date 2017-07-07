/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit.gui;

import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import woflieballdraftkit.data.MLBTeams;
import woflieballdraftkit.data.Player;
import static wolfieballdraftkit.gui.PlayersScreen.masterList;

/**
 *
 * @author Louis
 */
public class MLBScreen extends ScreenPane
{
    VBox mlbBox;
    Label mlbHeaderLabel;
    Label teamLabel;
    ComboBox<String> teamSelector;
    
    TableView<Player> mlbTable;
    
    ObservableList<Player> masterList;
    
    public MLBScreen()
    {
               mlbBox = new VBox();
      mlbBox.setPadding(new Insets(10, 10, 10, 10));
       mlbBox.setSpacing(10);
       
       masterList = PlayersScreen.getMasterList();
       
       mlbHeaderLabel = initHeaderLabel(mlbHeaderLabel);
       mlbHeaderLabel.setText("MLB Teams");
       teamLabel = initLabel(teamLabel, 15);
       teamLabel.setText("Choose Pro Team:  ");
       teamSelector = new ComboBox<>();
       teamSelector.getItems().addAll(MLBTeams.teams);
       teamSelector.setOnAction(e -> changeTeams());
       
       mlbTable = new TableView<>();
       TableColumn firstNameCol = new TableColumn("First Name");
       TableColumn lastNameCol = new TableColumn("Last Name");
       TableColumn posCol = new TableColumn("Positions");
           
       mlbTable.getColumns().addAll(firstNameCol, lastNameCol, posCol);
       firstNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
       lastNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
       posCol.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
       
       mlbBox.getChildren().addAll(mlbHeaderLabel, teamLabel, teamSelector, mlbTable);
       mlbBox.setStyle("-fx-background-color: white");
        mlbBox.setPrefWidth(990);
       mlbBox.setPrefHeight(600);
       
       this.getChildren().add(mlbBox);
    }
            public void setColor(Pane pane)
    {
        
      pane.setStyle("-fx-border-color: black; -fx-border-insets: 5, 5, 5, 5; -fx-background-color: khaki;");
    }

    private void changeTeams() {
              ObservableList<Player> updatedList = FXCollections.observableArrayList();
              
        for(Player p: masterList)
        {
            if(p.getTeam().equals(teamSelector.getSelectionModel().getSelectedItem()))
            {
                updatedList.add(p);
            }
            
    }
        Collections.sort(updatedList);
        mlbTable.setItems(updatedList);
        
    }
}
