/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit.gui;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import woflieballdraftkit.data.Player;
import wolfieballdraftkit.image.ImageManager;

/**
 *
 * @author Louis
 */
public class PlayersScreen extends ScreenPane
{


    VBox playersBox;
    HBox playerToolbar;
    HBox searchBox;
    HBox addRemoveBox;
    HBox selectionBox;
    
    TableView playersTable;
    TableColumn notes;
    Label playersHeaderLabel;
    Label searchLabel;
    static ObservableList<Player> masterList;


    public void setMasterList(ObservableList<Player> masterList) {
        this.masterList = masterList;
    }
    
    //Controls
    Button addButton;
    Button removeButton;
    TextField searchBar;
    
    ToggleGroup radioGroup = new ToggleGroup();
    RadioButton allButton;
    RadioButton CButton;
    RadioButton B1Button;
    RadioButton CIButton;
    RadioButton B3Button;
    RadioButton B2Button;
    RadioButton MIButton;
    RadioButton SSButton;
    RadioButton OFButton;
    RadioButton UButton;
    RadioButton PButton;
    Stage primaryStage;
    DraftScreen ds;
    
    public PlayersScreen(ObservableList<Player> players, Stage primaryStage)
    {
        this.ds = ds;
       playersBox = new VBox();
       playersBox.setPadding(new Insets(10, 10, 10, 10));
       playersBox.setSpacing(10);
       
       

       playersTable = new TableView();
              playersTable.setEditable(true);
       TableColumn firstNameCol = new TableColumn("First Name");
       TableColumn lastNameCol = new TableColumn("Last Name");
       TableColumn teamCol = new TableColumn("Pro Team");
       TableColumn posCol = new TableColumn("Positions");
       TableColumn dobCol = new TableColumn("Year of Birth");
       TableColumn stat1 = new TableColumn("R/W");
       TableColumn stat2 = new TableColumn("HR/SV");
       TableColumn stat3 = new TableColumn("RBI/K");
       TableColumn stat4 = new TableColumn("SB/ERA");
       TableColumn stat5 = new TableColumn("BA/WHUP");
       TableColumn value = new TableColumn("Estimated Value");
       notes = new TableColumn("Notes");
       

       playersTable.setOnMouseClicked(e->handleEditPlayer(e));
       playersTable.getColumns().addAll(firstNameCol, lastNameCol, teamCol, posCol, dobCol, stat1, stat2, stat3, stat4, stat5, value, notes);
       firstNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
       lastNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
       teamCol.setCellValueFactory(new PropertyValueFactory<String, String>("team"));
       posCol.setCellValueFactory(new PropertyValueFactory<String, String>("position"));
       dobCol.setCellValueFactory(new PropertyValueFactory<String, String>("dateOfBirth"));
       stat1.setCellValueFactory(new PropertyValueFactory<String, String>("runs"));
       stat2.setCellValueFactory(new PropertyValueFactory<String, String>("homeruns"));
       stat3.setCellValueFactory(new PropertyValueFactory<String, String>("rbi"));
       stat4.setCellValueFactory(new PropertyValueFactory<String, String>("stolenbases"));
       stat5.setCellValueFactory(new PropertyValueFactory<String, String>("ba"));
       value.setCellValueFactory(new PropertyValueFactory<String, String>("estimatedValue"));
       notes.setCellValueFactory(new PropertyValueFactory<String, String>("notes"));
       notes.setCellFactory(TextFieldTableCell.forTableColumn());

       
       this.masterList = players;
       playersTable.setItems(players);
       
       playersHeaderLabel = initHeaderLabel(playersHeaderLabel);
       playersHeaderLabel.setText("Available Players");
       playerToolbar = initPlayerToolbar(playerToolbar);   
       selectionBox = initSelectionToolbar(selectionBox);
       playersBox.getChildren().add(playersHeaderLabel);
       playersBox.getChildren().add(playerToolbar);
       playersBox.getChildren().add(selectionBox);
       playersBox.getChildren().add(playersTable);
       playersBox.setStyle("-fx-background-color: white");
       this.getChildren().add(playersBox);
    }
        public void setColor(Pane pane)
    {
       playersBox.setPrefWidth(1500);
       playersBox.setPrefHeight(600);
      pane.setStyle("-fx-border-color: black; -fx-border-insets: 5, 5, 5, 5; -fx-background-color: darkseagreen;");
    }

    private HBox initPlayerToolbar(HBox toolbar)
    {
        toolbar = new HBox();
        searchBox = new HBox();
        addRemoveBox = new HBox();
                      //init the buttons
       addButton = initButton(addButton, "Add Player", ImageManager.addIcon);
       removeButton = initButton(removeButton, "Remove Player", ImageManager.removeIcon);
       searchLabel = initLabel(searchLabel, 16);
       searchLabel.setText("Search: ");
       searchBar = initTextField(searchBar, (int) WolfieBallDraftKitGUI.width-187);
       
       addButton.setOnAction(e -> handleAddPlayer());
       //add them to the bar
       addRemoveBox.getChildren().addAll(addButton, removeButton);
       
       searchBox.getChildren().addAll(searchLabel, searchBar);
       searchBox.setSpacing(5);
       searchBar.setOnKeyReleased(e -> updateTableEvent());
       
       toolbar.setSpacing(10);
       toolbar.getChildren().addAll(addRemoveBox, searchBox);

       
       //Setup Events
       return toolbar;
       
    }

