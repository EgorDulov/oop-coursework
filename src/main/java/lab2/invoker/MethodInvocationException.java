package lab2.invoker;

public class MethodInvocationException extends RuntimeException {

    public MethodInvocationException(String message) {
        super(message);
    }

    public MethodInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}