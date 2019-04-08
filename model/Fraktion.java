package mvp.model;

import java.util.HashMap;

public class Fraktion
{
    private String name;

    private int currentT = 0;

    private HashMap<String, Integer> tierList;

    public Fraktion(String name)
    {
        this.name = name;
        tierList = new HashMap<String, Integer>();
        tierList.put("Tier 1", 350);
        tierList.put("Tier 2", 700);
        tierList.put("Tier 3", 1400);
        tierList.put("Tier 4", 2800);
        tierList.put("Tier 5", 5300);
        tierList.put("Tier 6", 10600);
        tierList.put("Tier 7", 22600);
        tierList.put("Tier 8", 45000);
        tierList.put("Tier 9", 90000);
        tierList.put("Tier 10", 179400);

    }

    public String getName()
    {
        return this.name;
    }

    public void setCurrentT(int t)
    {
        this.currentT = t;
    }

    public int getCurrentT()
    {
        return this.currentT;
    }

    public int getEXP(String s)
    {
        return tierList.get(s);
    }

    public String toString()
    {
        return this.name;
    }
}
