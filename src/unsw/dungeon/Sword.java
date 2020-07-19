package unsw.dungeon;


public class Sword extends Entity {
    private Dungeon dungeon;

    public Sword(Dungeon dungeon, int x, int y) {
        super(x, y, "Sword");
        this.dungeon = dungeon;
    }
    public void smash() {
        System.out.println("You picked up a sword!");
        dungeon.getPlayer().setSwordCharges(5);

        // TODO remove entity
    }

}

