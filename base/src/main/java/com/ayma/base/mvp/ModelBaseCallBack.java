package com.ayma.base.mvp;

public interface ModelBaseCallBack {
    void requestError(Throwable t);

    void requestFailure(Throwable t);
}
