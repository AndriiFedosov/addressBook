package exception;

import constants.ResponseCode;

import static constants.ConstantsMessages.NOT_FOUND_MESSAGE;

public class AddressBookException extends Exception {

    private ResponseCode code;
    private String message;

    public AddressBookException(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public AddressBookException(ResponseCode code) {
        this.code = code;
    }

    public AddressBookException() {
        this.code = ResponseCode.NOT_FOUND;
        this.message = NOT_FOUND_MESSAGE;
    }

    public ResponseCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
