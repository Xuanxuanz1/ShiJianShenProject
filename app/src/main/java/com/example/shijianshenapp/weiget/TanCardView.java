package com.example.shijianshenapp.weiget;

import android.animation.Animator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.bean.FindBean;
import com.example.shijianshenapp.utils.DensityUtil;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TanCardView extends FrameLayout implements View.OnTouchListener {

    private static final int PADDINGVALUE = 16;
    private static final float CARD_ROTATION_DEGREES = 40.0f;
    public static final int DURATIONTIME = 300;
    private ImageView ivHead,ivTips;
    private TextView tvName;
    private int padding,screenWidth;
    private float downX,downY,newX,newY,dX,dY,rightBoundary,leftBoundary;
    private OnLoadMoreListener loadMoreListener;


    public TanCardView(@NonNull Context context) {
        this(context,null);
    }

    public TanCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TanCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        if (!isInEditMode()){
            inflate(context, R.layout.card_view,this);
            screenWidth = DensityUtil.getScreenWidth(context);
            leftBoundary = screenWidth * (1.0f / 6.0f);
            rightBoundary = screenWidth * (5.0f / 6.0f);
            ivHead = findViewById(R.id.iv_head);
            ivTips = findViewById(R.id.iv_tips);
            tvName = findViewById(R.id.tv_name);
            padding = DensityUtil.dip2px(context,PADDINGVALUE);
            setOnTouchListener(this);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TanStackLayout tanStackLayout = ((TanStackLayout) v.getParent());
        TanCardView topCard = (TanCardView) tanStackLayout.getChildAt(tanStackLayout.getChildCount() - 1);
        if (topCard.equals(v)){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    v.clearAnimation();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    newX = event.getX();
                    newY = event.getY();
                    dX = newX - downX;
                    dY = newY - downY;
                    float posX = v.getX() + dX;
                    v.setX(v.getX() + dX);
                    v.setY(v.getY() + dY);
                    float rotation = (CARD_ROTATION_DEGREES * (posX)) / screenWidth;
                    int halfCardHeight = (v.getHeight() / 2);
                    if (downY < halfCardHeight - (2 * padding)){
                        v.setRotation(rotation);
                    }
                    else {
                        v.setRotation(-rotation);
                    }
                    float alpha = (posX - padding) / (screenWidth * 0.3f);
                    if(alpha > 0){
                        ivTips.setAlpha(alpha);
                        ivTips.setImageResource(R.drawable.ic_like);
                    }
                    else {
                        ivTips.setAlpha(-alpha);
                        ivTips.setImageResource(R.drawable.ic_nope);
                    }
                    return true;
                case MotionEvent.ACTION_UP:
                    if (isBeyondLeftBoundary(v)){
                        removeCard(v,-(screenWidth * 2));
                    }
                    else if (isBeyondRightBoundary(v)){
                        removeCard(v,(screenWidth * 2));
                    }
                    else {
                        resetCard(v);
                    }
                    return true;
                default:
                    return super.onTouchEvent(event);
            }
        }
        return super.onTouchEvent(event);
    }

    private boolean isBeyondLeftBoundary(View view){
        return (view.getX() + (view.getWidth() / 2) < leftBoundary);
    }

    private boolean isBeyondRightBoundary(View view){
        return (view.getX() + (view.getWidth() / 2) > rightBoundary);
    }

    private  void removeCard(final View view,int xPos){
        view.animate().x(xPos).y(0).setInterpolator(new AnticipateOvershootInterpolator()).setDuration(DURATIONTIME).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null){
                    viewGroup.removeView(view);
                }
                int count = viewGroup.getChildCount();
                if(count == 1 && loadMoreListener != null){
                    loadMoreListener.onLoad();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

     private void resetCard(final View view){
        view.animate().x(0).y(0).rotation(0).setInterpolator(new OvershootInterpolator()).setDuration(DURATIONTIME);
        ivTips.setAlpha(0f);
     }

     public void bind(FindBean user){
        if (user == null){
            return;
        }
        if (!TextUtils.isEmpty(user.getAvatarUrl())){
            Glide.with(ivHead.getContext()).load(user.getAvatarUrl()).into(ivHead);

//         ivHead.setImageResource(user.getAvatarUrl());
        }
        if (!TextUtils.isEmpty(user.getName())){
            tvName.setText(user.getName());
        }
     }

    public interface OnLoadMoreListener{
        void onLoad();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }
}
