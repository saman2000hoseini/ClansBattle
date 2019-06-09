import java.util.ArrayList;
import java.util.HashMap;

public class Tower extends Entity {
    private short attackDelay;
    public Tower(int x, int y, Boolean isTeam1) {
        super(x, y, new Sling(),isTeam1, (short) 10000);
        attackDelay=this.getWeapon().getAttackDelay();
    }


    @Override
    public String toString()
    {
        return "Tower";
    }

    @Override
    public ArrayList<Action> act(HashMap<Integer, Entity> team,Field field)
    {
        this.attackDelayDecrement();
        ArrayList<Action> actions = new ArrayList<>();
        if (this.getAttackDelay() == 0)
        {
            for (Integer id : team.keySet())
            {
                if ((team.get(id).getAlive())&&Math.abs(this.getLand().getX() - team.get(id).getLand().getX()) <= 2 && Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 2)
                {
                    if (team.get(id) instanceof Tower)
                    {
                        this.resetAttackDelay();
                    }
                    else
                    {
                        this.resetAttackDelay();
                        Action action = new AttackAction((Soldier) team.get(id), this.getWeapon(),this);
                        actions.add(action);
                    }
                }
            }
        }
//        else
//            this.attackDelayDecrement();
        return actions;
    }

    public void resetAttackDelay()
    {
        this.attackDelay = this.getWeapon().getAttackDelay();
    }

    public void attackDelayDecrement()
    {
        this.attackDelay = (short) Math.max(0,this.getAttackDelay()-1);
    }

    public short getAttackDelay()
    {
        return attackDelay;
    }
}
