import java.util.ArrayList;
import java.util.HashMap;

public class SwordsMan extends Soldier
{
    private HashMap<Integer, Entity> team;
    @Override
    public ArrayList<Action> act(HashMap<Integer, Entity> team, Field field)
    {
        this.team=team;
        this.setAttackDelay((short) Math.max(this.getAttackDelay() - 1, 0));
        this.setDelay((short) Math.max(this.getDelay() - 1, 0));
        ArrayList<Action> actions = new ArrayList<>();
        if (this.getAttackDelay() == 0)
        {
            for (Integer id : team.keySet())
            {
                if ((team.get(id).getAlive()) && Math.abs(this.getLand().getX() - team.get(id).getLand().getX()) <= 1 && Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1)
                {
                    if (team.get(id) instanceof Tower)
                    {
//                        this.setDelay((short) Math.max(this.getDelay() - 1,0));
                        this.getWeapon().setAttackDelay((short) (this.getWeapon().getAttackDelay() + (short) 1));
                        this.setAttackDelay(this.getWeapon().getAttackDelay());
                        return null;
                    }
//                    this.setDelay((short) Math.max(this.getDelay() - 1,0));
                    this.getWeapon().setAttackDelay((short) (this.getWeapon().getAttackDelay() + (short) 1));
                    this.setAttackDelay(this.getWeapon().getAttackDelay());
                    actions.add(new AttackAction((Soldier) team.get(id), this.getWeapon(), this));
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
            else if (field.isTower(land))
            {
                land.setX(this.getLand().getX());
                land.setY(this.getLand().getY());
                if (this.getTeam1())
                {
                    if (land.getY() != 0)
                    {
                        land.setY(land.getY() - 1);
                        if (field.isAvailable(land))
                        {
                            this.setDelay(this.getMovementDelay());
                            this.setLand(land);
                        }
                        else if (land.getY() != field.getN() - 2)
                        {
                            land.setY(land.getY() + 2);
                            if (field.isAvailable(land))
                            {
                                this.setDelay(this.getMovementDelay());
                                this.setLand(land);
                            }
                        }
                    }
                    else if (land.getY() != field.getN() - 1)
                    {
                        land.setY(land.getY() + 1);
                        if (field.isAvailable(land))
                        {
                            this.setDelay(this.getMovementDelay());
                            this.setLand(land);
                        }
                    }
                }
                else
                {
                    if (land.getY() != field.getN() - 1)
                    {
                        land.setY(land.getY() + 1);
                        if (field.isAvailable(land))
                        {
                            this.setDelay(this.getMovementDelay());
                            this.setLand(land);
                        }
                        else if (land.getY() != 1)
                        {
                            land.setY(land.getY() - 2);
                            if (field.isAvailable(land))
                            {
                                this.setDelay(this.getMovementDelay());
                                this.setLand(land);
                            }
                        }
                    }
                    else if (land.getY() != 0)
                    {
                        land.setY(land.getY() - 1);
                        if (field.isAvailable(land))
                        {
                            this.setDelay(this.getMovementDelay());
                            this.setLand(land);
                        }
                    }
                }
            }
        }
//        else
//        {
//            this.setAttackDelay((short) (this.getAttackDelay() - 1));
//            this.setDelay((short) (this.getDelay() - 1));
//        }
        return null;
    }


    private static class Sword extends Weapon
    {
        public Sword()
        {
            super((short) 2000, (short) 1);
        }

    }

    public SwordsMan(int x, int y, Boolean isTeam1, short hitPoint)
    {
        super(x, y, new Sword(), isTeam1, (short) 2, new IronShield(), hitPoint);
        this.setAttackDelay(this.getWeapon().getAttackDelay());
    }

    @Override
    public String toString()
    {
        return "SwordMan";
    }


    public boolean isAbleToFight()
    {
        for (Integer id : team.keySet())
        {
            if ((team.get(id).getAlive()) && Math.abs(this.getLand().getX() - team.get(id).getLand().getX()) <= 1 && Math.abs(this.getLand().getY() - team.get(id).getLand().getY()) <= 1)
                return true;
        }
        return false;
    }
}
