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
                if (temp.collides(player) == true) {
                    temp.smash();
                }
            }
        }

    }

    @Override
    public Entity getEntity() {
        return entity;
    }
}