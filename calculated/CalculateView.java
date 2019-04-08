package mvp.calculated;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mvp.model.Fraktion;

public class CalculateView extends VBox
{

    private CalculatePresenter calPres;

    private DoubleProperty progressValue = new SimpleDoubleProperty();

    @SuppressWarnings("rawtypes")
    public CalculateView(ObservableList l)
    {
        initView(l);
    }

    @SuppressWarnings(
    { "unchecked", "rawtypes" })
    private void initView(ObservableList l)
    {
        Label title = new Label("Reputations Rechner");
        title.setFont(new Font("Arial", 20));
        Label prgText = new Label();
        getChildren().add(title);

        // Erstellen der Auswahlboxen
        HBox hbCombo = new HBox(5);

        ComboBox<Fraktion> fraktions = new ComboBox<Fraktion>();
        ComboBox<Integer> tier = new ComboBox<Integer>();

        tier.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        fraktions.getItems().addAll(l);
        tier.setValue(2);
        fraktions.setValue(fraktions.getItems().get(0));

        // OnChange for Tier
        tier.setOnAction(e -> {
            fraktions.getValue().setCurrentT(0);
            CalculateProgress((double) fraktions.getValue().getEXP("Tier " + tier.getValue()), (double) fraktions.getValue().getCurrentT());
            prgText.setText(fraktions.getValue().getCurrentT() + "/" + fraktions.getValue().getEXP("Tier " + tier.getValue()));
        });
        // OnChange for Faction
        fraktions.setOnAction(e -> {
            fraktions.getValue().setCurrentT(0);
            CalculateProgress((double) fraktions.getValue().getEXP("Tier " + tier.getValue()), (double) fraktions.getValue().getCurrentT());
            prgText.setText(fraktions.getValue().getCurrentT() + "/" + fraktions.getValue().getEXP("Tier " + tier.getValue()));

        });

        hbCombo.setPadding(new Insets(5));
        hbCombo.getChildren().add(fraktions);
        hbCombo.getChildren().add(tier);
        prgText.setText(fraktions.getValue().getCurrentT() + "/" + fraktions.getValue().getEXP("Tier " + tier.getValue()));
        getChildren().add(hbCombo);

        // Hinzufügen der Progressbar zum Layout
        VBox fortschrittBox = new VBox(5);

        ProgressBar fortschritt = new ProgressBar();
        fortschritt.progressProperty().bind(progressValue);
        fortschritt.progressProperty().addListener(new ChangeListener()
        {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2)
            {
               prgText.setText(fraktions.getValue().getCurrentT() + "/" + fraktions.getValue().getEXP("Tier " + tier.getValue()));

            }

        });
        fortschrittBox.getChildren().add(fortschritt);
        fortschrittBox.getChildren().add(prgText);

        getChildren().add(fortschrittBox);

        // <placeholder>
        HBox currentRep = new HBox(5);
        CheckBox haveRep = new CheckBox();
        haveRep.setText("Vorhandene Reputation");
        haveRep.setId("CB_Rep");
        TextField amountOfRep = new TextField();
        amountOfRep.setPromptText("Reputation");
        Button enterRep = new Button("Reputation Eintragen");
        enterRep.setOnAction(e -> EnterRep(fraktions.getValue(), Integer.valueOf(amountOfRep.getText()), tier.getValue()));
        
        currentRep.getChildren().add(haveRep);
        currentRep.getChildren().add(amountOfRep);
        currentRep.getChildren().add(enterRep);
        
        getChildren().add(currentRep);
        
        if(!haveRep.isSelected())
        {
            amountOfRep.setDisable(true);
            enterRep.setDisable(true);
        }
        else
        {
            amountOfRep.setDisable(false);
            enterRep.setDisable(false);
        }
        
        haveRep.setOnAction(e->{
            if(!haveRep.isSelected())
            {
                amountOfRep.setDisable(true);
                enterRep.setDisable(true);
                EnterRep(fraktions.getValue(), 0, tier.getValue());
                amountOfRep.clear();
            }
            else
            {
                amountOfRep.setDisable(false);
                enterRep.setDisable(false);
            }            
        });
           
        
       
        // Squad auswahl
        HBox squadBox = new HBox(5);
        CheckBox squad = new CheckBox();
        squad.setText("Spiele in Squad (25% Boost) ");
        squad.setId("Squad");
        squadBox.getChildren().add(squad);
        getChildren().add(squadBox);

        // Premium auswahl
        HBox premiumBox = new HBox();
        CheckBox premium = new CheckBox();
        premium.setText("Premium Konto (50% Boost)");
        premium.setId("Premium");
        premiumBox.getChildren().add(premium);
        getChildren().add(premiumBox);

        // Booster auswahl
        VBox boosters = new VBox(5);
        boosters.setPadding(new Insets(5));
        VBox boosterBox = new VBox();

        // checkbox für generelle verwendung von Boostern
        CheckBox boost = new CheckBox();
        boost.setText("Benutzung von Boostern");
        boost.setId("Boost");
        boosters.getChildren().add(boost);

        // erstellen der ToggleGroup
        ToggleGroup rep = new ToggleGroup();

