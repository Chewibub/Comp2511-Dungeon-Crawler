package unsw.dungeon;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private StringProperty direction;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, "Player");
        this.dungeon = dungeon;
    }

    public String getDirection() {
        return direction.get();
    }

    public void moveUp() {
        int newY = getY() - 1;
        int newX = getX();
        if (dungeon.validMove(this, newX, newY)) {
            y().set(newY);
            direction = new SimpleStringProperty("Up");
            pingObservers();
        }
    }

    public void moveDown() {
        int newY = getY() + 1;
        int newX = getX();
        if (dungeon.validMove(this, newX, newY)) {
            y().set(newY);
            direction = new SimpleStringProperty("Down");
            pingObservers();
        }
    }

    public void moveLeft() {
        int newY = getY();
        int newX = getX() - 1;
        if (dungeon.validMove(this, newX, newY)) {
            x().set(newX);
            direction = new SimpleStringProperty("Left");            
            pingObservers();
        }         
    }

    public void moveRight() {
        int newY = getY();
        int newX = getX() + 1;
        if (dungeon.validMove(this, newX, newY)) {
            x().set(newX);
            direction = new SimpleStringProperty("Right");
            pingObservers();
        }
    }
}
