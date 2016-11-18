package model;

/**
 * Created by Shu SHANG on 18/11/16.
 */

public class NFATrans {
    private NFAState toState;
    private String inputSymbol;

    public NFATrans(NFAState toState, String inputSymbol) {
        this.toState = toState;
        this.inputSymbol = inputSymbol;
    }

    public NFAState getToState() {
        return toState;
    }

    public String getInputSymbol() {
        return inputSymbol;
    }

    public void setToState(NFAState toState) {
        this.toState = toState;
    }

    public void setInputSymbol(String inputSymbol) {
        this.inputSymbol = inputSymbol;
    }

    @Override
    public String toString() {
        return "to: " + toState.getNum() + ", input:" + inputSymbol;
    }
}
