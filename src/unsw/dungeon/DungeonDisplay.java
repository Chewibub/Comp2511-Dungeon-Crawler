package unsw.dungeon;

import java.util.List;

import javafx.application.Platform;

public class DungeonDisplay implements DungeonObserver {
    Dungeon dungeon;
    Player player;

    public DungeonDisplay (Dungeon dungeon, Player player) {
        this.dungeon = dungeon;
        this.player = player;
        this.player.addObserver(this);
    }

    @Override
    public void update() {
        List<Entity> entities = dungeon.getEntities();
        for (Entity temp : entities) {
            if (temp != null) {
                if (temp.collides(player) == true) {
                    temp.smash();
                }
            }
        }
        List<Goal> goals = dungeon.getGoals();
        String conditional = "ONE";
        for (Goal temp : goals) {
            if (temp != null) {
                temp.updateGoal();
                conditional = temp.getCondition();
                if (temp.checkCompleted() == true && temp.getCondition().equals("ONE")) {
                    System.out.println("You won!!");
                    Platform.exit();
                    break;
                } else if (temp.checkCompleted() == true && temp.getCondition().equals("OR")) {
                    System.out.println("You won!!");
                    Platform.exit();
                    break;
                } else if (temp.getCondition().equals("AND")) {
                    if (temp.checkCompleted() == false) {
                        return;
                    }
                }
            }
        }
        if (conditional.equals("AND")) {
            System.out.println("You won!!");
            Platform.exit();            
        }
    }

    public Entity getEntity() {
        return player;
    }
}