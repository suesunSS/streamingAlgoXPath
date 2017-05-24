package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shu SHANG on 15/11/16.
 * Project streamingAlgoXPath
 */

public class AutomatonState {
    private int num;
    private boolean isEndState;
    private boolean selfStarTransition;
    private List<AutomatonTransition> transitions;

    public AutomatonState(int num, boolean isEndState, boolean selfStarTransition) {
        this.num = num;
        this.isEndState = isEndState;
        this.selfStarTransition = selfStarTransition;
        transitions = new ArrayList<>();
    }

    public int getNum() {
        return num;
    }

    public boolean hasSelfStarTransition() {
        return selfStarTransition;
    }

    public boolean isEndState() {
        return isEndState;
    }

    public void setEndState(boolean endState) {
        isEndState = endState;
    }

    public List<AutomatonTransition> getTransitions() {
        return transitions;
    }

    public void addTransition(AutomatonTransition s) {
        transitions.add(s);
    }

    public boolean hasTransition(String input) {
        if (isEndState) {
            return false;
        }
        for (AutomatonTransition transition : transitions) {
            if (transition.getInputSymbol().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public AutomatonState nextState(String input) {
        if (hasTransition(input)) {
            for (AutomatonTransition transition : transitions) {
                if (transition.getInputSymbol().equals(input)) {
                    return transition.getToState();
                }
            }
            return null;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "[State " + getNum() +
                ", Transitions: " + transitions.toString() +
                ", endState: " + isEndState() +
                ", selfStarTransition: " + selfStarTransition + "]";
    }
}
