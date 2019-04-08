package mvp.main;

import javafx.collections.ObservableList;
import mvp.calculated.CalculateView;
import mvp.model.Fraktion;
import mvp.model.Model;

public class MainPresenter
{
    private MainView mainView;
    
    private StartView startView;
    
    private CalculateView calView;
    
    private Model model;
    
    public void setMainView(MainView mainView)
    {
        this.mainView=mainView;
    }
    
    public MainView getView()
    {
        return this.mainView;
    }

    public Model getModel()
    {
        return model;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public StartView getStartView()
    {
        return startView;
    }

    public void setStartView(StartView startView)
    {
        this.startView = startView;
    }
    
    public void showStartView()
    {
        mainView.setContent(startView);
    }

    public CalculateView getCalView()
    {
        return calView;
    }

    public void setCalView(CalculateView calView)
    {
        this.calView = calView;
    }
    
    public void showCalView()
    {
        mainView.setContent(calView);
    }
    
    public ObservableList<Fraktion> getFaktions()
    {
        return model.getFaktions();
    }
}
