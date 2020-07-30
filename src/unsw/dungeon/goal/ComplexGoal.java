package unsw.dungeon.goal;

import java.util.List;

public abstract class ComplexGoal implements Goal {
    protected List<Goal> subGoals;
    public void addGoal(Goal goal) {
        subGoals.add(goal);
    }
}
