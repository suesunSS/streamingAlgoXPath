package Exceptions;

/**
 * Created by Shu SHANG on 15/11/16.
 */

public class StreamingAlgorithmException extends Exception {
    private ExceptionIndex index;
    private String formatted;
    private Object[] args;

    public StreamingAlgorithmException(ExceptionIndex index, Throwable throwable) {
        super(throwable);
        this.index = index;
        this.args = new Object[1];
        this.args[0] = throwable.getMessage();
        formatted = format(index, args);
    }

    public StreamingAlgorithmException(ExceptionIndex index, Object... args) {
        this.index = index;
        this.args = args;
        formatted = format(index, args);
    }

    private static String format(ExceptionIndex index, Object... args) {
        return String.format(index.toString(), args);
    }

    public ExceptionIndex getIndex() {
        return index;
    }

    @Override
    public String getMessage() {
        return "[#" + index.ordinal() + "] " + formatted;
    }

    public Object[] getArguments() {
        return args;
    }
}
