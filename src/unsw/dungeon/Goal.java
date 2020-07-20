package unsw.dungeon;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Goal implements GoalInterface {
    private Boolean completed;
    private String type;
    private String condition;

    private CopyOnWriteArrayList<Entity> entities;

    private Dungeon dungeon;

    public Goal(String type, String condition) {
        this.completed = false;
        this.type = type;
        this.condition = condition;
        this.entities = new CopyOnWriteArrayList<Entity>();
    }

    @Override
    public void updateGoal() {
        if (type.equals("exit") && entities != null) {
            for (Entity temp : entities) {
                if (temp != null) {
                    if (temp.checkUsed()) {
                        this.completed = true;
                    }
                }
            }
        }
    }

    // @Override
    // public void updateGoal(Treasure treasure) {
    //     if (exit.checkAccessed() && type.equals("exit")) {
    //         this.completed = true;
    //     }
    // }

    public boolean checkCompleted() {
        return completed;
    }

    //Goals such as exit, boulders, etc
    public String getType() {
        return this.type;
    }

    //Conditionals such as AND and OR
    public String getCondition() {
        return this.condition;
    }

    public String toString() {
        if (type.equals("exit")) {
            return "Get to the exit";
        } 
        return "TODO";
    }

    public void addEntities(List<Entity> listEntities) {
        for (Entity temp : listEntities) {
            if (temp != null) {
                if (type.equals("exit")) {
                    if (temp.getType().equals("Exit")) {
                        entities.add(temp);
                    }
                } 
            }
        } 
    }
}