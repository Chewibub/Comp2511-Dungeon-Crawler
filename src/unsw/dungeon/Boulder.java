package unsw.dungeon;

import java.util.List;

public class Boulder extends Entity {

    private Dungeon dungeon;  
    
    public Boulder (Dungeon dungeon, int x, int y) {
        super(x, y, "Boulder");
        this.dungeon = dungeon;
    }

    @Override
    public void smash(){
        Player player = dungeon.getPlayer();
        String direction = player.getDirection();
        switch(direction) {
        case "Up":
            if (this.checkMove(direction)) {
                this.setX(this.getY() - 1);
            }
        case "Down":
            if (this.checkMove(direction)) {
                this.setX(this.getY() + 1);
            }
        case "Left":
            if (this.checkMove(direction)) {
                this.setX(this.getX() - 1);
            }
        case "Right":
            if (this.checkMove(direction)) {
                this.setX(this.getX() + 1);
            }
        }
        
    }

    public boolean checkMove(String direction) {
        List<Entity> entities = dungeon.getEntities();
        for (Entity temp : entities) {
            if (temp != null) {
                switch(direction) {
                    case "Up":
                    if (temp.getY() == this.getY() - 1 && temp.getX() == this.getX()) {
                        if (temp.checkSolid()) {
                            return false;
                        }
                    }
                    case "Down":
                    if (temp.getY() == this.getY() + 1 && temp.getX() == this.getX()) {
                        if (temp.checkSolid()) {
                            return false;
                        }
                    }
                    case "Left":
                    if (temp.getX() == this.getX() - 1 && temp.getY() == this.getY()) {
                        if (temp.checkSolid()) {
                            return false;
                        }
                    }
                    case "Right":
                    if (temp.getX() == this.getX() + 1 && temp.getY() == this.getY()) {
                        if (temp.checkSolid()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkSolid() {
        Player player = dungeon.getPlayer();
        String direction = player.getDirection();
        if (checkMove(direction)) {
            return false;
        } else {
            return true;
        }

    }
}