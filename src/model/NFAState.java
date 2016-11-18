package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shu SHANG on 17/11/16.
 */

public class NFAState extends  AutomatonState{
    private List<AutomatonTransition> transitions;

    public NFAState(int num, boolean isEndState) {
        super(num, isEndState);
        this.transitions  = new ArrayList<>();
    }

    public List<AutomatonTransition> getTransitions() {
        return transitions;
    }

    public void addTransition(AutomatonTransition s){
        transitions.add(s);
    }

    @Override
    public String toString() {
        return "[State " + super.getNum() + ", Transitions: "+ transitions.toString() + ", endState: " + super.isEndState() + "]";
    }
}
