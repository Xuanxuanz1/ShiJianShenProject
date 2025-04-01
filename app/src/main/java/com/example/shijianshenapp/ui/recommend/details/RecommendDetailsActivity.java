package com.example.shijianshenapp.ui.recommend.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import java.util.List;

public class RecommendDetailsActivity extends BaseActivity<RecommendDetailsContact.IRecommendDetailsView,RecommendDetailsPresenter> implements RecommendDetailsContact.IRecommendDetailsView {

    @BindView(R.id.ard_tv_message)
    TextView tvMessage;
    @BindView(R.id.ard_tv_title)
    TextView tvTitle;
    @BindView(R.id.ard_iv_img)
    ImageView ivImg;

    private String positition;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.recommend_details);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        positition = intent.getStringExtra("positition");
        int number = Integer.parseInt(positition);
        if (number < 8){
            ivImg.setVisibility(View.VISIBLE);
            TypedArray ar = getResources().obtainTypedArray(R.array.recommend_img);
            Glide.with(context).load(ar.getResourceId(number,0)).centerCrop().into(ivImg);
//        ivImg.setImageResource(ar.getResourceId(number,0));
            ar.recycle();
        }
        presenter.fetchNewList(number);
    }

    @Override
    public RecommendDetailsPresenter initPresenter() {
        return new RecommendDetailsPresenter(context);
    }

    public static void show(Context context,String positition){
        Intent intent = new Intent(context,RecommendDetailsActivity.class);
        intent.putExtra("positition",positition);
        context.startActivity(intent);
    }


    @Override
    public void fetchSccuess(RecommendDetailBean data) {
        tvTitle.setText(data.getTitle());
        tvMessage.setText(data.getMessage());
    }
}