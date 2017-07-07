/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import woflieballdraftkit.data.WolfieBallDraftKitDataManager;
import wolfieballdraftkit.file.JsonPlayerManager;
import wolfieballdraftkit.gui.WolfieBallDraftKitGUI;

/**
 *
 * @author Louis
 */
public class WolfieBallDraftKit extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        //Initialize the GUI
       JsonPlayerManager jpm = new JsonPlayerManager();
       WolfieBallDraftKitDataManager dataManager = new WolfieBallDraftKitDataManager(jpm);
       WolfieBallDraftKitGUI gui = new WolfieBallDraftKitGUI(primaryStage, dataManager);
       gui.initGui();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
    
}
