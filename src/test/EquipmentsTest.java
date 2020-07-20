package test;

import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class EquipmentsTest {
    private Dungeon dungeon;
    private Enemy enemy;

    @Before
    public void setup() throws FileNotFoundException {
        dungeon = TestUtil.load("dungeons/test-equipments.json");
        for (Entity entity : dungeon.getEntities()) {
            if ("Enemy".equals(entity.getType())) {
                enemy = (Enemy) entity;
                enemy.setMovable(false);
            }
        }
    }

    @Test
    public void testSword(){
        Player player = dungeon.getPlayer();
        int enemyCount = TestUtil.countEnemy(dungeon);
        // get the sword
        player.moveLeft();
        assertEquals(5,player.getSwordCharges());
        TestUtil.moveLeft(player, 3);
        TestUtil.moveUp(player, 2);
        assertEquals(4,player.getSwordCharges());
        assertEquals(enemyCount - 1, TestUtil.countEnemy(dungeon));
        TestUtil.moveRight(player, 4);
        assertEquals(0, player.getSwordCharges());
        assertEquals(enemyCount- 5, TestUtil.countEnemy(dungeon));

        player.moveUp();
        // killed by an enemy
        assertTrue(player.failed());
    }

    @Test
    public void testPotion(){
        Player player = dungeon.getPlayer();
        int enemyCount = TestUtil.countEnemy(dungeon);
        assertEquals(10, enemyCount);
        // get the potion
        player.moveRight();
        assertTrue(player.getInvincibility());
        TestUtil.moveRight(player, 2);
        TestUtil.moveUp(player, 2);
        assertEquals(enemyCount - 1, TestUtil.countEnemy(dungeon));
        TestUtil.moveLeft(player, 4);
        assertEquals(0, player.getSwordCharges());
        assertEquals(enemyCount- 5, TestUtil.countEnemy(dungeon));
        player.moveUp();
        // kill left enemies
        TestUtil.moveRight(player, 4);
        assertEquals(0, TestUtil.countEnemy(dungeon));
        assertFalse(player.failed());
    }
}
