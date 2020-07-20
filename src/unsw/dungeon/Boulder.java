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
            this.setY(this.getY() - 1);
            break;
        case "Down":
            this.setY(this.getY() + 1);
            break;
        case "Left":
            this.setX(this.getX() - 1);
            break;
        case "Right":               
            this.setX(this.getX() + 1);
            break;
        }
        
    }

    public boolean checkMove(String direction) {
        List<Entity> entities = dungeon.getEntities();
        for (Entity temp : entities) {
            if (temp != null) {
                switch(direction) {
                    case "Up":
                        if (temp.getY() == this.getY() - 1 && temp.getX() == this.getX()) {
                            if (temp.getType().equals("Boulder")) {
                                return false;
                            } else if (temp.checkSolid()) {
                                return false;
                            }
                        }
                        break;
                    case "Down":
                        if (temp.getY() == this.getY() + 1 && temp.getX() == this.getX()) {
                            if (temp.getType().equals("Boulder")) {
                                return false;
                            } else if (temp.checkSolid()) {
                                return false;
                            }
                        }
                        break;
                    case "Left":
                        if (temp.getX() == this.getX() - 1 && temp.getY() == this.getY()) {
                            if (temp.getType().equals("Boulder")) {
                                return false;
                            } else if (temp.checkSolid()) {
                                return false;
                            }
                        }
                        break;
                    case "Right":
                        if (temp.getX() == this.getX() + 1 && temp.getY() == this.getY()) {
                            if (temp.getType().equals("Boulder")) {
                                return false;
                            } else if (temp.checkSolid()) {
                                return false;
                            }
                        }
                        break;
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