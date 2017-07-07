/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit.gui;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import woflieballdraftkit.data.Player;
import woflieballdraftkit.data.Team;
import wolfieballdraftkit.image.ImageManager;

/**
 *
 * @author Louis
 */
public class TeamsScreen extends ScreenPane
{


    VBox teamsBox;
    HBox draftTeamNameBox;
    HBox teamToolbar;
    Label teamsHeaderLabel;
    Label taxiSquadLabel;
    
    Label startingLineupHeader;
    
    Button addButton;
    Button removeButton;
    Button editButton;
    
    static TableView <Player> lineupTable;
    TableView <Player> taxiSquad;
    
    Label selectTeamLabel;
    ComboBox<Team> selectTeamBox;
    
    public static ArrayList <Team> teamsList;
    
    TextField draftTeamNameField;
    Label draftTeamLabel;
    
    Stage primaryStage;
    PlayersScreen ps;
    FantasyStandingsScreen fs;
    
    public TeamsScreen(Stage primaryStage, PlayersScreen ps, FantasyStandingsScreen fs)
    {
        this.fs = fs;
        this.ps = ps;
        this.primaryStage = primaryStage;
       teamsBox = new VBox();
       teamsBox.setPadding(new Insets(10, 10, 10, 10));
       teamsBox.setSpacing(10);
       
       taxiSquadLabel = initHeaderLabel(taxiSquadLabel);
       taxiSquadLabel.setText("Taxi Squad");
       
       lineupTable = new TableView<Player>();
              TableColumn posCol = new TableColumn("Position");
              TableColumn firstNameCol = new TableColumn("First Name");
       TableColumn lastNameCol = new TableColumn("Last Name");
       TableColumn teamCol = new TableColumn("Pro Team");
       TableColumn dobCol = new TableColumn("Year of Birth");
       TableColumn stat1 = new TableColumn("R/W");
       TableColumn stat2 = new TableColumn("HR/SV");
       TableColumn stat3 = new TableColumn("RBI/K");
       TableColumn stat4 = new TableColumn("SB/ERA");
       TableColumn stat5 = new TableColumn("BA/WHUP");
       TableColumn value = new TableColumn("Estimated Value");
       TableColumn contract = new TableColumn("Contract");
       TableColumn salary = new TableColumn("Salary");
       
        lineupTable.setOnMouseClicked(e->handleEditPlayer(e));
       
       lineupTable.getColumns().addAll(posCol, firstNameCol, lastNameCol, teamCol, dobCol, stat1, stat2, stat3, stat4, stat5, value, contract, salary);
       firstNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
       lastNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
       teamCol.setCellValueFactory(new PropertyValueFactory<String, String>("team"));
       posCol.setCellValueFactory(new PropertyValueFactory<String, String>("chosenPosition"));
       dobCol.setCellValueFactory(new PropertyValueFactory<String, String>("dateOfBirth"));
       stat1.setCellValueFactory(new PropertyValueFactory<String, String>("runs"));
       stat2.setCellValueFactory(new PropertyValueFactory<String, String>("homeruns"));
       stat3.setCellValueFactory(new PropertyValueFactory<String, String>("rbi"));
       stat4.setCellValueFactory(new PropertyValueFactory<String, String>("stolenbases"));
       stat5.setCellValueFactory(new PropertyValueFactory<String, String>("ba"));
       contract.setCellValueFactory(new PropertyValueFactory<String, String>("contract"));
       salary.setCellValueFactory(new PropertyValueFactory<String, String>("salary"));
       
       taxiSquad = new TableView<Player>();
       
       TableColumn tposCol = new TableColumn("Positions");
       TableColumn tfirstNameCol = new TableColumn("First Name");
       TableColumn tlastNameCol = new TableColumn("Last Name");
       TableColumn tteamCol = new TableColumn("Pro Team");
       TableColumn tdobCol = new TableColumn("Year of Birth");
       TableColumn tstat1 = new TableColumn("R/W");
       TableColumn tstat2 = new TableColumn("HR/SV");
       TableColumn tstat3 = new TableColumn("RBI/K");
       TableColumn tstat4 = new TableColumn("SB/ERA");
       TableColumn tstat5 = new TableColumn("BA/WHUP");
       TableColumn tvalue = new TableColumn("Estimated Value");
       TableColumn tcontract = new TableColumn("Contract");
       
       taxiSquad.getColumns().addAll(tposCol, tfirstNameCol, tlastNameCol, tteamCol, tdobCol, tstat1, tstat2, tstat3, tstat4, tstat5, tvalue, tcontract);
       
       tfirstNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
       tlastNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
       tteamCol.setCellValueFactory(new PropertyValueFactory<String, String>("team"));
       tposCol.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
       tdobCol.setCellValueFactory(new PropertyValueFactory<String, String>("dateOfBirth"));
       tstat1.setCellValueFactory(new PropertyValueFactory<String, String>("runs"));
       tstat2.setCellValueFactory(new PropertyValueFactory<String, String>("homeruns"));
       tstat3.setCellValueFactory(new PropertyValueFactory<String, String>("rbi"));
       tstat4.setCellValueFactory(new PropertyValueFactory<String, String>("stolenbases"));
       tstat5.setCellValueFactory(new PropertyValueFactory<String, String>("ba"));
       tcontract.setCellValueFactory(new PropertyValueFactory<String, String>("contract"));

       
       teamsHeaderLabel = initHeaderLabel(teamsHeaderLabel);
       teamsHeaderLabel.setText("Fantasy Teams");
       
       startingLineupHeader = initHeaderLabel(startingLineupHeader);
       startingLineupHeader.setText("Starting Lineup");
       
       teamToolbar = initTeamsToolbar(teamToolbar);
       draftTeamNameBox = initDraftToolbar(draftTeamNameBox);
       
       teamsBox.getChildren().add(teamsHeaderLabel);
       teamsBox.getChildren().add(draftTeamNameBox);
       
       teamsBox.getChildren().add(teamToolbar);
       teamsBox.getChildren().add(startingLineupHeader);
       
       teamsBox.getChildren().add(lineupTable);
       
       teamsBox.getChildren().add(taxiSquadLabel);
       
       teamsBox.getChildren().add(taxiSquad);
       selectTeamBox.setOnAction(e -> handleSelectTeamEvent());
       this.getChildren().add(teamsBox);
       
       teamsBox.setStyle("-fx-background-color: white");
       
       teamsList = new ArrayList<Team>();
    }

