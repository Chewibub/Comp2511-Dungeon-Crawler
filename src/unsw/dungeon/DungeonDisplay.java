package unsw.dungeon;

import javafx.application.Platform;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Portal;
import unsw.dungeon.ui.LevelSelectScreen;

import java.util.List;

public class DungeonDisplay implements DungeonObserver {
    Dungeon dungeon;
    Player player;

    public DungeonDisplay(Dungeon dungeon, Player player) {
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
                    Enemy enemy = (Enemy) temp;
                    enemy.triggerMovement();
                }
                if (temp.getType().equals("Portal")) {
                    Portal portal = (Portal) temp;
                    portal.activate();
                }
            }

        }

        for (Entity temp : dungeon.getEntities()) {
            if (temp != null) {
                if (temp.collides(player)) {
                    temp.smash();
                }

            }
        }
        dungeon.getEntitesByType("Switch").forEach(s -> s.update());
        if (dungeon.getGoal() != null && dungeon.getGoal().completed()) {
            DungeonApplication.setLevelStatus(dungeon.getDungeonIndex() + 1, true);
            LevelSelectScreen levelSelect = new LevelSelectScreen();
            levelSelect.activate();
        }
//        List<GoalOld> goals = dungeon.getGoals();
//        String conditional = "ONE";
//        for (GoalOld temp : goals) {
//            if (temp != null) {
//                temp.updateGoal();
//                conditional = temp.getCondition();
//                if (temp.checkCompleted() == true && temp.getCondition().equals("ONE")) {
//                    System.out.println("You won!!");
//                    Platform.exit();
//                    break;
//                } else if (temp.checkCompleted() == true && temp.getCondition().equals("OR")) {
//                    System.out.println("You won!!");
//                    Platform.exit();
//                    break;
//                } else if (temp.getCondition().equals("AND")) {
//                    if (temp.checkCompleted() == false) {
//                        return;
//                    }
//                }
//            }
//        }
//        if (conditional.equals("AND")) {
//            System.out.println("You won!!");
//            Platform.exit();
//        }
    }

    public Entity getEntity() {
        return player;
    }
}