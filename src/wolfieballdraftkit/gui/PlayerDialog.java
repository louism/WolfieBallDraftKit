

package wolfieballdraftkit.gui;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import woflieballdraftkit.data.Player;
import woflieballdraftkit.data.Team;
import wolfieballdraftkit.image.ImageManager;

/**
 *
 * @author Louis
 */
public class PlayerDialog extends Stage
{
    Label headerLabel;
    
    boolean editing;
    
    Label playerName;
    Label playerPosition;
    
    Label teamLabel;
    Label positionLabel;
    Label contractLabel;
    Label salaryLabel;

    ImageView playerPic;
    ImageView flagPic;
    
    Team oldTeam;
    
    ComboBox<Team> teamBox;
    ComboBox<String> positionBox;
    ComboBox<String> contractBox;
    
    TextField salaryField;
    
    Button completeButton;
    Button cancelButton;
    
    VBox dialogPane;
    GridPane gridPane;
    
    Stage primaryStage;
    
    Player playerToEdit;
    Team teamToEdit;
    
    PlayersScreen ps;
    
    public PlayerDialog(Player player, Stage primaryStage, PlayersScreen ps)
    {
        editing = false;
        this.playerToEdit = player;
         initModality(Modality.WINDOW_MODAL);
         initOwner(primaryStage);
         this.primaryStage = primaryStage;
         this.ps =ps;
        //init leabels and fields
        headerLabel = new Label("Player Details");
        headerLabel.setFont(new Font("Arial", 20));

        initImages(playerPic, flagPic);
        
        playerName = new Label(player.getFirstName() + " " + player.getLastName());
        playerName.setFont(new Font("Arial", 16));
        
        playerPosition= new Label(player.getPosition());
        playerPosition.setFont(new Font("Arial", 16));
        
        teamLabel = initLabel(teamLabel, "Team", 14);
        positionLabel = initLabel(positionLabel, "Position", 14);
        contractLabel = initLabel(contractLabel, "Contract", 14);
        salaryLabel = initLabel(salaryLabel, "Salary", 14);
        
        teamBox = new ComboBox();
        positionBox = new ComboBox();
        contractBox  = new ComboBox();

        teamBox.setItems(FXCollections.observableArrayList(TeamsScreen.teamsList));
        initContractBox();
        
        salaryField = new TextField();
        
        completeButton = new Button("Complete");
        cancelButton = new Button("Cancel");
        
        teamBox.setOnAction(e -> handleChangeTeam());
        positionBox.setOnMouseClicked(e -> handleChangePosition());
        completeButton.setOnAction(e -> handleComplete());
        cancelButton.setOnAction(e -> handleCancel());
        
        gridPane = new GridPane();
        
        gridPane.add(playerPic, 0, 0);
        gridPane.add(flagPic, 1, 0);
        gridPane.add(playerName, 0, 1);
        gridPane.add(playerPosition, 1, 1);
        
        gridPane.add(teamLabel, 0, 2);
        gridPane.add(teamBox, 1, 2);
        gridPane.add(positionLabel, 0, 3);
        gridPane.add(positionBox, 1, 3);
        gridPane.add(contractLabel, 0, 4);
        gridPane.add(contractBox, 1, 4);
        gridPane.add(salaryLabel, 0, 5);
        gridPane.add(salaryField, 1, 5);
        
        gridPane.add(completeButton, 0, 6);
        gridPane.add(cancelButton, 1, 6);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        

        
        dialogPane = new VBox();
        dialogPane.getChildren().addAll(headerLabel, gridPane);

        dialogPane.setPadding(new Insets(10, 20, 20, 20));
        dialogPane.setSpacing(10);
        
        Scene scene = new Scene(dialogPane);
        this.setScene(scene);
    }
    
    
    public void showDialog()
    {
        this.showAndWait();
    }