    private HBox initTeamsToolbar(HBox toolbar)
    {
        toolbar = new HBox();

                      //init the buttons
       addButton = initButton(addButton, "Add Team", ImageManager.addIcon);
       removeButton = initButton(removeButton, "Remove Team", ImageManager.removeIcon);   
       editButton = initButton(editButton, "Remove Button", ImageManager.editIcon);
       selectTeamLabel = initLabel(selectTeamLabel, 16);
       selectTeamLabel.setText("Select Team");
       selectTeamBox = new ComboBox();
       //add them to the bar
       toolbar.getChildren().addAll(addButton, removeButton, editButton, selectTeamLabel, selectTeamBox);
       toolbar.setStyle("-fx-background-color: gainsboro;");
       toolbar.setSpacing(5);    
       //Setup Events
       
       addButton.setOnAction(e -> handleAddTeamEvent());
       removeButton.setOnAction(e -> handleRemoveTeamEvent());
       editButton.setOnAction(e-> handleEditTeamEvent());
       return toolbar;
       
    }
    
    private HBox initDraftToolbar(HBox toolbar)
    {
        toolbar = new HBox();
        draftTeamNameField = new TextField();
        draftTeamLabel = initLabel(draftTeamLabel, 16);
        draftTeamLabel.setText("Draft Name: ");
        toolbar.getChildren().addAll(draftTeamLabel, draftTeamNameField);
        return toolbar;
    }
    
