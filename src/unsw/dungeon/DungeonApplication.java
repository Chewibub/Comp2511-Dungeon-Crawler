package unsw.dungeon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import unsw.dungeon.controller.MainMenuController;

import java.io.IOException;

public class DungeonApplication extends Application {
    private static Stage primaryStage;
    private static boolean[] levelStatus;
    private static int gold;
    private static String[] dungeons;
    private static int currentLevel = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {

        DungeonApplication.primaryStage = primaryStage;
        DungeonApplication.levelStatus = new boolean[6];
        DungeonApplication.levelStatus[0] = true;
        String[] dunBuff = {"test-exit.json", "advanced.json", "advanced.json", "advanced.json", "advanced.json", "advanced.json"};
        DungeonApplication.dungeons = dunBuff;

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

    public static boolean getLevelStatus(int index) {
        return levelStatus[index];
    }

    public static void setLevelStatus(int index, boolean status) {
        DungeonApplication.levelStatus[index] = status;
    }

    public static void nextLevel() {
        if (currentLevel < levelStatus.length) {
            currentLevel++;
            levelStatus[currentLevel] = true;
        }
    }

    public static int currentLevel() {
        return currentLevel + 1;
    }

    public static String getDungeon(int index) {
        return dungeons[index];
    }

    public static void main(String[] args) {
        launch(args);
    }

}
