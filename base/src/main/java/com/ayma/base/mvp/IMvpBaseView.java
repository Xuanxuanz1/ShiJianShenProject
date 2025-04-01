package com.ayma.base.mvp;

public interface IMvpBaseView {
    void showLoading();

    void showLoading(String tips);

    void hideLoading();

    void showToast(String message);
}
