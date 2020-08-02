package unsw.dungeon.ui;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.controller.StoreController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class StoreScreen {
    private Scene scene;
    public StoreScreen() {
        
        StoreController controller = new StoreController();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Store.fxml"));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.scene = scene;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Platform.exit();
        }
        
    }

    public void activate() {
        DungeonApplication.getPrimaryStage().setScene(scene);
    }
}