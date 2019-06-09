public class AttackAction implements Action
{
    private Soldier soldier;
    private Weapon weapon;
    private Entity attacker;

    public AttackAction(Soldier soldier, Weapon weapon,Entity attacker)
    {
        this.soldier = soldier;
        this.weapon = weapon;
        this.attacker=attacker;
    }

    @Override
    public boolean doAction()
    {
        soldier.setHitPoint((short) (soldier.getHitPoint()-((short)100-soldier.getShield().getDecrement())*weapon.getDestruction()/100));
        if (soldier.getHitPoint()<=0)
        {
            soldier.setHitPoint((short)0);
            return true;
        }
        return false;
    }


    public Soldier getSoldier()
    {
        return soldier;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public Entity getAttacker()
    {
        return attacker;
    }
}
