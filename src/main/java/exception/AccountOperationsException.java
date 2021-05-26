package exception;

public class AccountOperationsException extends RuntimeException {
    private int errorCode;
    public AccountOperationsException(int code, String msg) {
        super(msg);
    }

    public int getErrorCode() {
        return errorCode;
    }
}

