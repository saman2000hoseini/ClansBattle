public class MovementAction implements Action
{
    private Soldier soldier;

    public MovementAction(Soldier soldier)
    {
        this.soldier = soldier;
    }

    @Override
    public boolean doAction()
    {
        if (soldier.getTeam1())
            soldier.setLand(new Land((soldier.getLand().getX()+1), soldier.getLand().getY()));
        else
            soldier.setLand(new Land((soldier.getLand().getX()-1), soldier.getLand().getY()));
        return false;
    }
}
