package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity implements EntitySubject{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;

    private StringProperty type;
    private CopyOnWriteArrayList<DungeonObserver> observers;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, String type) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = new SimpleStringProperty(type);
        this.observers = new CopyOnWriteArrayList<DungeonObserver>();
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public StringProperty type() {
        return type;
    }

    public String getType() {
        return type().get();
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    @Override
    public void addObserver(DungeonObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(DungeonObserver o) {
        observers.remove(o);
    }

    @Override
    public void pingObservers() {
        for (DungeonObserver o : observers) {
            o.update();
        }
    }

    public boolean collides(Entity player) {
        if (this.getType().equals("Player")) {
            return false;
        }
        if (player.getX() == this.getX() && player.getY() == this.getY()) {
            return true;
        } else {
            return false;
        }
    }

    public void smash() {
    }
}
