package unsw.dungeon;

public class Door extends Entity {
    
    private Dungeon dungeon;
    private State state;
    private int id;

    public Door(Dungeon dungeon, int x, int y, int id) {
        super(x, y, "Door");
        this.dungeon = dungeon;
        this.state = State.OFF;
        this.id = id;
    }

    @Override
    public boolean checkSolid() {
        if (this.state == State.ON) {
            return false;
        }
        if (dungeon.getPlayer().getKeyInventory() == 0) {
            return true;
        }
        if (this.id == dungeon.getPlayer().getKey().getID()) {
            this.state = State.ON;
            dungeon.getPlayer().delKey();
            return false;
        } else if (this.state == State.ON) {
            return false;
        }
        return true;
    }

    public boolean getOpened() {
        if (state == State.ON) {
            return true;
        }
        return false;
    }

    @Override
    public int getID() {
        return this.id;
    }

}