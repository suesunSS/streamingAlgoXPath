package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shu SHANG on 15/11/16.
 * Project streamingAlgoXPath
 */

public class DFAState extends AutomatonState {
    private ArrayList<AutomatonState> nfaStates; //a DFA state could contain several NFA states

    public DFAState(int num, boolean isEndState, boolean selfStarTransition, ArrayList<AutomatonState> nfaStates) {
        super(num, isEndState, selfStarTransition);
        this.nfaStates = nfaStates;
    }

    public ArrayList<AutomatonState> getNfaStates() {
        return nfaStates;
    }

    @Override
    public boolean hasTransition(String input) {
        for(AutomatonState state : nfaStates){
            if(state.hasTransition(input)){
                return true;
            }
        }
        return false;
    }

    public boolean stateHasTransition(String input) {
        List<AutomatonState> allNFAStates = this.getNfaStates();
        for (AutomatonState nfaState : allNFAStates) {
            if (nfaState.hasTransition(input)) {
                return true;
            }
            if (nfaState.hasSelfStarTransition()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<AutomatonState> nextPossibleStates(String input) {
        ArrayList<AutomatonState> nextPossibleStates = new ArrayList<>();
        for (AutomatonState nfaSate : this.getNfaStates()) {
            AutomatonState possibleState = nfaSate.nextState(input);
            if (possibleState != null) {
                nextPossibleStates.add(possibleState);
            }
            if (nfaSate.hasSelfStarTransition()) {
                nextPossibleStates.add(nfaSate);
            }
            if (nfaSate.hasTransition("epsilon")) {
                nextPossibleStates.add(nfaSate);
            }
        }
        return nextPossibleStates;
    }

    @Override
    public String toString() {
        StringBuffer nfaStatesNum = new StringBuffer();
        nfaStatesNum.append("[");
        for (AutomatonState state : nfaStates) {
            nfaStatesNum.append(state.getNum() + " ");
        }
        nfaStatesNum.append("]");
        return "[State D" + getNum() + nfaStatesNum.toString() +
                ", endState: " + isEndState() +
                ", selfStarTransition: " + hasSelfStarTransition()+ "]";
    }
}
