package unsw.dungeon;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The Enemy entity
 * Moves left to right
 */

public class Enemy extends Entity {

    private Dungeon dungeon;
    private int direction;
    /**
     * Create a enemy positioned in square (x,y)
     * @param x
     * @param y
     */

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, "Enemy");
        this.dungeon = dungeon;
        this.direction = 1;
    }
    public void triggerMovement() {
        move(direction);
    }

    public void move(int d) {
        int newY = getY();
        int newX = getX() + d;
        if (dungeon.validMove(this, newX, newY)) {
            x().set(newX);
        } else {
            direction = d * -1;
        }
    }
    
    public void smash() {
        Player player = dungeon.getPlayer();

        if (player.getInvincibility()) {
            System.out.println("You killed an enemy!");
            dungeon.removeEntity(this);
        } else if (player.getSwordCharges() > 0) {
            System.out.println("You killed an enemy!");
            dungeon.removeEntity(this);
            player.setSwordCharges(player.getSwordCharges() - 1);
        } else {
            System.out.println("You have died!");
            Platform.exit();
        }
    }
}
