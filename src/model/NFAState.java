package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shu SHANG on 17/11/16.
 */

public class NFAState {
    private int num;
    private boolean isEndState;
    private List<NFATrans> transitions;

    public NFAState(int num, boolean isEndState) {
        this.num = num;
        this.isEndState = isEndState;
        this.transitions  = new ArrayList<>();
    }

    public NFAState() {

    }

    public int getNum() {
        return num;
    }

    public boolean isEndState() {
        return isEndState;
    }

    public List<NFATrans> getTransitions() {
        return transitions;
    }

    public void setEndState(boolean endState) {
        isEndState = endState;
    }

    public void addTransition(NFATrans s){
        transitions.add(s);
    }

    @Override
    public String toString() {
        return "[State " + num + ", Transitions: "+ transitions.toString() + ", endState: " + isEndState + "]";
    }
}
