import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    private static HashMap<Integer, Entity> team1 = new HashMap<>();
    private static HashMap<Integer, Entity> team2 = new HashMap<>();
    private static Field field;
    private static void getInputs(int count,boolean isTeam1, Scanner in)
    {
        for (int i = 0; i < count; i++)
        {
            String str = in.next();
            String[] arrOfStr = str.split(",");
            int id = Integer.valueOf(arrOfStr[0]);
            String type = arrOfStr[1];
            Land land = new Land(Integer.valueOf(arrOfStr[2]), Integer.valueOf(arrOfStr[3]));
            Entity entity;
            if (type.equals("SwordMan"))
            {
                entity = new SwordsMan(land.getX(), land.getY(), isTeam1, (short) 5000);
            }
            else if (type.equals("SpearMan"))
            {
                entity = new SpearsMan(land.getX(), land.getY(), isTeam1, (short) 3000);
            }
            else
            {
                entity = new Tower(land.getX(), land.getY(), isTeam1);
            }
            if (isTeam1)
                team1.put(id, entity);
            else
                team2.put(id,entity);
            field.addEntity(entity);
        }
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        field = new Field(n);
        int team1Count = input.nextInt();
        int team2Count = input.nextInt();
        getInputs(team1Count,true,input);
        getInputs(team2Count,false,input);
        String tick= input.next();
        tick=input.nextLine();
        Game game = new Game(team1,team2,field);
        while(!tick.equals("terminate"))
        {
            String[] count = tick.split("\\s+");
            for (int i = 0; i < Integer.valueOf(count[1]); i++)
            {
                game.tick();
            }
            printResult(team1);
            printResult(team2);
            tick=input.nextLine();
        }
    }

    private static void printResult(HashMap<Integer, Entity> team)
    {
        for (Integer ID : team.keySet())
        {
            System.out.println(ID.toString() + "," + team.get(ID).toString() +","+ team.get(ID).getHitPoint() + "," + team.get(ID).getLand().getX() + "," + team.get(ID).getLand().getY());
        }
    }

}


/*
10
1
0
1,SwordMan,1,0
tick 6
terminate
 */