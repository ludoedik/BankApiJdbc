package exception;

import entity.AccountEntity;

public class BusinessException extends AccountOperationsException {
    public BusinessException(int errorCode, String msg) {
        super(errorCode, msg);
    }
}
