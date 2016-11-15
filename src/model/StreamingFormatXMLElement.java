package model;

/**
 * Created by Shu SHANG on 14/11/16.
 */
public class StreamingFormatXMLElement {
    private int startOrEnd;
    private String eleName;

    public StreamingFormatXMLElement(int startOrEnd, String eleName) {
        this.startOrEnd = startOrEnd;
        this.eleName = eleName;
    }

    public int getStartOrEnd() {
        return startOrEnd;
    }

    public String getEleName() {
        return eleName;
    }

    @Override
    public String toString() {
        if (startOrEnd == 0) {
            return "<" + eleName + ">";
        } else {
            return "</" + eleName + ">";
        }
    }
}
