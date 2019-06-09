import java.util.ArrayList;

public class Weapon {
    private short destruction;
    private short attackDelay;

    public Weapon(short destruction, short attackDelay) {
        this.destruction = destruction;
        this.attackDelay = attackDelay;
    }

    public short getAttackDelay() {
        return attackDelay;
    }

    public short getDestruction()
    {
        return destruction;
    }

    public void setDestruction(short destruction)
    {
        this.destruction = destruction;
    }

    public void setAttackDelay(short attackDelay)
    {
        this.attackDelay = attackDelay;
    }
}
