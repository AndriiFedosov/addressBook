package constants;

public enum ResponseCode {
    FAILED_SAVE_DB(445),
    FAILED_DELETE_DB(446),
    FAILED_GET_DB(447),
    FAILED_GET_DATA(448),
    FAILED_UPDATE_DB(449),
    NOT_FOUND(404),
    OBJECT_EXIST(302),
    WRONG_DATA_TYPE(444),
    NOT_CONTENT(204),
    STORAGE_IS_EMPTY(222),
    OBJECT_WAS_NOT_CHANGED(999);

    private int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
