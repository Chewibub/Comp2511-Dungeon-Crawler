package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import unsw.dungeon.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestUtil {
    public static void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
            case "player":
                Player player = new Player(dungeon, x, y);
                dungeon.setPlayer(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(x, y);
                entity = wall;
                break;
            case "exit":
                Exit exit = new Exit(dungeon, x, y);
                entity = exit;
                break;

            case "sword":
                Sword sword = new Sword(dungeon, x, y);
                entity = sword;
                break;

            case "enemy":
                Enemy enemy = new Enemy(dungeon, x, y);
                entity = enemy;
                break;
            case "potion":
                Potion potion = new Potion(dungeon, x, y);
                entity = potion;
                break;
            case "boulder":
                Boulder boulder = new Boulder(dungeon, x, y);
                entity = boulder;
                break;
            case "switch":
                Switch s = new Switch(dungeon, x, y);
                entity = s;
                break;
        }
        dungeon.addEntity(entity);
    }

    public static void moveLeft(Player player, int steps) {
        for (int i = 0; i < steps; i++) {
            player.moveLeft();
        }
    }

    public static void moveRight(Player player, int steps) {
        for (int i = 0; i < steps; i++) {
            player.moveRight();
        }
    }

    public static void moveUp(Player player, int steps) {
        for (int i = 0; i < steps; i++) {
            player.moveUp();
        }
    }

    public static Dungeon load(String file) throws FileNotFoundException {
        JSONObject json = new JSONObject(new JSONTokener(new FileReader(file)));
        int width = json.getInt("width");
        int height = json.getInt("height");
        Dungeon dungeon = new Dungeon(width, height);
        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            TestUtil.loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        loadGoals(dungeon, jsonGoals, "ONE");
        return dungeon;

    }

    public static void moveDown(Player player, int steps) {
        for (int i = 0; i < steps; i++) {
            player.moveDown();
        }
    }

    private static void loadGoals(Dungeon dungeon, JSONObject json, String type) {
        String goalType = json.getString("goal");
        if (goalType.equals("AND")) {
            type = "AND";

            JSONArray goals = json.getJSONArray("subgoals");
            for (int i = 0; i < goals.length(); i++) {
                loadGoals(dungeon, goals.getJSONObject(i), type);
            }
        } else if (goalType.equals("OR")) {
            type = "OR";
            JSONArray goals = json.getJSONArray("subgoals");
            for (int i = 0; i < goals.length(); i++) {
                loadGoals(dungeon, goals.getJSONObject(i), type);
            }
        } else {
            Goal temp;
            switch (goalType) {
                case "exit":
                    temp = new Goal("exit", type);
                    dungeon.addGoal(temp);
                    break;
                case "boulders":
                    temp = new Goal("boulder", type);
                    dungeon.addGoal(temp);
                    break;
                case "enemies":
                    temp = new Goal("enemies", type);
                    dungeon.addGoal(temp);
                    break;
                case "treasure":
                    temp = new Goal("treasure", type);
                    dungeon.addGoal(temp);
                    break;
            }
        }
    }

    public static int countEnemy(Dungeon dungeon) {
        return (int) dungeon.getEntities().stream().filter(e -> e.getType().equals("Enemy")).count();
    }
}
