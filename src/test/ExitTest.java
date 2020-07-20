package test;

import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ExitTest {
    private Dungeon dungeon;

    @Before
    public void setup() throws FileNotFoundException {
        dungeon = TestUtil.load("dungeons/test-exit.json");

    }
    @Test
    public void testExit(){
        // check goal
        Player player = dungeon.getPlayer();
        Goal goal = dungeon.getGoals().get(0);
        assertFalse(goal.checkCompleted());
        assertEquals("exit",goal.getType());

        TestUtil.moveRight(player, 4);
        assertFalse(goal.checkCompleted());

        //move to exit
        TestUtil.moveDown(player, 4);
        assertTrue(goal.checkCompleted());
    }


}
