package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shu SHANG on 15/11/16.
 * Project streamingAlgoXPath
 */

public class NFA {
    private List<AutomatonState> nfa;  //a NFA consists of a set of states

    public NFA() {
        nfa = new ArrayList<>();
    }

    public List<AutomatonState> getNfa() {
        return nfa;
    }

    // This method convert the XPath query into a non deterministic finite automaton (NFA)
    public void XPathToNFA(String query) {
        int stateNum = 0;
        String[] subForms = query.substring(2).split("//");
        AutomatonState tempState = new AutomatonState(stateNum++, false, false);
        for (String subForm : subForms) {
            String[] stepArray = subForm.split("/");
            for (int j = 0; j < stepArray.length; j++) {
                if (j == 0) {
                    AutomatonState initialState = tempState;
                    AutomatonState interState = new AutomatonState(stateNum++, false, true);
                    AutomatonState nextState = new AutomatonState(stateNum++, false, false);
                    AutomatonTransition initToInter = new AutomatonTransition(interState, "epsilon");
                    AutomatonTransition interToNext = new AutomatonTransition(nextState, stepArray[j]);
                    initialState.addTransition(initToInter);
                    interState.addTransition(interToNext);
                    nfa.add(initialState);
                    nfa.add(interState);
                    tempState = nextState;
                } else {
                    AutomatonState interState = tempState;
                    AutomatonState nextState = new AutomatonState(stateNum++, false, false);
                    AutomatonTransition interToNext = new AutomatonTransition(nextState, stepArray[j]);
                    interState.addTransition(interToNext);
                    nfa.add(interState);
                    tempState = nextState;
                }
            }
        }
        AutomatonState endState = tempState;
        endState.setEndState(true);
        nfa.add(endState);
    }

    @Override
    public String toString() {
        StringBuffer descNFA = new StringBuffer();
        for (AutomatonState state: nfa) {
            descNFA.append(state.toString());
            descNFA.append("\n");
        }
        return descNFA.toString();
    }
}