        // Erstellen der RadioButtons
        RadioButton boostOne = new RadioButton();
        RadioButton boostTwo = new RadioButton();
        RadioButton boostThree = new RadioButton();
        RadioButton boostFour = new RadioButton();

        boostOne.setText("10% Booster");
        boostTwo.setText("25% Booster");
        boostThree.setText("50% Booster");
        boostFour.setText("75% Booster");
        
        boostOne.setId("rb0");
        boostTwo.setId("rb1");
        boostThree.setId("rb2");
        boostFour.setId("rb3");

        // Hinzufügen zur ToggleGroup
        boostOne.setToggleGroup(rep);
        boostTwo.setToggleGroup(rep);
        boostThree.setToggleGroup(rep);
        boostFour.setToggleGroup(rep);

        // hinzufügen zum Container BoosterBox
        boosterBox.getChildren().add(boostOne);
        boosterBox.getChildren().add(boostTwo);
        boosterBox.getChildren().add(boostThree);
        boosterBox.getChildren().add(boostFour);

        // angabe der anzahl verwendeter Booster
        HBox anzBoostBox = new HBox();
        Label beschreibungBoost = new Label();
        beschreibungBoost.setText("Anzahl vorhandener Booster:");
        TextField anzahl = new TextField();
        anzahl.setPromptText("Booster");
        anzahl.setId("AnzahlBooster");
        anzBoostBox.getChildren().add(beschreibungBoost);
        anzBoostBox.getChildren().add(anzahl);

        // Booster Layout beenden
        boosterBox.getChildren().add(anzBoostBox);
        boosters.getChildren().add(boosterBox);
        getChildren().add(boosters);

        // ein und ausblenden der Booster auswahl
        if (!boost.isSelected())
        {
            boosterBox.setVisible(false);
        }
        else
        {
            boosterBox.setVisible(true);
        }

        boost.setOnAction(e -> {
            if (!boost.isSelected())
            {
                boosterBox.setVisible(false);
            }
            else
            {
                boosterBox.setVisible(true);
            }
        });
        
        
        // Berechnung
        
        Button berechnen = new Button("Berechnen");
        
        EventHandler<ActionEvent> b= e->Calculate(fraktions.getValue().getCurrentT(),fraktions.getValue().getEXP("Tier " + tier.getValue()));
        
        berechnen.setOnAction(b);
        
        getChildren().add(berechnen);
    }

    public void setCalPres(CalculatePresenter p)
    {
        this.calPres = p;
    }

    public CalculatePresenter getCalPres()
    {
        return this.calPres;
    }

    // Berechnet den momentanten Fortschritt
    public void CalculateProgress(double totalEXP, double currentEXP)
    {
        try
        {
            progressValue.setValue(calPres.CalProgress(totalEXP, currentEXP));
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    public void EnterRep(Fraktion fraktion, Integer rep, int tier)
    {
        fraktion.setCurrentT(rep);
        CalculateProgress(fraktion.getEXP("Tier " + tier), fraktion.getCurrentT());
    }
    
    public void Calculate(int currentRep, int totalRep)
    {
        double boost=0;
        double squad=0;
        double premium=0;
        int anzahl=0;
        CheckBox booster= (CheckBox) lookup("#Boost");
        CheckBox squads= (CheckBox) lookup("#Squad");
        CheckBox premiums= (CheckBox) lookup("#Premium");
        
 
        // Feststellen der gewälten multiplikatoren 
        if (squads.isSelected())
        {
            squad=0.25;
        }
        if (premiums.isSelected())
        {
            premium=0.5;
        }
        if (booster.isSelected())
        {
            for(int i=0; i<=3; i++)
            {
                RadioButton rb = (RadioButton) lookup("#rb"+i);
                
                if (rb.isSelected() && i==0)
                {
                    boost=0.10;
                    break;
                }
                else if (rb.isSelected() && i==1)
                {
                    boost=0.25;
                    break;
                }
                else if (rb.isSelected() && i==2)
                {
                    boost=0.50;
                    break;
                }
                else if (rb.isSelected() && i==3)
                {
                    boost=0.75;
                    break;
                }
                else
                {
                    boost=0.0;
                    System.out.println("IDIOT !!");
                   
                }
            }
            TextField tf = (TextField) lookup("#AnzahlBooster");
            if (tf.getText().isEmpty())
            {
                boost=0.0;
            }
            else if (!tf.getText().isEmpty())
            {
                try
                {
                    anzahl = Integer.valueOf(tf.getText());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Alert wrongInput = new Alert(AlertType.ERROR);
                    wrongInput.setHeaderText("Eingabe Fehler");
                    wrongInput.setContentText("Sie haben keine anzahl Booster angegeben!");
                    wrongInput.showAndWait();
                    return;
                }
            }
        }
        //System.out.println("die if´s laufen durch");
        int rounds =calPres.berechnen(currentRep, boost, squad, premium, anzahl , totalRep);
        //System.out.println(rounds);
        
        Alert end = new Alert(AlertType.INFORMATION);
        
        end.setHeaderText("");
        end.setContentText("Sie benötigen "+rounds+" Runden um aufzusteigen.");
        end.showAndWait();
        
        
    }
}
