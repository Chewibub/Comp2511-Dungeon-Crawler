package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setConstroller(this);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }
    
    public void moveEnemies() {
        for (Entity e : dungeon.getEntities()) {
            if (e.getType() == "Enemy") {
                Enemy enemy = (Enemy) e;
                enemy.triggerMovement();
            }
        }
    }

    public void removeImage(ImageView original) {
        this.initialEntities.remove(original);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            moveEnemies();
            player.pingObservers();
            break;
        case DOWN:
            player.moveDown();
            moveEnemies();
            player.pingObservers();
            break;
        case LEFT:
            player.moveLeft();
            moveEnemies();
            player.pingObservers();
            break;
        case RIGHT:
            player.moveRight();
            moveEnemies();
            player.pingObservers();
            break;
        default:
            break;
        }
    }

}

