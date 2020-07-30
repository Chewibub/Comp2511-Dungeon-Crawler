package unsw.dungeon.ui;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.controller.LevelSelectController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LevelSelectScreen {
    private Scene scene;
    public LevelSelectScreen() {
        
        LevelSelectController controller = new LevelSelectController();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelSelect.fxml"));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.scene = scene;
        } catch (Exception e) {
            Platform.exit();
        }
        
    }

    public void activate() {
        DungeonApplication.getPrimaryStage().setScene(scene);
    }
}