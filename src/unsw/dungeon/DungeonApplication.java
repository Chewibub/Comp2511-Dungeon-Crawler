package unsw.dungeon;

import javafx.application.Application;
import javafx.stage.Stage;

import unsw.dungeon.ui.MainMenuScreen;

import java.io.IOException;

public class DungeonApplication extends Application {
    private static Stage primaryStage;
    private static boolean[] levelStatus;
    private static int gold;
    private static String[] dungeons;

    @Override
    public void start(Stage primaryStage) throws IOException {

        DungeonApplication.primaryStage = primaryStage;
        DungeonApplication.levelStatus = new boolean[6];
        DungeonApplication.levelStatus[0] = true;
        String[] dunBuff = {"test-exit.json", "advanced.json", "advanced.json", "advanced.json", "advanced.json", "advanced.json"};
        DungeonApplication.dungeons = dunBuff;
        DungeonApplication.gold = 5;

        primaryStage.setTitle("C-Dungeons");

        MainMenuController controller = new MainMenuController();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/MainMenu.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static int getGold() {
        return gold;
    }

    public static void giveGold(int value) {
        DungeonApplication.gold = DungeonApplication.gold + value;
    }

    public static boolean getLevelStatus(int index) {
        return levelStatus[index];
    }

    public static void setLevelStatus(int index, boolean status) {
        DungeonApplication.levelStatus[index] = status;
    }

    public static String getDungeon(int index) {
        return dungeons[index];
    }

    public static void main(String[] args) {
        launch(args);
    }

}
