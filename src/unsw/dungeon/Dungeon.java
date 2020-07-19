/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

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
    private List<Entity> entities;
    private Player player;

    private DungeonDisplay dungeonDisplay;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
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
        return entities;
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
        for (Entity temp : this.entities) {
            if (temp != null) {
                if (temp.getX() == x && temp.getY() == y) {
                    if (temp.getType().equals("Wall")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