    public void updateTableEvent()
    {

        String text = searchBar.getText().toLowerCase();
        RadioButton selectedButton = (RadioButton) radioGroup.getSelectedToggle();
        String selectedPosition = selectedButton.getText();
        
        ObservableList<Player> updatedList = FXCollections.observableArrayList();

        for(Player p: masterList)
        {
            if(p.getFirstName().toLowerCase().startsWith(text) || p.getLastName().toLowerCase().startsWith(text))
            {
                updatedList.add(p);
            }
            
        }
        
          ObservableList<Player> updatedList2 = FXCollections.observableArrayList();
        
        for(Player p: updatedList)
        {
            if(selectedPosition.equals("All"))
            {
                updatedList2 = updatedList;
                break;
            }
            if(selectedPosition.equals("P"))
            {
              if(p.getPosition().contains("P"))
                 {
                updatedList2.add(p);
                }
            }
            else if(selectedPosition.equals("U"))
            {
              if(!p.getPosition().contains("P"))
                 {
                updatedList2.add(p);
                }
            }
                        else if(selectedPosition.equals("C"))
            {
              if(p.getPosition().contains("C"))
                 {
                updatedList2.add(p);
                }
            }
            else if(selectedPosition.equals("1B"))
            {
              if(p.getPosition().contains("1B"))
                 {
                updatedList2.add(p);
                }
            }
            else if(selectedPosition.equals("CI"))
            {
              if(p.getPosition().contains("1B") || p.getPosition().contains("3B"))
                 {
                updatedList2.add(p);
                }
            }
            else if(selectedPosition.equals("3B"))
            {
              if(p.getPosition().contains("3B"))
                 {
                updatedList2.add(p);
                }
            }
            else if(selectedPosition.equals("2B"))
            {
              if(p.getPosition().contains("2B"))
                 {
                updatedList2.add(p);
                }
            }
            else if(selectedPosition.equals("MI"))
            {
              if(p.getPosition().contains("2B") || p.getPosition().contains("SS"))
                 {
                updatedList2.add(p);
                }
            }
            else if(selectedPosition.equals("SS"))
            {
              if(p.getPosition().contains("SS"))
                 {
                updatedList2.add(p);
                }
            }
            else if(selectedPosition.equals("OF"))
            {
              if(p.getPosition().contains("OF"))
                 {
                updatedList2.add(p);
                }
            }
        }
        playersTable.setItems(updatedList2);
    }

    private HBox initSelectionToolbar(HBox selectionBox)
    {
       selectionBox = new HBox();
       selectionBox.setSpacing(10);
       selectionBox.setStyle("-fx-background-color: gainsboro;");
       
       allButton = new RadioButton("All");
       allButton.setToggleGroup(radioGroup);
       allButton.setSelected(true);
       UButton = new RadioButton("U");
       UButton.setToggleGroup(radioGroup);
       PButton = new RadioButton("P");
       PButton.setToggleGroup(radioGroup);
       CButton = new RadioButton("C");
       CButton.setToggleGroup(radioGroup);
       B1Button = new RadioButton("1B");
       B1Button.setToggleGroup(radioGroup);
       CIButton = new RadioButton("CI");
       CIButton.setToggleGroup(radioGroup);
       B2Button = new RadioButton("2B");
       B2Button.setToggleGroup(radioGroup);
       B3Button = new RadioButton("3B");
       B3Button.setToggleGroup(radioGroup);
       MIButton = new RadioButton("MI");
       MIButton.setToggleGroup(radioGroup);
       SSButton = new RadioButton("SS");
       SSButton.setToggleGroup(radioGroup);
       OFButton = new RadioButton("OF");
       OFButton.setToggleGroup(radioGroup);
      
       radioGroup.selectedToggleProperty().addListener(e -> handleToggle());
       
       selectionBox.getChildren().addAll(allButton, CButton, B1Button, CIButton, B3Button, B2Button, MIButton, SSButton, OFButton, UButton, PButton);
       return selectionBox;
    }

    private void handleToggle()
    {

       updateTableEvent();

       
    }

    private void handleEditPlayer(MouseEvent e) 
    {
        if(e.getClickCount()==2)
        {
           Player p = (Player) playersTable.getSelectionModel().getSelectedItem();
           PlayerDialog fdt = new PlayerDialog(p, primaryStage, this);
           fdt.showDialog();
        }
    }


    public static ObservableList<Player> getMasterList() {
        return masterList;
    }

    private void handleAddPlayer() 
    {
        NewPlayerDialog npd= new NewPlayerDialog(primaryStage, this);
        npd.showDialog();
    }
    
    @Override
    public void onShow()
    {
        for(Player p : masterList)
        {
            p.calculateEstimatedValue();
        }
    }
}
