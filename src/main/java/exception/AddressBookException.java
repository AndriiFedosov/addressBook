package exception;

import entity.Contact;

public class AddressBookException extends Exception {

    private ResponseCode code;
    private String message;

    public AddressBookException(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
