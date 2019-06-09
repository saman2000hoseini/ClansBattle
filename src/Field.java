import java.util.ArrayList;

public class Field {
    private int n;
    private ArrayList<Entity> entities=new ArrayList<>();

    public Field(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isAvailable(Land land)
    {
        for (Entity entity:entities)
        {
            if (entity.getLand().getX()==land.getX() && entity.getLand().getY()==land.getY())
                return false;
        }
        return true;
    }
    public boolean isTower(Land land)
    {
        for (Entity entity:entities)
        {
            if (entity.getLand().getX()==land.getX() && entity.getLand().getY()==land.getY())
                if (entity instanceof Tower)
                    return true;
                else
                    return false;
        }
        return false;
    }
    public void addEntity(Entity entity)
    {
        entities.add(entity);
    }
}


