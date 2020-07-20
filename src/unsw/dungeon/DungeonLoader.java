package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        loadGoals(dungeon, jsonGoals, "ONE");
        dungeon.printGoals();

        return dungeon;
    }

    private void loadGoals(Dungeon dungeon, JSONObject json, String type) {
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

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
            Exit exit = new Exit(dungeon, x, y);
            onLoad(exit);
            entity = exit;
            break;

        case "sword":
            Sword sword = new Sword(dungeon, x, y);
            onLoad(sword);
            entity = sword;
            break;

        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            entity = enemy;
            break;
            
        case "potion":
            Potion potion = new Potion(dungeon, x, y);
            onLoad(potion);
            entity = potion;
            break;
            
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            Switch s = new Switch(dungeon, x, y);
            onLoad(s);
            entity = s;
            break;
        case "treasure":
            Treasure treasure = new Treasure(dungeon, x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        case "door":
            int doorID = json.getInt("id");
            Door door = new Door(dungeon, x, y, doorID);
            onLoad(door);
            entity = door;
            break;
        case "key":
            int keyID = json.getInt("id");
            Key key = new Key(dungeon, x, y, keyID);
            onLoad(key);
            entity = key;
            break;
        case "portal":
            int portalID = json.getInt("id");
            Portal portal = new Portal(dungeon, x, y, portalID);
            onLoad(portal);
            entity = portal;
            break;
        }

        // TODO Handle other possible entities
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Potion potion);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Switch s);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Door door);
    public abstract void onLoad(Portal portal);

}
