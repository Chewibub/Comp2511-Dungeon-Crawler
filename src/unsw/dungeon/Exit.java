package unsw.dungeon;

public class Exit extends Entity {
    
    private Dungeon dungeon;

    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y, "Exit");
        this.dungeon = dungeon;
    }

    @Override
    public void smash() {
        System.out.println("You win!");
    }

}