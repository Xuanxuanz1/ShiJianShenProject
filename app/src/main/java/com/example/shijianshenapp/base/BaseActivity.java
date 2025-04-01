package com.example.shijianshenapp.base;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ayma.base.mvp.MvpBaseActivity;
import com.ayma.base.widget.ToastCompat;
import com.example.shijianshenapp.R;

public abstract class BaseActivity<V extends BaseContact.IBaseView,T extends BasePresenter> extends MvpBaseActivity<V, T> implements BaseContact.IBaseView {

    protected Handler handler;
    private ProgressDialog progressDialog;
    protected boolean isResume = false;
    protected boolean isLoading = false;


    protected abstract String setTitle();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        handler = new Handler(context.getMainLooper());
        presenter.onCreate();
        View backView = $(R.id.app_title_iv_left);
        if (backView != null){
            backView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishActivity();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog = null;
        }
        super.onDestroy();
    }

    protected void hideBack(){
        View vLeft = $(R.id.app_title_iv_left);
        if (vLeft != null){
            vLeft.setVisibility(View.GONE);
        }
    }

    protected void showRightBtnText(String text){
        View right = $(R.id.app_title_btn_right);
        if (right != null){
            right.setVisibility(View.VISIBLE);
            ((Button) right).setText(text);
        }
    }

    protected void showRightBtn(String text,View.OnClickListener listener){
        View right = $(R.id.app_title_btn_right);
        if (right != null){
            right.setVisibility(View.VISIBLE);
            ((Button) right).setText(text);
            right.setOnClickListener(listener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResume = true;
        showTitle();
    }

    private void showTitle(){
        View view = $(R.id.app_title_tv_title);
        if (view == null){
            return;
        }
        ((TextView) view).setText(setTitle());
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }

    @Override
    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null){
            if (this.getCurrentFocus().getWindowToken() != null){
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null && view.getWindowToken() != null){
            imm.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void showLoading(){
        showLoading("正在加载...");
    }

    public void showLoading(String tips){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            Window window = progressDialog.getWindow();
            if (window != null){
                window.setWindowAnimations(R.style.progressAnim);
            }
        }
        isLoading = true;
        progressDialog.setMessage(tips);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isFinishing()){
                        if (progressDialog != null){
                            progressDialog.dismiss();
                            isLoading = false;
                        }
                    }
                }
            },500);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showSingleAlertNoAction(CharSequence message) {
        showSingleAlertNoAction("",message);
    }

    @Override
    public void showSingleAlertNoAction(CharSequence title, CharSequence message) {
        showSingleAlertNoAction(title,message,"确定");
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
        if (isFinishing()){
            return;
        }
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom_simple,null,false);

        TextView tvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        TextView tvMessage = (TextView) dialogView.findViewById(R.id.tv_message);
        TextView btnCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        TextView btnOk = (TextView) dialogView.findViewById(R.id.tv_ok);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
        if (!isFinishing()){
            alertDialog.show();
        }
    }

    @Override
    public void showToast(String message) {
        new ToastCompat().showToast(context,message, Toast.LENGTH_SHORT);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.anim_enter_finish,R.anim.anim_out_finish);
    }

    public boolean onTouchEvent(MotionEvent event){
        if (null != this.getCurrentFocus()){
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void showTipsOnTop(String message) {
        View clTitle = findViewById(R.id.app_title_cl_tips);
        if (clTitle == null){
            showToast(message);
            return;
        }
        View viewById = findViewById(R.id.app_title_tv_tips);
        if (viewById == null){
            showToast(message);
            return;
        }
        if (handler == null){
            showToast(message);
            return;
        }

        TextView tvTips = (TextView) viewById;
        tvTips.setText(message);
        clTitle.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFinishing()){
                    return;
                }
                clTitle.setVisibility(View.GONE);
            }
        },3000);
    }
}