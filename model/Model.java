package mvp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model
{
    private ObservableList<Fraktion> faktions =FXCollections.observableArrayList();

    
    
    public Model()
    {
        Fraktion scavenger= new Fraktion("Scavenger");
        Fraktion blackMarket= new Fraktion("Black Market");
        Fraktion renaissanceArmy= new Fraktion("Renaissance Army");
        Fraktion fringSettlers= new Fraktion("Fring Settlers");
        
        faktions.add(scavenger);
        faktions.add(blackMarket);
        faktions.add(renaissanceArmy);
        faktions.add(fringSettlers);
    }
    
    
    public Fraktion getFraktion(int i)
    {
        return faktions.get(i);
    }
    
    public ObservableList<Fraktion> getFaktions()
    {
        return faktions;
    }
}
