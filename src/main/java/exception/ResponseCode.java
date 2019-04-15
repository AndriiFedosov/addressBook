package exception;

public enum ResponseCode {
    NOT_FOUND(404),
    OBJECT_EXIST(302),
    VALIDATION_ERROR(301),
    WRONG_DATA_TYPE(206),
    SERVER_ERROR(500);

    private int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
