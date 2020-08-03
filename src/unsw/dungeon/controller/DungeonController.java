package unsw.dungeon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.ui.PopUp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    private Button goalButton=new Button("goal");

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setController(this);
    }

    @FXML
    public void initialize() {
        System.out.println("D Controller Init");
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

        // GridPane.setRowIndex(goalButton, 0);

        // goalButton.setStyle("-fx-background-color: rgba(255,255,255,0.3);-fx-text-fill: white");
        // goalButton.setOnMouseClicked(event -> {
            

            
        // });

        // GridPane.setColumnIndex(goalButton, dungeon.getWidth() - 2);
        // GridPane.setColumnSpan(goalButton,2);
        // squares.getChildren().add(goalButton);


    }

    public void removeImage(ImageView original) {
        this.initialEntities.remove(original);
        squares.getChildren().remove(original);
    }

    public void setImage(ImageView view) {
        squares.getChildren().add(view);
    }

    public void setDungeonIndex(int index) {
        dungeon.setDungeonIndex(index);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case ESCAPE:
            break;
        default:
            break;
        }
    }

}

