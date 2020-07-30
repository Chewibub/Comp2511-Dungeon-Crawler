package unsw.dungeon.goal;

import java.util.ArrayList;

public class OrGoals extends ComplexGoal{
    public OrGoals() {
        subGoals = new ArrayList<>();
    }

    @Override
    public boolean completed() {
        for (Goal subGoal : subGoals) {
            if (subGoal.completed()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{ OR: ");
        for (Goal subGoal : subGoals) {
            builder.append(subGoal.toString()+", ");
        }
        builder.append(" }");
        return builder.toString();
    }
}
