package com.boredream.androidespressotest;

/**
 * 数据返回基类
 */
public class BaseResponse {
    /**
     * 错误码,0时代表成功
     */
    private int errNum;
    private String errMsg;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
