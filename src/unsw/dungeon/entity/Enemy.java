package unsw.dungeon.entity;

import javafx.animation.Timeline;
import unsw.dungeon.Dungeon;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

/**
 * The Enemy entity
 * Moves left to right
 */

public class Enemy extends Entity {

    private Dungeon dungeon;
    private int direction;
    private boolean movable = true;
    protected Timeline timeline;

    /**
     * Create a enemy positioned in square (x,y)
     *
     * @param x
     * @param y
     */

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, "Enemy");
        this.dungeon = dungeon;
        this.direction = 1;
        this.timeline = new Timeline();
        initialiseTimeLine();
    }

    public void triggerMovement() {
        if (dungeon.getPlayer().getX()==getX()&&dungeon.getPlayer().getY() == getY()) {
            smash();
            return;
        }
        if (movable) {
            move(direction);
        }
    }

    public void move(int d) {
        int newY = getY();
        int newX = getX() + d;
        if (dungeon.validMove(this, newX, newY)) {
            x().set(newX);
        } else {
            direction = d * -1;
            x().set(getX() + direction);
        }
    }

    public void smash() {
        Player player = dungeon.getPlayer();

        if (player.getInvincibility()) {
            System.out.println("You killed an enemy!");
            System.out.println(this);
            dungeon.removeEntity(this);
        } else if (player.getSwordCharges() > 0) {
            System.out.println("You killed an enemy!");
            dungeon.removeEntity(this);
            player.setSwordCharges(player.getSwordCharges() - 1);
        } else {
            System.out.println("You have died!");
            dungeon.getPlayer().fail();
//            Platform.exit();
        }
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public void initialiseTimeLine() {
        double refresh = 1/2;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(refresh), e -> {
            triggerMovement();
        }));
        timeline.play();
    }
}
