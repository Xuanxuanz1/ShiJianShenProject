package com.ayma.base.mvp;

public interface IMvpBasePresenter {
    void onStart();

    void onCreate();

    void onResume();

    void onPause();

    void onStop();

    void onRestart();

    void onDestroy();
}