    public void setColor(Pane pane)
    {
       teamsBox.setPrefWidth(1100);
       teamsBox.setPrefHeight(600);
      pane.setStyle("-fx-border-color: black; -fx-border-insets: 5, 5, 5, 5; -fx-background-color: lightskyblue;");
    }

    private void handleAddTeamEvent()
    {
       Team team = new Team();
       FantasyTeamDialog fdt = new FantasyTeamDialog(team, primaryStage);
       fdt.showDialog();
       selectTeamBox.getItems().add(team);
       selectTeamBox.getSelectionModel().select(team);
       teamsList.add(team);
       fs.addTeam(team);
       
    }
    
    private void handleSelectTeamEvent()
    {
        try{
                      lineupTable.setItems(selectTeamBox.getSelectionModel().getSelectedItem().players);
                      taxiSquad.setItems(selectTeamBox.getSelectionModel().getSelectedItem().getTaxiSquad());
        }
        catch(Exception e)
        {
            
        }
    }

    private void handleEditPlayer(MouseEvent e) {
                if(e.getClickCount()==2)
        {
           Player p = (Player) lineupTable.getSelectionModel().getSelectedItem();
           PlayerDialog fdt = new PlayerDialog(p, primaryStage, ps);
           fdt.setPlayer(p, selectTeamBox.getSelectionModel().getSelectedItem());
           fdt.showDialog();
        }
    }

    private void handleEditTeamEvent() 
    {
        
       Team teamToEdit = selectTeamBox.getSelectionModel().getSelectedItem();
       FantasyTeamDialog fdt = new FantasyTeamDialog(teamToEdit, primaryStage);
       fdt.setTeam(teamToEdit);
       fdt.showDialog();
       selectTeamBox.getItems().remove(teamToEdit);
       selectTeamBox.getItems().add(teamToEdit);
       selectTeamBox.getSelectionModel().select(teamToEdit);
    }

    private void handleRemoveTeamEvent() 
    {
        
      Team teamToRemove = selectTeamBox.getSelectionModel().getSelectedItem();
      teamsList.remove(teamToRemove);
       for(Player p : teamToRemove.players)
       {
           PlayersScreen.getMasterList().add(p);
       }
        ps.updateTableEvent();
       selectTeamBox.getItems().remove(teamToRemove);
                MessageDialog ed = new MessageDialog(primaryStage, "OK");
                ed.show("Team has been removed and players have returned to the free agent pool.");
                
                lineupTable.setItems(FXCollections.emptyObservableList());
             
                
                selectTeamBox.getSelectionModel().select(0);
                fs.removeTeam(teamToRemove);
    }

    ArrayList<Team> getTeams() {
       return teamsList;
    }

    public void loadTeams(ArrayList<Team> teams) 
    {
       teamsList.addAll(teams);
       selectTeamBox.setItems(FXCollections.observableArrayList(teams));
       fs.setTeams(FXCollections.observableArrayList(teams));
       for(Team t: teams)
       {
           System.out.println(t.getName());
       }
    }
    public static int getMoneyRemaining() {
        int money = 0;
     for(Team t: teamsList)
     {
      money+= t.getMoneyLeft();
     }
     return money;
    }
        static void refreshTable() 
    {
      for (int i = 0; i < lineupTable.getColumns().size(); i++) 
      {
        ((TableColumn)(lineupTable.getColumns().get(i))).setVisible(false);
        ((TableColumn)(lineupTable.getColumns().get(i))).setVisible(true);
     }
    }
        
        public void onShow()
        {
            for(Player p : lineupTable.getItems())
            {
                p.calculateEstimatedValue();
            }
            
            for(Player p : taxiSquad.getItems())
            {
                p.calculateEstimatedValue();
            }
        }
        
}
