package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shu SHANG on 18/11/16.
 */

public class NFA {
    private List<NFAState> nfa;  //a NFA consists of a set of states

    public NFA() {
        nfa = new ArrayList<>();
    }

    public List<NFAState> getNfa() {
        return nfa;
    }

    // This method convert the XPath query into a non deterministic finite automaton (NFA)
    public void XPathToNFA(String query) {
        int stateNum = 0;
        String[] subForms = query.substring(2).split("//");
        NFAState tempState = new NFAState(stateNum++, false);
        for (String subForm : subForms) {
            String[] stepArray = subForm.split("/");
            for (int j = 0; j < stepArray.length; j++) {
                if (j == 0) {
                    NFAState initialState = tempState;
                    NFAState interState = new NFAState(stateNum++, false);
                    NFAState nextState = new NFAState(stateNum++, false);
                    NFATrans initToInter = new NFATrans(interState, "epsilon");
                    NFATrans interToNext = new NFATrans(nextState, stepArray[j]);
                    initialState.addTransition(initToInter);
                    interState.addTransition(interToNext);
                    nfa.add(initialState);
                    nfa.add(interState);
                    tempState = nextState;
                } else {
                    NFAState interState = tempState;
                    NFAState nextState = new NFAState(stateNum++, false);
                    NFATrans interToNext = new NFATrans(nextState, stepArray[j]);
                    interState.addTransition(interToNext);
                    nfa.add(interState);
                    tempState = nextState;
                }
            }
        }
        NFAState endState = tempState;
        endState.setEndState(true);
        nfa.add(endState);
    }
}
