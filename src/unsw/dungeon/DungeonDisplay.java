package unsw.dungeon;

import java.util.List;

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
                if (temp.getType().equals("Enemy")) {
                    Enemy enemy= (Enemy) temp;
                    enemy.triggerMovement();
                }
            }
        }

        for (Entity temp : entities) {
            if (temp != null) {
                if (temp.collides(player)) {
                    temp.smash();
                }

            }
        }

        List<Goal> goals = dungeon.getGoals();
        for (Goal temp : goals) {
            if (temp != null) {
                temp.updateGoal();
                if (temp.checkCompleted() == true && temp.getCondition().equals("ONE")) {
                    System.out.println("You won!!");
                }
            }
        }

    }

//    @Override
//    public Entity getEntity() {
//        return player;
//    }
}