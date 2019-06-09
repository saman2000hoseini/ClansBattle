import java.util.ArrayList;
import java.util.HashMap;

public abstract class Entity
{
    private Land land;
    private Weapon weapon;

    public void setTeam1(Boolean team1)
    {
        isTeam1 = team1;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    private Boolean isAlive;
    private Boolean isTeam1;
    private short hitPoint;

    public short getHitPoint()
    {
        return hitPoint;
    }

    public void setHitPoint(short hitPoint)
    {
        this.hitPoint = hitPoint;
    }

    public Entity(int x, int y, Weapon weapon, Boolean isTeam1, short hitPoint)
    {
        land = new Land(x, y);
        this.weapon = weapon;
        this.isTeam1 = isTeam1;
        this.hitPoint = hitPoint;
        isAlive = true;
    }

    public abstract ArrayList<Action> act(HashMap<Integer, Entity> team, Field field);


    public Weapon getWeapon()
    {
        return weapon;
    }

    public Land getLand()
    {
        return land;
    }

    public void setLand(Land land)
    {
        this.land = land;
    }

    public Boolean getTeam1()
    {
        return isTeam1;
    }

    public Boolean getAlive()
    {
        return isAlive;
    }

    public void setAlive(Boolean alive)
    {
        isAlive = alive;
    }
}
