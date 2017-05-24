package Exceptions;

/**
 * Created by Shu SHANG on 15/11/16.
 * Project streamingAlgoXPath
 */

public enum ExceptionIndex {
    queryNotStartWithSlash("XPath query should start with a slash bar"), //
    ;
    private final String text;

    ExceptionIndex(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
