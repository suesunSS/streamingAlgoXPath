package model;

/**
 * Created by Shu SHANG on 18/11/16.
 */

public abstract class AutomatonState {
    private int num;
    private boolean isEndState;

    public AutomatonState(int num, boolean isEndState) {
        this.num = num;
        this.isEndState = isEndState;
    }

    public int getNum() {
        return num;
    }

    public boolean isEndState() {
        return isEndState;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setEndState(boolean endState) {
        isEndState = endState;
    }
}
