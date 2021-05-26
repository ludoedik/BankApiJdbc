package exception;

public class UnexpectedServerException extends AccountOperationsException{
    public UnexpectedServerException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
