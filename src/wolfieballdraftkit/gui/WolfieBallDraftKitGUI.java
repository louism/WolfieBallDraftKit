/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wolfieballdraftkit.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import woflieballdraftkit.data.Player;
import woflieballdraftkit.data.Team;
import woflieballdraftkit.data.WolfieBallDraftKitDataManager;
import wolfieballdraftkit.image.ImageManager;

/**
 *
 * @author Louis
 */
public class WolfieBallDraftKitGUI
{
    Stage primaryStage;
    Scene primaryScene;
    
    PlayersScreen playersScreen;
    TeamsScreen teamsScreen;
    DraftScreen draftScreen;
    FantasyStandingsScreen fantasyStandingsScreen;
    MLBScreen mlbScreen;
    
    WolfieBallDraftKitDataManager dataManager;
    
    BorderPane mainPane;
    public static FlowPane currentScreen;
    
    HBox toolbarBox;
    HBox screenToolbar;
    
    //Controls for the top toolbar
    Button newButton;
    Button openButton;
    Button saveButton;
    Button exportButton;
    Button quitButton;
    
    //Controls for the bottom toolbar
    Button fantasyTeamsButton;
    Button PlayersButton;
    Button fantasyStandingsButton;
    Button draftSummaryButton;
    Button mlbButton;
    
    //
    Label playersHeaderLabel;
    Label teamsHeaderLabel;
    Label draftHeaderLabel;
    Label standingsHeaderLabel;
    Label mlbHeaderLabel;
    
    ObservableList<Player> visibleList;
    
    VBox playersBox;
    VBox teamsBox;
        VBox draftBox;
    VBox standingsBox;
        VBox mlbBox;

    TableView playersTable;
    
    public static double width = 1120;
    public static double height = 700;

    public WolfieBallDraftKitGUI(Stage primaryStage, WolfieBallDraftKitDataManager dataManager)
    {
       this.primaryStage = primaryStage;
       this.dataManager = dataManager;

    }

    public void initGui()
    {
        //Setup the toolbar
        initToolbar();
        //Setup the window
        initWindow();
        
        
    }

    private void initWindow()
    {
        // SET THE WINDOW TITLE
        primaryStage.setTitle("WolfieBallDraftKit");

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.centerOnScreen();
        
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        //Setup the main pane
        mainPane = new BorderPane();
        primaryScene = new Scene(mainPane);
       
        
        mainPane.setTop(toolbarBox);
        primaryStage.setScene(primaryScene);
        primaryStage.getIcons().add(ImageManager.wolfieBallIcon);
        primaryStage.show();
    }

    private void initToolbar()
    {
       toolbarBox = new HBox();
       //init the buttons
       newButton = initButton(newButton, "New Draft", ImageManager.newIcon);
       saveButton = initButton(saveButton, "Save Draft", ImageManager.saveIcon);
       openButton = initButton(openButton, "Open Draft", ImageManager.openIcon);
       exportButton = initButton(exportButton, "Export Draft", ImageManager.exportIcon);
       quitButton = initButton(quitButton, "Quit Program", ImageManager.quitIcon);
       toolbarBox.getChildren().addAll(newButton, saveButton, openButton, exportButton, quitButton);
       
       //Setup Events
       
       newButton.setOnAction(e -> createNewDraft());
       saveButton.setOnAction(e  -> saveDraft());
       openButton.setOnAction(e -> loadDraft());
    }

    private Button initButton(Button button, String tooltipText, Image icon)
    {
        button = new Button();
        button.setGraphic(new ImageView(icon));
        Tooltip tooltip = new Tooltip(tooltipText);
        button.setTooltip(tooltip);
        return button;
    }

    private void createNewDraft()
    {
                dataManager.loadPlayers();
                currentScreen = new FlowPane();
                
        fantasyStandingsScreen = new FantasyStandingsScreen();
        playersScreen = new PlayersScreen(dataManager.getHitters(), primaryStage);
        teamsScreen = new TeamsScreen(primaryStage, playersScreen, fantasyStandingsScreen);
         draftScreen = new DraftScreen(playersScreen, teamsScreen, primaryStage);
       mlbScreen = new MLBScreen();
        initScreenToolbar();


        
        mainPane.setBottom(screenToolbar);
        mainPane.setCenter(currentScreen);
        currentScreen.setStyle("-fx-border-color: black; -fx-border-insets: 5, 5, 5, 5; -fx-background-color: lightskyblue;");
        showScreen(teamsScreen);
    }
    
    
        
    



