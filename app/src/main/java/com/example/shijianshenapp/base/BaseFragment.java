package com.example.shijianshenapp.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ayma.base.mvp.MvpBaseFragment;
import com.ayma.base.widget.ToastCompat;
import com.example.shijianshenapp.R;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.ButterKnife;

public abstract class BaseFragment<V extends BaseContact.IBaseView, T extends BasePresenter> extends MvpBaseFragment<V, T> implements BaseContact.IBaseView {

    private ProgressDialog progressDialog;
    private BaseActivity baseActivity;
    protected boolean isLoading = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (activity instanceof BaseActivity) {
            baseActivity = (BaseActivity) activity;
        }
    }

    protected abstract String setTitle();

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        View backVIew = $(R.id.app_title_iv_left);
        if (backVIew != null) {
            backVIew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (backClick(v)) {
                        finishActivity();
                    }
                }
            });
        }
    }


    protected void showTitle() {
        View view = $(R.id.app_title_tv_title);
        if (view == null) {
            return;
        }
    }

    protected boolean backClick(View v) {
        return true;
    }

    protected void hideBack() {
        View vLeft = $(R.id.app_title_iv_left);
        if (vLeft != null) {
            vLeft.setVisibility(View.GONE);
        }
    }

    protected void showRightBtnText(String text) {
        View right = $(R.id.app_title_btn_right);
        if (right != null) {
            right.setVisibility(View.VISIBLE);
            ((TextView) right).setText(text);
        }
    }

    protected void showRightBtn(String text, View.OnClickListener listener) {
        View right = $(R.id.app_title_btn_right);
        if (right != null) {
            right.setVisibility(View.VISIBLE);
            ((TextView) right).setText(text);
            right.setOnClickListener(listener);
        }
    }

    @Override
    public void loadData() {
        presenter.onCreate();
    }

    public void init() {

    }

    @Override
    public void onResume() {
        super.onResume();
        showTitle();
    }

    public  void finishAll(){

    }

    @Override
    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null && view.getWindowToken() != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void finishActivity() {

    }


    @Override
    public void showSingleAlertNoAction(CharSequence message) {
        showSingleAlertNoAction("", message);
    }

    @Override
    public void showSingleAlertNoAction(CharSequence title, CharSequence message) {
        showSingleAlertNoAction(title, message, "确定");
    }

    @Override
    public void showSingleAlertNoAction(CharSequence title, CharSequence message, CharSequence btnText) {
        showSingleAlert(title,message,btnText,null);
    }

    @Override
    public void showSingleAlert(CharSequence title, CharSequence message, CharSequence btnText, View.OnClickListener listener) {
        showAlert(title, message, null, null, btnText, listener);
    }

    @Override
    public void showAlert(CharSequence title, CharSequence message, CharSequence negText, View.OnClickListener negListener, CharSequence posText, View.OnClickListener posListener) {
        showAlert(title, message, negText, negListener, posText, posListener, false);
    }

    @Override
    public void showAlert(CharSequence title, CharSequence message, CharSequence negText, View.OnClickListener negListener, CharSequence posText, View.OnClickListener posListener, boolean cancelable) {
        if (activity.isFinishing()){
            return;
        }
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom_simple,null,false);

        TextView tvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        TextView tvMessage = (TextView) dialogView.findViewById(R.id.tv_message);
        TextView btnCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        TextView btnOk = (TextView) dialogView.findViewById(R.id.tv_ok);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        AlertDialog alertDialog = builder.setCancelable(cancelable).create();
        alertDialog.setView(dialogView);
        if (TextUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        }
        else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(message)){
            tvMessage.setText(message);
        }
        if (TextUtils.isEmpty(negText)){
            btnCancel.setVisibility(View.GONE);
        }
        else {
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setText(negText);
            if (negListener == null){
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
            else {
                btnCancel.setOnClickListener(negListener);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negListener.onClick(v);
                        alertDialog.dismiss();
                    }
                });
            }
        }
        if (TextUtils.isEmpty(posText)){
            btnOk.setVisibility(View.GONE);
            btnOk.setOnClickListener(null);
        }
        else {
            btnOk.setText(posText);
            if (posListener == null){
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
            else {
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        posListener.onClick(v);
                        alertDialog.dismiss();
                    }
                });
            }
        }
        if (!activity.isFinishing()){
            alertDialog.show();
        }
    }


    @Override
    public void showLoading() {
        showLoading("正在加载...");
    }

    @Override
    public void showLoading(String tips) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setCancelable(false);
            Window window = progressDialog.getWindow();
            if (window != null) {
                window.setWindowAnimations(R.style.progressAnim);
            }
        }
        isLoading = true;
        progressDialog.setMessage(tips);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            if (rootView != null) {
                rootView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!activity.isFinishing())
                            if (progressDialog != null) {
                                progressDialog.dismiss();
                                isLoading = false;
                            }
                    }
                }, 500);
            }
        }
    }

    @Override
    public void showToast(String message) {
        new ToastCompat().showToast(activity, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showTipsOnTop(String message) {
        if (activity == null) {
            showToast(message);
            return;
        }
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showTipsOnTop(message);
        } else {
            showToast(message);
        }
    }

//    public void navigateTo(int actionId){
//        NavController navController = Navigation.findNavController(rootView);
//        navController.navigate(actionId);
//    }
}
