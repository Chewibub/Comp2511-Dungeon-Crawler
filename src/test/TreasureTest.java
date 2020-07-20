package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;

import static org.junit.Assert.*;

class TreasureTest {
    @Test
    void treasurePickUp() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 1);
        Goal goal = new Goal("treasure", "ONE");
        Treasure treasure = new Treasure(dungeon, 2, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(treasure);
        dungeon.addGoal(goal);
        player.moveRight();
        player.moveRight();
        assertEquals(2, treasure.getX());
    }

}