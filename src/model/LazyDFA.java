package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shu SHANG on 15/11/16.
 * Project streamingAlgoXPath
 */

public class LazyDFA {
    private List<DFAState> lazyDFA;

    public LazyDFA() {
        this.lazyDFA = new ArrayList<>();
    }

    public List<DFAState> getLazyDFA() {
        return lazyDFA;
    }


    @Override
    public String toString() {
        StringBuffer descLazyDFA = new StringBuffer();
        for (DFAState dfaState : lazyDFA) {
            descLazyDFA.append(dfaState.toString());
            descLazyDFA.append("\n");
        }
        return descLazyDFA.toString();
    }
}
