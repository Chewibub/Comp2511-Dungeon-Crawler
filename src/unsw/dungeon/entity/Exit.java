package unsw.dungeon.entity;

import unsw.dungeon.Dungeon;

public class Exit extends Entity {
    
    private Dungeon dungeon;

    private boolean accessed;

    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y, "Exit");
        this.dungeon = dungeon;
        this.accessed = false;
    }

    @Override
    public void smash() {
        this.accessed = true;
    }

    @Override
    public boolean checkUsed() {
        return this.accessed;
    }

//    @Override
//    public boolean checkSolid() {
//        List<GoalOld> goals = dungeon.getGoals();
//        for (GoalOld temp : goals) {
//            if (temp != null) {
//                if (temp.getCondition().equals("ONE")) {
//                    if (temp.getType().equals("exit")) {
//                        return false;
//                    } else {
//                        if (temp.checkCompleted()) {
//                            return false;
//                        } else {
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }

}