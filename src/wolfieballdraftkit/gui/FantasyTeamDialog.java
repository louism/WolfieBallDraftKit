

package wolfieballdraftkit.gui;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import woflieballdraftkit.data.Team;

/**
 *
 * @author Louis
 */
public class FantasyTeamDialog extends Stage
{
    Label headerLabel;
    Label nameLabel;
    Label ownerLabel;
    
    TextField nameField;
    TextField ownerField;
    
    Button completeButton;
    Button cancelButton;
    
    VBox dialogPane;
    GridPane gridPane;
    
    Stage primaryStage;
    
    Team teamToEdit;
    
    public FantasyTeamDialog(Team team, Stage primaryStage)
    {
         initModality(Modality.WINDOW_MODAL);
         initOwner(primaryStage);
         this.primaryStage = primaryStage;
         
        //init leabels and fields
        headerLabel = new Label("Fantasy Team Details");
        headerLabel.setFont(new Font("Arial", 20));
        nameLabel = new Label("Name: ");
        nameLabel.setFont(new Font("Arial", 16));
        ownerLabel = new Label("Owner: ");
        ownerLabel.setFont(new Font("Arial", 16));
        
        nameField = new TextField();
        ownerField = new TextField();
        
        completeButton = new Button("Complete");
        cancelButton = new Button("Cancel");
        
        completeButton.setOnAction(e -> handleComplete());
        cancelButton.setOnAction(e -> handleCancel());
        
        gridPane = new GridPane();
        
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        
        gridPane.add(ownerLabel, 0, 1);
        gridPane.add(ownerField, 1, 1);
        
        gridPane.add(completeButton, 0, 2);
        gridPane.add(cancelButton, 1, 2);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        teamToEdit = team;
        
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
       String name = nameField.getText();
       String owner = ownerField.getText();

       if(name.equals("") || owner.equals(""))
       {
        MessageDialog ed = new MessageDialog(this, "OK");
        ed.show("Both fields must be filled out");
        return;
       }
       
       teamToEdit.setName(name);
       teamToEdit.setOwner(owner);
       
       this.close();
    }

    private void handleCancel()
    {
     this.close();
    }

    void setTeam(Team teamToEdit) {
      nameField.setText(teamToEdit.getName());
      ownerField.setText(teamToEdit.getOwner());
    }
}
