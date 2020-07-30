package unsw.dungeon.goal;

import java.util.ArrayList;

public class AndGoals extends ComplexGoal{
    public AndGoals() {
        subGoals = new ArrayList<>();
    }
    @Override
    public boolean completed() {
        for (Goal subGoal : subGoals) {
            if (!subGoal.completed()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{ AND: ");
        for (Goal subGoal : subGoals) {
            builder.append(subGoal.toString()+", ");
        }
        builder.append(" }");
        return builder.toString();
    }
}
