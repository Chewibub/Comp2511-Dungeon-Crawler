package unsw.dungeon.controller;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import unsw.dungeon.ui.LevelSelectScreen;
import unsw.dungeon.ui.StoreScreen;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import java.io.IOException;
public class MainMenuController extends MenuController {

    @FXML
    private Pane menuPane;

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
    private void handlePlay(MouseEvent event) throws IOException {
        LevelSelectScreen levelSelect = new LevelSelectScreen();
        levelSelect.activate();
    }

    @FXML
    private void handleStore(MouseEvent event) {
        StoreScreen store = new StoreScreen();
        store.activate();
    }


    public MainMenuController() {
        super();
    }

    public void initialize() {
        menuPane.setBackground(super.background);
    }

}