package com.example.shijianshenapp.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.FrameLayout;

import com.example.shijianshenapp.bean.FindBean;
import com.example.shijianshenapp.utils.DensityUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TanStackLayout extends FrameLayout implements TanCardView.OnLoadMoreListener {

    private ViewGroup.LayoutParams params = null;
    public static final float BASESCALE_X_VALUE = 50.0f;
    public static final int BASESCALE_Y_VALUE = 8;
    public static final int DURATIONTIME = 300;
    private static final int STACK_SIZE = 4;
    private int index = 0;
    private int scaleY;
    private TanCardView tanCardView;
    private List<FindBean> userList;

    public TanStackLayout(@NonNull Context context) {
        this(context,null);
    }

    public TanStackLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TanStackLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        scaleY = DensityUtil.dip2px(getContext(), BASESCALE_Y_VALUE);
    }

    private void addCard(TanCardView view){
        int count = getChildCount();
        addView(view,0,params);
        float scaleX = 1 - (count / BASESCALE_Y_VALUE);
        view.animate().x(0).y(count * scaleY).scaleX(scaleX).setInterpolator(new AnticipateOvershootInterpolator()).setDuration(DURATIONTIME);
    }

    public void setDatas(List<FindBean> list){
        this.userList = list;
        if (userList == null){
            return;
        }
        for (int i = index;index < i+STACK_SIZE;index++){
            tanCardView = new TanCardView(getContext());
            tanCardView.bind(userList.get(index));
            tanCardView.setOnLoadMoreListener(this);
            addCard(tanCardView);
        }
    }

    @Override
    public void onLoad() {
        for (int i = index;index < i+(STACK_SIZE - 1);index++){
            if (index == userList.size()){
                return;
            }
            tanCardView = new TanCardView(getContext());
            tanCardView.bind(userList.get(index));
            tanCardView.setOnLoadMoreListener(this);
            addCard(tanCardView);
        }
        int childCount = getChildCount();
        for (int i = childCount - 1;i >= 0;i--){
            TanCardView tanCardView = (TanCardView) getChildAt(i);
            if (tanCardView != null){
                float scaleValue = 1 - ((childCount - 1 - i) / 50.0f);
                tanCardView.animate().x(0).y((childCount - 1 - i) * scaleY).scaleX(scaleValue).rotation(0).setInterpolator(new AnticipateOvershootInterpolator()).setDuration(DURATIONTIME);
            }
        }
    }
}
