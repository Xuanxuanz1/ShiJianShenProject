package com.ayma.base.mvp;

public interface ModelParamCallBack<T> extends ModelBaseCallBack {
    void requestSuccess(T data);
}