    private void handleComplete()
    {
             if(teamBox.getSelectionModel().getSelectedItem() == null || positionBox.getSelectionModel().getSelectedItem() == null
                   ||  contractBox.getSelectionModel().getSelectedItem() == null || salaryField.getText().equals(""))
       {
         MessageDialog ed = new MessageDialog(this, "OK");
        ed.show("All fields must be completed");

       }
             playerToEdit.setContract(contractBox.getSelectionModel().getSelectedItem());
             playerToEdit.setChosenPosition(positionBox.getSelectionModel().getSelectedItem());
            
             try{
             playerToEdit.setSalary(Double.parseDouble((salaryField.getText())));
             }catch(Exception e)
             {
                          MessageDialog ed = new MessageDialog(this, "OK");
               ed.show("A number must be inputted for the salary");
               return;
             }
             
             ps.updateTableEvent();

             if(!editing)
             {
             playerToEdit.setFantasyName(teamToEdit.getName());
             if(positionBox.getSelectionModel().getSelectedItem().equals("Taxi Squad"))
             {
                 
                 if(!contractBox.getSelectionModel().getSelectedItem().equals("X"))
                 {
                                               MessageDialog ed = new MessageDialog(this, "OK");
               ed.show("Taxi squad can only have an X contract");
               return;
                 }
                 teamToEdit.addTaxiPlayer(playerToEdit);
             }
             else
             {
             teamToEdit.addPlayer(playerToEdit, positionBox.getSelectionModel().getSelectedItem());
             }
             }
             else if(positionBox.getSelectionModel().getSelectedItem().equals("Free Agent"))
             {
                 playerToEdit.setContract("free");
                 teamToEdit.removePlayer(playerToEdit, positionBox.getSelectionModel().getSelectedItem());
                 PlayersScreen.getMasterList().add(playerToEdit);
                 ps.updateTableEvent();
             }
             else if(oldTeam != teamToEdit)
             {
                 playerToEdit.setFantasyName(teamToEdit.getName());
                 oldTeam.removePlayer(playerToEdit, positionBox.getSelectionModel().getSelectedItem());
                 teamToEdit.addPlayer(playerToEdit, positionBox.getSelectionModel().getSelectedItem());
             }
            if(playerToEdit.getContract().equals("S2") || playerToEdit.getContract().equals("X"))
            {
               DraftScreen.getDraftTable().getItems().add(playerToEdit);
            }
            PlayersScreen.getMasterList().remove(playerToEdit);
             TeamsScreen.refreshTable();
             this.close();
    }

    private void handleCancel()
    {
     this.close();
    }

    public void initImages(ImageView playerImg, ImageView flagImg)
    {
        Image image1;
        Image image2;
        
        try{
        image1 = new Image("wolfieball_images/players/" + playerToEdit.getLastName() + playerToEdit.getFirstName() + ".jpg");
       image2 = new Image("wolfieball_images/flags/" + playerToEdit.getNationOfBirth() + ".png");

        }
        catch(Exception e)
        {
            image1 = ImageManager.defaultPic;
            image2 = ImageManager.defaultFlag;
        }
       playerPic = new ImageView(image1);
       flagPic = new ImageView(image2);
    }
    
        public Label initLabel(Label label, String text, int size)
    {
        label = new Label();
        label.setFont(new Font("Arial", size));
        label.setText(text);
        return label;
    }

    private void handleChangePosition()
    {
       if(teamBox.getSelectionModel().getSelectedItem() == null)
       {
         MessageDialog ed = new MessageDialog(this, "OK");
        ed.show("Team must be selected first");

       }
       
       
    }

    private void handleChangeTeam() 
    {
        this.teamToEdit = teamBox.getSelectionModel().getSelectedItem();
        positionBox.setItems(FXCollections.observableList(teamToEdit.getAvailiblePositions(playerToEdit.getPosition())));
    }

    private void initContractBox() 
    {
       contractBox.setItems(FXCollections.observableArrayList("S1", "S2", "X"));
    }

    void setPlayer(Player p, Team t) 
    {
        teamBox.getSelectionModel().select(t);
        contractBox.getSelectionModel().select(p.getContract());
        positionBox.getSelectionModel().select(p.getChosenPosition());
        positionBox.getItems().add("Free Agent");
        oldTeam = t;
        editing = true;
        salaryField.setText(p.getSalary().toString());
        
    }
}
