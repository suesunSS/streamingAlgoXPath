package model;

/**
 * Created by Shu SHANG on 18/11/16.
 */

public class AutomatonTransition {
    private AutomatonState toState;
    private String inputSymbol;

    public AutomatonTransition(AutomatonState toState, String inputSymbol) {
        this.toState = toState;
        this.inputSymbol = inputSymbol;
    }

    public AutomatonState getToState() {
        return toState;
    }

    public String getInputSymbol() {
        return inputSymbol;
    }

    public void setToState(AutomatonState toState) {
        this.toState = toState;
    }

    public void setInputSymbol(String inputSymbol) {
        this.inputSymbol = inputSymbol;
    }

    @Override
    public String toString() {
        return "To: " + toState.getNum() + ", input: " + inputSymbol;
    }
}