    private void loadPlayersIntoTable()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void showScreen(ScreenPane p)
    {
        currentScreen.getChildren().clear();
        currentScreen.getChildren().add(p);
        p.setColor(currentScreen);
        p.onShow();
    }

    private void initScreenToolbar()
    {
       screenToolbar = new HBox();
       
    
              //init the buttons
       fantasyTeamsButton = initButton(fantasyTeamsButton, "Fantasy Teams", ImageManager.fantasyTeamsIcon);
       PlayersButton = initButton(PlayersButton, "Available Players", ImageManager.playersIcon);
       fantasyStandingsButton = initButton(fantasyStandingsButton, "Fantasy Standings", ImageManager.fantasyStandingsIcon);
       draftSummaryButton = initButton( draftSummaryButton, "Draft Summary", ImageManager.draftSummaryIcon);
       mlbButton = initButton(mlbButton, "MLB Teams", ImageManager.mlbIcon);
       screenToolbar.getChildren().addAll(fantasyTeamsButton, PlayersButton, fantasyStandingsButton, draftSummaryButton, mlbButton);
       
       //Setup Events
       fantasyTeamsButton.setOnAction(e -> showScreen(teamsScreen));
       PlayersButton.setOnAction(e -> showScreen(playersScreen));
       draftSummaryButton.setOnAction(e -> showScreen(draftScreen));
       fantasyStandingsButton.setOnAction(e -> showScreen(fantasyStandingsScreen));
       mlbButton.setOnAction(e -> showScreen(mlbScreen));
    }

    private Label initHeaderLabel(Label headerLabel)
    {
        headerLabel = new Label();
        headerLabel.setFont(new Font("Arial", 20));
        return headerLabel;
    }

    private void saveDraft()
    {
        try {
            String fileName = teamsScreen.draftTeamNameField.getText();
            FileWriter fw = new FileWriter(fileName + ".txt");
            for(Team t: TeamsScreen.teamsList)
            {
                String teamString = "#" + t.getName() + ":" + t.getOwner() + "\n";
                String newString = teamString.replace(' ', '_');
                fw.write(newString);
                for(Player p: t.players)
                {
                    fw.write(p.getFirstName() + ":" + p.getLastName() + ":" + p.getChosenPosition() + ":" + p.getContract() + ":" + p.getSalary() + "\n");
                    
                }
                for(Player p: t.getTaxiSquad())
                {
                    fw.write(p.getFirstName() + ":" + p.getLastName() + ":" + p.getChosenPosition() + ":" + p.getContract() + ":" + p.getSalary() + "\n");
                }
            }
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(WolfieBallDraftKitGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDraft()
    {
        createNewDraft();
        FileChooser chooser = new FileChooser();
        File fileToOpen = chooser.showOpenDialog(primaryStage);
              FileInputStream fis; 
        try
        {
            ArrayList<Team> teamsList = new ArrayList<Team>();
            fis = new FileInputStream(fileToOpen);
            InputStreamReader inStream = new InputStreamReader(fis);
            Scanner sc = new Scanner(inStream);
            Team teamToEdit = null;
            while(sc.hasNext())
            {
                String line = sc.next();
                if(line.startsWith("#"))
                {
                    System.out.println(line);
                    String[] parseTeam = line.split(":");
                    String teamName1 = parseTeam[0].substring(1);
                    String teamName = teamName1.replace('_', ' ');
                    String ownerName = parseTeam[1];
                    Team team = new Team();
                    team.setName(teamName);
                    team.setOwner(ownerName);
                    teamToEdit = team;

                    teamsList.add(teamToEdit);
                }
                else
                {
                    String[] parsePlayer = line.split(":");
                    String firstName = parsePlayer[0];
                    String lastName = parsePlayer[1];
                    String position = parsePlayer[2];
                    String contract = parsePlayer[3];
                    String salary = parsePlayer[4];
                    Player p = Team.lookUpPlayer(firstName.toLowerCase() + lastName.toLowerCase(), PlayersScreen.getMasterList());
                    if(p==null)
                    {
                        p = new Player(firstName, lastName, position, "-");
                    }
                    p.setChosenPosition(position);
                    p.setContract(contract);
                    p.setSalary(Double.parseDouble(salary));
                    if(position.equals("Taxi"))
                    {
                        teamToEdit.addTaxiPlayer(p);
                    }
                    else
                    {
                    teamToEdit.addPlayer(p, position);
                    }
                    PlayersScreen.getMasterList().remove(p);
                }
            }
            
            teamsScreen.loadTeams(teamsList);
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

    }
    
}
