package exception;

/**
 * Implementation of RuntimeException class
 * errorCode is an html error code for server response.
 */
public class AccountOperationsException extends RuntimeException {
    private int errorCode;
    public AccountOperationsException(int code, String msg) {
        super(msg);
        errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

