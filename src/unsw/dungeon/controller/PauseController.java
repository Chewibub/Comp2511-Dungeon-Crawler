package unsw.dungeon.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import unsw.dungeon.ui.PauseScreen;

public class PauseController {

    @FXML
    private Button resumeButton;

    private PauseScreen screen;

    public PauseController(PauseScreen pScreen) {
        this.screen = pScreen;
    }

    @FXML
    void handleResumeButton(ActionEvent event) {
        screen.getStage().hide();
        screen.getDungeonController().togglePause();
    }


}
