/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private CopyOnWriteArrayList<Entity> entities;
    private Player player;

    private DungeonDisplay dungeonDisplay;

    public CopyOnWriteArrayList<Goal> goals;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new CopyOnWriteArrayList<>();
        this.player = null;
        this.goals = new CopyOnWriteArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.dungeonDisplay = new DungeonDisplay(this, player);
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public void addEntity(Entity entity) {
        if (entity != null) {
            if (!entity.getType().equals("Player")) {
                entity.addObserver(dungeonDisplay);
            }
            entities.add(entity);
        }
    }

    public boolean validMove(Entity player, int x, int y) {
        if (x > getWidth() - 1 || y > getHeight() - 1 || x < 0 || y < 0) {
            System.out.println("Out of bounds");
            return false;
        }
        for (Entity temp : this.entities) {
            if (temp != null) {
                if (temp.getX() == x && temp.getY() == y) {
                    if (temp.checkSolid()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
        goal.addEntities(this.entities);
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void printGoals() {
        System.out.println("~~~~GOALS~~~~");
        String type = "ONE";
        for (Goal temp : goals) {
            if (temp != null) {
                System.out.println(temp.toString());
                type = temp.getCondition();
            }
        }
        if (type.equals("ONE") || type.equals("OR")) {
            System.out.println("~~~~Complete one of the above~~~~");
        }
    }
}
