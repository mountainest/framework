package io.github.mountainest;

public interface ErrCode {
    int SUCCESS = 0;
    int BAD_REQUEST = 10400;
    int INTERNAL_SERVER_ERROR = 10500;
    int FEIGN_CALL_ERROR = 10600;
}
