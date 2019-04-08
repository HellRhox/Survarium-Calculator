package mvp.calculated;

import javafx.collections.ObservableList;
import mvp.main.MainPresenter;
import mvp.model.Fraktion;

public class CalculatePresenter
{

    private MainPresenter main;

    private CalculateView calView;

    public CalculatePresenter()
    {

    }

    public MainPresenter getMain()
    {
        return main;
    }

    public void setMain(MainPresenter main)
    {
        this.main = main;
    }

    public CalculateView getCalView()
    {
        return calView;
    }

    public void setCalView(CalculateView calView)
    {
        this.calView = calView;
    }

    public ObservableList<Fraktion> getFaktions()
    {
        return main.getFaktions();
    }

    public double CalProgress(double totalEXP, double currentEXP)
    {
        double value = currentEXP / totalEXP;
        return value;
    }

    public int berechnen(int currentRep, double boost, double squad, double premium, int anzahl, int totalRep)
    {
        double rep = currentRep;
        double lvlCap = totalRep;
        int i = 0;
        int anz = anzahl;
        // System.out.println("methoide berechenen aufgerufen");
        while (rep < lvlCap)
        {
            // eigentliche Berechnung
            if (anz >= 1)
            {
                rep = rep + 100 + (100 * boost) + (100 * squad) + (100 * premium);
            }
            else
            {
                rep = rep + 100 + (100 * squad) + (100 * premium);
            }

            // herausfiltern der nutzungen der Booster und verfall selbiger
            if (boost == 0.10 && i % 3 == 0)
            {
                anz = anz - 1;
            }
            else if (boost == 0.25 && i % 3 == 0)
            {
                anz = anz - 1;
            }
            else if (boost == 0.50 && i % 5 == 0)
            {
                anz = anz - 1;
            }
            else if (boost == 0.75 && i % 5 == 0)
            {
                anz = anz - 1;
            }

            i++;

            // System.out.println(i + ".durchlauf der schleife");

        }
        return i;
    }
}
