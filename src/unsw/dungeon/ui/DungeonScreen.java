package unsw.dungeon.ui;
import unsw.dungeon.DungeonApplication;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import unsw.dungeon.controller.DungeonController;
import unsw.dungeon.controller.DungeonControllerLoader;

public class DungeonScreen {
    public DungeonScreen(int dungeon) {
    
        try {
            DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(DungeonApplication.getDungeon(dungeon));

            DungeonController controller = dungeonLoader.loadController();
            controller.setDungeonIndex(dungeon);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../DungeonView.fxml"));
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            root.requestFocus();
            DungeonApplication.getPrimaryStage().setScene(scene);
        } catch (Exception e) {
            //TODO: handle exception
        }
       

    }

    public void activate() {
    }
    

}