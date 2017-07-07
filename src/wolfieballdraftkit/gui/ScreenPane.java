/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 *
 * @author Louis
 */
abstract class ScreenPane extends Pane
{
        public void setColor(Pane pane)
    {
      pane.setStyle("-fx-border-color: black; -fx-border-insets: 5, 5, 5, 5; -fx-background-color: lightskyblue;");
    }
        
    public Label initHeaderLabel(Label headerLabel)
    {
        headerLabel = new Label();
        headerLabel.setFont(new Font("Arial", 20));
        return headerLabel;
    }
    
        public Label initLabel(Label label, int size)
    {
        label = new Label();
        label.setFont(new Font("Arial", size));
        return label;
    }
    
    public Button initButton(Button button, String tooltipText, Image icon)
    {
        button = new Button();
        button.setGraphic(new ImageView(icon));
        Tooltip tooltip = new Tooltip(tooltipText);
        button.setTooltip(tooltip);
        return button;
    }
    
        public TextField initTextField(TextField field, int width)
    {
        field = new TextField();
        field.setPrefWidth(width);
        return field;
    }

    void onShow() 
    {
        
    }
}
