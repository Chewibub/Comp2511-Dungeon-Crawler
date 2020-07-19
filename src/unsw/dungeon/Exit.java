package unsw.dungeon;

public class Exit extends Entity {
    
    private Dungeon dungeon;

    private boolean accessed;

    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y, "Exit");
        this.dungeon = dungeon;
        this.accessed = false;
    }

    @Override
    public void smash() {
        this.accessed = true;
    }

    @Override
    public boolean checkUsed() {
        return this.accessed;
    }

}