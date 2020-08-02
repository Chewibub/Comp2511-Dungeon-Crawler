package unsw.dungeon.ui;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.controller.MainMenuController;

import java.io.IOException;

public class MainMenuScreen {
    private Scene scene;
    public MainMenuScreen() throws IOException {
        
        MainMenuController controller = new MainMenuController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.scene = scene;
    }

    public void activate() {
        DungeonApplication.getPrimaryStage().setScene(scene);
    }
    

}