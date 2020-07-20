package test;

import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;


public class MovementTest {

    private Dungeon dungeon;

    @Before
    public void setup() throws FileNotFoundException {
        dungeon = TestUtil.load("dungeons/maze.json");


    }

    @Test
    public void testMove() {

        Player player = dungeon.getPlayer();
        // up and left: wall
        player.moveUp();
        player.moveLeft();
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        player.moveDown();
        assertEquals(1, player.getX());
        assertEquals(2, player.getY());
        // right: wall
        player.moveRight();
        assertEquals(1, player.getX());
        assertEquals(2, player.getY());
        player.moveDown();
        TestUtil.moveRight(player, 5);
        assertEquals(6, player.getX());
        assertEquals(3, player.getY());

    }


}

