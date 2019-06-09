import java.util.HashMap;

public abstract class Soldier extends Entity
{
    private short movementDelay;
    private short delay;
    private short attackDelay;
    private Shield shield;

    public Soldier(int x, int y, Weapon weapon, Boolean isTeam1, short movementDelay, Shield shield, short hitPoint)
    {
        super(x, y, weapon, isTeam1, hitPoint);
        this.shield = shield;
        this.movementDelay = (short) (movementDelay + shield.getMovementDelay());
        delay = this.movementDelay;
    }

    public void setAttackDelay(short attackDelay)
    {
        this.attackDelay = attackDelay;
    }

    public void resetDelay()
    {
        delay = movementDelay;
    }

    public void delayDecrement()
    {
        delay--;
    }


    public Shield getShield()
    {
        return shield;
    }

    public short getMovementDelay()
    {
        return movementDelay;
    }

    public void setMovementDelay(short movementDelay)
    {
        this.movementDelay = movementDelay;
    }

    public short getDelay()
    {
        return delay;
    }

    public void setDelay(short delay)
    {
        this.delay = delay;
    }

    public short getAttackDelay()
    {
        return attackDelay;
    }

    public void setShield(Shield shield)
    {
        this.shield = shield;
    }

    public void kickSoldier()
    {
        Land land = new Land(-1,-1);
        this.setLand(land);
        this.setAlive(false);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Soldier && ((Soldier) obj).getLand().equals(this.getLand()))
        {
            return true;
        }
        return false;
    }



}
