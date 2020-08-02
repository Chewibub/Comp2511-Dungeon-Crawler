package unsw.dungeon.controller;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import java.io.File;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;

public class MenuController {
    
    protected Background background;

    @FXML
    private Button exitButton;
    
    @FXML
    private Button storeButton;

    @FXML
    private Button playButton;
    
    @FXML
    private void handleExit(MouseEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void handlePlay(MouseEvent event) {
    
    }

    @FXML
    private void handleStore(MouseEvent event) {
        
    }

    @FXML
    private void buttonHover(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #8aacb8; ");
    }

    @FXML
    private void buttonLeave(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-background-color: #ADD8E6; ");
    }


    public MenuController() {
        Image image = new Image((new File("images/dungeon-wall.png")).toURI().toString());

        BackgroundImage myBI = new BackgroundImage(image,
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
        background = new Background(myBI);
    }

    public void initialize() {
       
    }

}