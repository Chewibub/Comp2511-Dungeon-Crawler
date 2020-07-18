package unsw.dungeon;

import java.util.ArrayList;

public class DungeonDisplay implements DungeonObserver {
    Dungeon dungeon;
    Entity entity;

    public DungeonDisplay (Dungeon dungeon, Entity entity) {
        this.dungeon = dungeon;
        this.entity = entity;
        this.entity.addObserver(this);
    }

    public void update() {
        Player player;
        player = dungeon.getPlayer();
        if (entity.collides(player) == true) {
            entity.smash();
        }
    }
}