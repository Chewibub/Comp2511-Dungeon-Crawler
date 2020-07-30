package unsw.dungeon.ui;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;

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