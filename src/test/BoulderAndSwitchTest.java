package test;

import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class BoulderAndSwitchTest {
    private Dungeon dungeon;

    @Before
    public void setup() throws FileNotFoundException {
        dungeon=TestUtil.load("dungeons/test-boulders.json");
    }

    @Test
    public void testBoulderAndSwitch(){
        Player player = dungeon.getPlayer();
        Goal goal = dungeon.getGoals().get(0);
        assertFalse(goal.checkCompleted());
        assertEquals("boulder",goal.getType());
        assertEquals( 0,player.getX());
        assertEquals( 0,player.getY());
        // play can not push 2 boulders at once
        player.moveDown();
        assertEquals(0, player.getY());

        TestUtil.moveRight(player, 4);
        // x is 3, because the play is blocked by the boulder
        assertEquals( 3,player.getX());
        assertEquals( 0,player.getY());
        // push the first boulder to the switch
        assertFalse(goal.checkCompleted());

        TestUtil.moveDown(player, 3);
        assertEquals(3, player.getY());
        assertEquals(3, player.getX());
        assertTrue(goal.checkCompleted());
        // push the second boulder to the switch, goal complete
        assertTrue(goal.checkCompleted());

    }
}
