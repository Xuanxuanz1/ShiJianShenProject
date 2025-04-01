package com.ayma.base.mvp;

import retrofit2.Response;

public interface IMvpBaseModel {

    void onDestroy();

    Throwable generateThrowable(String message);

    Throwable generateThrowable(Throwable t);

    Throwable generateThrowable(Response response);

}
