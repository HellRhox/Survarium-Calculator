package mvp.main;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EventObject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MainView extends BorderPane
{
    private MainPresenter mainPresenter;
    
    public MainView()
    {
        initView();
    }
    
    private void initView()
    {   
        // Initialisieren des Menüs
        VBox topArea = new VBox();
        
        MenuBar menu= new MenuBar();
        
        Platform.runLater(()->menu.setUseSystemMenuBar(true));
        Menu anzeige= new Menu("Anzeigen");
        Menu menue= new Menu("Menü");
        
        MenuItem exit = new MenuItem("Beenden");
        exit.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                System.exit(0);
            }
        });
        
        MenuItem startView = new MenuItem("Startseite");
        MenuItem calculatorView = new MenuItem("Brechnen");

        
        startView.setOnAction(e ->mainPresenter.showStartView());
        calculatorView.setOnAction(e ->mainPresenter.showCalView());
        
        EventHandler<ActionEvent> s = e ->creditDialog();
        MenuItem credit = new MenuItem("Credits");
        credit.setOnAction(s);
        
        menue.getItems().add(credit);
        menue.getItems().add(exit);
        
        anzeige.getItems().add(startView);
        anzeige.getItems().add(calculatorView);
        
        
        menu.getMenus().add(menue);
        menu.getMenus().add(anzeige);
        
        topArea.getChildren().addAll(menu);
        setTop(topArea);
        
        // initalisieren der bottom Area 
        HBox bottomArea= new HBox();
        Hyperlink link= new Hyperlink("www.Survarium.com"); // Link zur Survarium Seite 
        Hyperlink link2= new Hyperlink("www.Survarium.pro");//Link zur Statistik Seite Survarium Pro
        
        EventHandler<ActionEvent> t = e ->openLink(((Labeled) ((EventObject)e).getSource()).getText()) ;
        
        link.setOnAction(t);
        link2.setOnAction(t);
        
        Label version = new Label();
        version.setText("v.0.0.2");
        version.setAlignment(Pos.BOTTOM_RIGHT);
        
        Label disclaimer = new Label();
        disclaimer.setText("  Entspricht noch nicht dem Finalen zustand  ");
        
        bottomArea.getChildren().add(link);
        bottomArea.getChildren().add(link2);
        bottomArea.getChildren().add(disclaimer);
        bottomArea.getChildren().add(version);
        
        
        bottomArea.setAlignment(Pos.BOTTOM_RIGHT);
        
        
        
        setBottom(bottomArea);
        
    }
    
    public void setContent(VBox v)
    {
        setCenter(v);
    }
    
    public MainPresenter getMainPresenter()
    {
        return this.mainPresenter;
    }
    
    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter=mainPresenter;
    }
    
    public void openLink(String s) // Methode zum Öffnen der Links 
    {
        try
        {
            Desktop.getDesktop().browse(new URI(s)); 
        }
        catch (IOException | URISyntaxException e)
        {                    
            e.printStackTrace();
            Alert error= new Alert(AlertType.ERROR);
            error.setTitle("Fehler");
            error.setHeaderText("Ein Fehler beim Öffnen des Links");
            error.setContentText("");
            error.showAndWait();
            
        }
    }
    
    private void creditDialog() // Methode zum erstellen und öffnen des Menüpunktes Credit
    {
        Alert credit = new Alert(AlertType.INFORMATION);
        
        credit.setHeaderText("Credits");
        credit.setContentText("Geschrieben von: HellRhox");
        
        credit.showAndWait();
    }

}
