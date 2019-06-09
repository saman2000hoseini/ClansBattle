import java.util.ArrayList;
import java.util.HashMap;

public class SpearsMan extends Soldier
{
    private HashMap<Integer,Entity> team;
    @Override
    public ArrayList<Action> act(HashMap<Integer, Entity> team, Field field)
    {
        this.team = team;
        this.setAttackDelay((short) Math.max(this.getAttackDelay() - 1, 0));
        this.setDelay((short) Math.max(this.getDelay() - 1,0));
        ArrayList<Action> actions = new ArrayList<>();
        if (this.getAttackDelay() == 0)
        {
            for (Integer id : team.keySet())
            {
                if (this.getTeam1())
                {
                    if ((team.get(id).getAlive())&&(((this.getLand().getX() - team.get(id).getLand().getX()) == -2)&& Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1) || ((this.getLand().getX() - team.get(id).getLand().getX()) == -1) && Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1)
                    {
                        if (team.get(id) instanceof Tower)
                        {
//                            this.setDelay((short) Math.max(this.getDelay() - 1,0));
                            this.setAttackDelay(this.getWeapon().getAttackDelay());
                            return null;
                        }
//                        this.setDelay((short) Math.max(this.getDelay() - 1, 0));
                        this.setAttackDelay(this.getWeapon().getAttackDelay());
                        actions.add(new AttackAction((Soldier) team.get(id), this.getWeapon(),this));
                        return actions;
                    }
                }
                else if ((team.get(id).getAlive())&&(((this.getLand().getX() - team.get(id).getLand().getX()) == 2)&& Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1) || ((this.getLand().getX() - team.get(id).getLand().getX()) == 1) && Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1)
                {
                    if (team.get(id) instanceof Tower)
                    {
//                        this.setDelay((short) Math.max(this.getDelay() - 1,0));
                        this.getWeapon().setAttackDelay((short) (this.getWeapon().getAttackDelay() + (short) 1));
                        this.setAttackDelay(this.getWeapon().getAttackDelay());
                        return null;
                    }
//                    this.setDelay((short) Math.max(this.getDelay() - 1, 0));
                    this.setAttackDelay(this.getWeapon().getAttackDelay());
                    actions.add(new AttackAction((Soldier) team.get(id), this.getWeapon(),this));
                    return actions;
                }
            }
        }
        if (this.getDelay() == 0 && !isAbleToFight())
        {
//            this.setAttackDelay((short) Math.max(this.getAttackDelay() - 1, 0));
            Land land = new Land(this.getLand().getX(), this.getLand().getY());
            if (this.getTeam1() && land.getX() < field.getN() - 1)
                land.setX(land.getX() + 1);
            else if (this.getTeam1() && land.getX() == field.getN() - 1)
            {
                land.setX(land.getX() - 1);
                this.setTeam1(false);
            }
            else if (!this.getTeam1() && land.getX() > 0)
                land.setX(land.getX() - 1);
            else if (!this.getTeam1() && land.getX() == 0)
            {
                land.setX(land.getX() + 1);
                this.setTeam1(true);
            }
            if (field.isAvailable(land))
            {
                this.setDelay(this.getMovementDelay());
                actions.add(new MovementAction(this));
                return actions;
            }
        }
//        else
//        {
//            this.setAttackDelay((short) (this.getAttackDelay() - 1));
//            this.delayDecrement();
//        }
        return null;
    }

    private static class Spear extends Weapon
    {
        public Spear()
        {
            super((short) 1500, (short) 2);
        }
    }

    public SpearsMan(int x, int y, Boolean isTeam1, short hitPoint)
    {
        super(x, y, new Spear(), isTeam1, (short) 1, new IronShield(), hitPoint);
        this.setAttackDelay(this.getWeapon().getAttackDelay());
    }

    @Override
    public String toString()
    {
        return "SpearMan";
    }

    public boolean isAbleToFight()
    {
        for (Integer id : team.keySet())
        {
            if ((team.get(id).getAlive())&&(((this.getLand().getX() - team.get(id).getLand().getX()) == -2)&& Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1) || ((this.getLand().getX() - team.get(id).getLand().getX()) == -1) && Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1)
                return true;
            else if ((team.get(id).getAlive())&&(((this.getLand().getX() - team.get(id).getLand().getX()) == 2)&& Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1) || ((this.getLand().getX() - team.get(id).getLand().getX()) == 1) && Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1)
                return true;
        }
        return false;
    }
}
