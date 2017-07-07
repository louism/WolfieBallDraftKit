/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit.gui;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import woflieballdraftkit.data.Player;
import woflieballdraftkit.data.Team;
import wolfieballdraftkit.image.ImageManager;

/**
 *
 * @author Louis
 */
public class DraftScreen extends ScreenPane
{
    private VBox draftBox;
    private Label draftHeaderLabel;
    
    private Button pickButton;
    private Button playButton;
    private Button pauseButton;
    
    Random r = new Random();
    
    private HBox controlBox;
    
    private static TableView<Player> draftTable;
    private ObservableList<Player> players;
    
    PlayersScreen ps;
    TeamsScreen ts;
    Stage primaryStage;
    
    AutoThread currentThread;
    
    public DraftScreen(PlayersScreen ps, TeamsScreen ts, Stage primaryStage)
    {
        this.ps = ps;
        this.ts = ts;
       draftBox = new VBox();
       draftBox.setPadding(new Insets(10, 10, 10, 10));
       draftBox.setSpacing(10);
       this.primaryStage = primaryStage;
       
       players = FXCollections.observableArrayList();
       
       controlBox = initDraftControls(controlBox);
       draftTable = new TableView<>();
       
       TableColumn numberCol = new TableColumn("Pick #");
       numberCol.setCellValueFactory(new Callback<CellDataFeatures<String, String>, ObservableValue<String>>() {
       @Override public ObservableValue<String> call(CellDataFeatures<String, String> p) 
       {
    return new ReadOnlyObjectWrapper(draftTable.getItems().indexOf(p.getValue()) + "");
  }
}); 
       TableColumn firstNameCol = new TableColumn("First Name");
       TableColumn lastNameCol = new TableColumn("Last Name");
       TableColumn teamNameCol = new TableColumn("Team Name");
       TableColumn contractCol = new TableColumn("Contract");
       TableColumn salaryCol = new TableColumn("Salary");
       
       teamNameCol.setPrefWidth(80);
       firstNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
       lastNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
       teamNameCol.setCellValueFactory(new PropertyValueFactory<String, String>("fantasyName"));
       contractCol.setCellValueFactory(new PropertyValueFactory<String, String>("contract"));
       salaryCol.setCellValueFactory(new PropertyValueFactory<String, String>("salary"));
       
       draftTable.getColumns().addAll(numberCol, firstNameCol, lastNameCol, teamNameCol, contractCol, salaryCol);
       draftHeaderLabel = initHeaderLabel(draftHeaderLabel);
       draftHeaderLabel.setText("Draft Summary");
       draftBox.getChildren().addAll(draftHeaderLabel, controlBox, draftTable);
       this.getChildren().add(draftBox);
    }
            public void setColor(Pane pane)
    {
      pane.setStyle("-fx-border-color: black; -fx-border-insets: 5, 5, 5, 5; -fx-background-color: thistle;");
    }

    private HBox initDraftControls(HBox controlBox) 
    {
        controlBox = new HBox();
         pickButton = initButton(pickButton, "Pick Player", ImageManager.chooseIcon);
         pickButton.setOnAction(e -> handlePickPlayerEvent());
         playButton = initButton(playButton, "Start AutoDraft", ImageManager.playIcon);
         playButton.setOnAction(e -> handleStartAutoDraftEvent());
         pauseButton = initButton(pauseButton, "Pause AutoDraft", ImageManager.pauseIcon);
         pauseButton.setOnAction(e -> handlePauseAutoDraftEvent());
         controlBox.getChildren().addAll(pickButton, playButton, pauseButton);
         return controlBox;
    }

    private void handlePickPlayerEvent() 
    {
       boolean success = pickPlayer();
       if(!success)
       {
                   MessageDialog ed = new MessageDialog(primaryStage, "OK");
        ed.show("All teams are full; no players were drafted");
       }
    }
    
    private void handleStartAutoDraftEvent()
    {
        if(currentThread == null || !currentThread.isAlive())
        {
        AutoThread autoThread = new AutoThread();
        autoThread.start();
        currentThread = autoThread;
        }
    }
    
    @Override
    void onShow()
    {
        ObservableList<Player> tempList = FXCollections.observableArrayList(draftTable.getItems());

        
                
        for(Player p : tempList)
        {
            if(!p.getContract().equals("S2") || !p.getContract().equals("X"))
            {
               draftTable.getItems().remove(p);
            }
        }

    }
    
    public static TableView<Player> getDraftTable()
    {
        return draftTable;
    }

    private boolean pickPlayer()
    {
         boolean chosen = false;
     for(Team t: ts.getTeams())
     {
         while(!chosen)
         {

            Player randomPlayer = PlayersScreen.getMasterList().get(r.nextInt(PlayersScreen.getMasterList().size()));
           if(t.getPlayers().size()<23)
           {
            if(t.getAvailiblePositions(randomPlayer.getPosition()).size()>0)
            {

               String pos = t.getAvailiblePositions(randomPlayer.getPosition()).get(0);
               randomPlayer.setFantasyName(t.getName());
               randomPlayer.setChosenPosition(pos);
               randomPlayer.setContract("S2");
               randomPlayer.setSalary(1.0);
               t.addPlayer(randomPlayer, pos);
                            draftTable.getItems().add(randomPlayer);
                                        chosen = true;
                                        PlayersScreen.getMasterList().remove(randomPlayer);
            
            }
            }
            else
            {
               break;
            }
         }
     }
     
     for(Team t: ts.getTeams())
     {
         while(!chosen)
         {
                        Player randomPlayer = PlayersScreen.getMasterList().get(r.nextInt(PlayersScreen.getMasterList().size()));
            
                if(t.getTaxiSquad().size()<8)
                {
               randomPlayer.setTeam(t.getName());
               randomPlayer.setContract("X");
                              randomPlayer.setSalary(1.0);
                              randomPlayer.setFantasyName(t.getName());
                draftTable.getItems().add(randomPlayer);
               PlayersScreen.getMasterList().remove(randomPlayer);
               t.addTaxiPlayer(randomPlayer);
                                        chosen = true;
                }
                else
                {
                    break;
                }
            }
         
     }
     return chosen;
    }

    private void handlePauseAutoDraftEvent()
    {
                if(currentThread == null || currentThread.isAlive())
        {
        currentThread.setDead();
        }
    }

    private  class AutoThread extends Thread {

        boolean isAlive;
        public AutoThread() {
            isAlive = true;
        }
        
        public void setDead()
        {
            isAlive = false;
        }
        
        @Override
        public void run()
        {
            while(isAlive)
            {
                       boolean success = pickPlayer();
        if(!success)
       {
                   isAlive = false;
           Platform.runLater(new Runnable() {

               @Override
               public void run() {
                                      MessageDialog ed = new MessageDialog(primaryStage, "OK");
        ed.show("All teams are full; no players were drafted");
               }
           });

          }
                try {
                    Thread.sleep(350);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DraftScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
