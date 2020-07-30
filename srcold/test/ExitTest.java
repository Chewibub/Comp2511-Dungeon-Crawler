package test;


import org.junit.Test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

public class ExitTest {

    @Test
    public void testExit(){
        
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 0);
        
        Wall wall1 = new Wall(3, 3);
        Wall wall2 = new Wall(3, 2);
        Wall wall3 = new Wall(3, 4);

        dungeon.addEntity(wall1);
        dungeon.addEntity(wall2);
        dungeon.addEntity(wall3);

        Exit exit = new Exit(dungeon, 4, 4);

        dungeon.addEntity(exit);


        Goal goal  = new Goal("exit", "ONE");
        
        dungeon.addGoal(goal);
        
        // check goal
        assertFalse(goal.checkCompleted());
        assertEquals("exit", goal.getType());

        TestUtil.moveRight(player, 4);
        assertFalse(goal.checkCompleted());

        //move to exit
        exit.smash();
        goal.updateGoal();
        assertTrue(goal.checkCompleted());
    }


}
