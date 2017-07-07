

package wolfieballdraftkit.gui;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import woflieballdraftkit.data.Player;
import woflieballdraftkit.data.Team;

/**
 *
 * @author Louis
 */
public class NewPlayerDialog extends Stage
{
    Label headerLabel;
    Label firstNameLabel;
    Label lastNameLabel;
    Label proTeam;
    
    CheckBox cBox;
    CheckBox b1Box;
    CheckBox b3Box;
    CheckBox b2Box;
    CheckBox ssBox;
    CheckBox ofBox;
    CheckBox pBox;
    
    TextField firstNameField;
    TextField lastNameField;
    ComboBox<String> proTeamBox;
    
    Button completeButton;
    Button cancelButton;
    
    HBox positionPane;
    HBox buttonPane;
    
    VBox dialogPane;
    GridPane gridPane;
    
    Stage primaryStage;
    
    PlayersScreen ps;
    Team teamToEdit;
    
    public NewPlayerDialog(Stage primaryStage, PlayersScreen ps)
    {
         this.ps = ps;
         initModality(Modality.WINDOW_MODAL);
         initOwner(primaryStage);
         this.primaryStage = primaryStage;
         
        //init leabels and fields
        headerLabel = new Label("New Player");
        headerLabel.setFont(new Font("Arial", 20));
        firstNameLabel = new Label("First Name: ");
        firstNameLabel.setFont(new Font("Arial", 16));
        lastNameLabel = new Label("Last Name: ");
        lastNameLabel.setFont(new Font("Arial", 16));
        proTeam = new Label("Pro Team: ");
        proTeam.setFont(new Font("Arial", 16));
        
        
        firstNameField = new TextField();
        lastNameField = new TextField();
        
                proTeamBox = new ComboBox<String>();
        setProTeams();
        
        completeButton = new Button("Complete");
        cancelButton = new Button("Cancel");
        
        completeButton.setOnAction(e -> handleComplete());
        cancelButton.setOnAction(e -> handleCancel());
        
        gridPane = new GridPane();
        
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameField, 1, 0);
        
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameField, 1, 1);
        
                
        gridPane.add(proTeam, 0, 2);
        gridPane.add(proTeamBox, 1, 2);
        

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        cBox = new CheckBox("C");
       b1Box = new CheckBox("1B");
        b3Box = new CheckBox("3B");
       b2Box = new CheckBox("2B");
      ssBox = new CheckBox("SS");
    ofBox = new CheckBox("OF");
    pBox = new CheckBox("P");
    
        positionPane = new HBox();
        positionPane.getChildren().addAll(cBox, b1Box, b3Box, b2Box, ssBox, ofBox, pBox);
        positionPane.setSpacing(5);
        
        buttonPane = new HBox();
        buttonPane.getChildren().addAll(completeButton, cancelButton);
        buttonPane.setSpacing(5);
        
        dialogPane = new VBox();
        dialogPane.getChildren().addAll(headerLabel, gridPane, positionPane, buttonPane);

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
       String firstName = firstNameField.getText();
       String lastName = lastNameField.getText();
       String proTeam = proTeamBox.getSelectionModel().getSelectedItem();
       String position = buildPosition();
       if(firstName.equals("") || lastName.equals(""))
       {
        MessageDialog ed = new MessageDialog(this, "OK");
        ed.show("All fields must be filled out");
        return;
       }
       
       Player player = new Player(firstName, lastName, position, proTeam);
       PlayersScreen.getMasterList().add(player);
       ps.updateTableEvent();
       this.close();
    }

    private void handleCancel()
    {
     this.close();
    }

    private void setProTeams() 
    {
        proTeamBox.getItems().addAll("ATL", "AZ", "CHC", "CIN", "COL", "LAD", "MIA", "MIL", "NYM", "PHI", "PIT", "SD", "SF", "STL", "WAS");
    }

    private String buildPosition() 
    {
        String posString = "";
        if(cBox.isSelected())
        {
            posString += "C_";
        }
                if(b1Box.isSelected())
        {
            posString += "1B_";
        }
                        if(b2Box.isSelected())
        {
            posString += "2B_";
        }
                                if(b3Box.isSelected())
        {
            posString += "3B_";
        }
        if(ssBox.isSelected())
        {
            posString += "SS_";
        }
                if(ofBox.isSelected())
        {
            posString += "OF_";
        }
                        if(pBox.isSelected())
        {
            posString += "P_";
        }

        String newString = posString.substring(0, posString.length());
        return newString;
    }


}
