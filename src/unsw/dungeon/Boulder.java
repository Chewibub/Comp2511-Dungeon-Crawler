package unsw.dungeon;

public class Boulder extends Entity {

    private Dungeon dungeon;  
    
    public Boulder (Dungeon dungeon, int x, int y) {
        super(x, y, "Boulder");
        this.dungeon = dungeon;
    }
}