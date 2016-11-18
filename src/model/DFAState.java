package model;

import java.util.List;

/**
 * Created by Shu SHANG on 18/11/16.
 */

public class DFAState extends AutomatonState{
    private List<NFAState> dfaStates;

    public DFAState(int num, boolean isEndState, List<NFAState> dfaStates) {
        super(num, isEndState);
        this.dfaStates = dfaStates;
    }
}
