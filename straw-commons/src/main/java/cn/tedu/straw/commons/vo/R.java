package cn.tedu.straw.commons.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 响应到客户端的JSON数据的封装类
 */
@Data
@Accessors(chain = true)
public class R<T> {

    /**
     * 响应状态码
     */
    private Integer state;
    /**
     * 出错时的错误提示信息
     */
    private String message;
    /**
     * 成功时响应给客户端的数据
     */
    private T data;

    public R() {
        super();
    }

    /**
     * 操作成功
     * @return  状态码已经标记为"成功"的对象
     */
    public static R ok() {
        return new R().setState(State.SUCCESS);
    }

    /**
     * 操作失败
     * @param failureState  操作失败的状态码
     * @param e 操作失败时抛出并被捕获的异常对象
     * @return  已经封装了操作失败的状态码、错误描述信息的对象
     */
    public static R failure(Integer failureState, Throwable e) {
        return new R().setState(failureState).setMessage(e.getMessage());
    }

    /**
     * 状态码
     */
    public static interface State{
        int SUCCESS = 2000;
        /**
         * 邀请码错误
         */
        int ERR_INVITE_CODE = 4000;
        /**
         * 手机号码冲突
         */
        int ERR_PHONE_DUPLICATE = 4001;
        /**
         * 插入数据失败
         */
        int ERR_INSERT_FAIL = 4002;
        /**
         * 未知错误
         */
        int ERR_UNKNOWN = 9000;
    }
}
