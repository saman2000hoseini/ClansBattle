import java.util.ArrayList;
import java.util.HashMap;

public class Game
{
    private HashMap<Integer, Entity> team1 = new HashMap<>();
    private HashMap<Integer, Entity> team2 = new HashMap<>();
    private Field field;

    public Game(HashMap<Integer, Entity> team1, HashMap<Integer, Entity> team2, Field field)
    {
        this.team1 = team1;
        this.team2 = team2;
        this.field = field;
    }

    public void tick()
    {
        ArrayList<Action> actions = new ArrayList<>();
        findActions(actions, team1, team2);
        findActions(actions, team2, team1);
        Soldier soldier;
        Land deathLand = new Land(-1,-1);
        for (Action action : actions)
        {
            if (action.doAction() && action instanceof AttackAction && !(((AttackAction) action).getSoldier().getLand().equals(deathLand)))
            {
                soldier = ((AttackAction) action).getSoldier();
                for (Action attackAction:actions)
                {
                    if (attackAction instanceof AttackAction && ((AttackAction) attackAction).getSoldier().equals(soldier) &&((AttackAction) attackAction).getAttacker() instanceof Soldier)
                    {
                        ((AttackAction) attackAction).getAttacker().setLand(soldier.getLand());
                        soldier.kickSoldier();
                        break;
                    }
                }
                soldier.kickSoldier();
            }
        }
    }

    private void findActions(ArrayList<Action> actions, HashMap<Integer, Entity> team2, HashMap<Integer, Entity> team1)
    {
        for (Integer id : team2.keySet())
        {
            ArrayList<Action> action;
            if (team2.get(id).getAlive())
                action = team2.get(id).act(team1, field);
            else
                continue;
            if (action != null)
                actions.addAll(action);
        }
    }
}
