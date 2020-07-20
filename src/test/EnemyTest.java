package test;

import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EnemyTest {
    private Dungeon dungeon;
    private Enemy enemy;

    @Before
    public void setup() throws FileNotFoundException {
        dungeon = TestUtil.load("dungeons/test-enemy.json");
        for (Entity entity : dungeon.getEntities()) {
            if ("Enemy".equals(entity.getType())) {
                enemy = (Enemy) entity;
                return;
            }
        }
    }

    @Test
    public void testEnemyMove() {
        Player player = dungeon.getPlayer();
        assertEquals(0, enemy.getX());
        assertEquals(0, enemy.getY());
        player.moveDown();
        assertEquals(1, enemy.getX());
        TestUtil.moveUp(player, 3);
        assertEquals(4, enemy.getX());
        player.moveUp();
        // killed by an enemy
        assertEquals(player.getX(), enemy.getX());
        assertEquals(player.getY(), enemy.getY());
        assertTrue(player.failed());
    }
}
