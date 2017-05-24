package Model;

/**
 * Created by Shu SHANG on 15/11/16.
 * Project streamingAlgoXPath
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

    @Override
    public String toString() {
        return "To: " + toState.getNum() + ", input: " + inputSymbol;
    }
}
