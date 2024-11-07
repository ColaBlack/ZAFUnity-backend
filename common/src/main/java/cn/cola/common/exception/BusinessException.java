package cn.cola.common.exception;


import cn.cola.common.common.ErrorCode;
import lombok.Getter;

/**
 * 自定义业务异常类
 *
 * @author ColaBlack
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}