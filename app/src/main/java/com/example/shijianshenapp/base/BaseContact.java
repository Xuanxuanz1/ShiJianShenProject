package com.example.shijianshenapp.base;

import android.content.DialogInterface;
import android.view.View;

import com.ayma.base.mvp.IMvpBasePresenter;
import com.ayma.base.mvp.IMvpBaseView;

public interface BaseContact {
    interface IBaseView extends IMvpBaseView{
        void hideSoftKeyboard();

        void hideSoftKeyboard(View view);

        void showSingleAlertNoAction(CharSequence message);

        void showSingleAlertNoAction(CharSequence title, CharSequence message);

        void showSingleAlertNoAction(CharSequence title, CharSequence message, CharSequence btnText);

        void showSingleAlert(CharSequence title, CharSequence message, CharSequence btnText, View.OnClickListener listener);

        void showAlert(CharSequence title, CharSequence message, CharSequence negText, View.OnClickListener negListener, CharSequence posText, View.OnClickListener posListener);

        void showAlert(CharSequence title, CharSequence message, CharSequence negText, View.OnClickListener negListener, CharSequence posText, View.OnClickListener posListener, boolean cancelable);

        void finishActivity();

        void showTipsOnTop(String message);
    }

    interface IBasePresenter extends IMvpBasePresenter{

    }
}
