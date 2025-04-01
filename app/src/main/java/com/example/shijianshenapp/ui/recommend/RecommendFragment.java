package com.example.shijianshenapp.ui.recommend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseFragment;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.ui.recommend.details.RecommendDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends BaseFragment<RecommendContact.IRecommendView,RecommendPresenter> implements RecommendContact.IRecommendView {

    @BindView(R.id.arf_viewpager)
    ViewPager viewPager;
    @BindView(R.id.arf_rlv)
    RecyclerView rlv;

    private Handler handler;

    @Override
    public RecommendPresenter initPresenter() {
        return new RecommendPresenter(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recommend_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();

                currentItem++;
                viewPager.setCurrentItem(currentItem);
                handler.postDelayed(this,5000);
            }
        },3000);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                if (lastChildView != null){
                    int lastChildBottom = lastChildView.getBottom();

                    int recyclerBottom = recyclerView.getBottom() - recyclerView.getPaddingBottom();

                    int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);

                    if (dy > 0 && recyclerBottom - lastChildBottom < 100 && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
//                        showToast("暂无最新数据");
                        presenter.fetchNewRecommendData();
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected String setTitle() {
        return getString(R.string.recommend);
    }

    @Override
    public void showBanner() {
        List<Integer> list = new ArrayList<>();
        TypedArray ar = getResources().obtainTypedArray(R.array.banner_url);
        for (int i = 0;i <= 4;i++){
            list.add(ar.getResourceId(i,0));
        }
        ar.recycle();
        RecommendPagerAdapter adapter = new RecommendPagerAdapter();
        adapter.setData(list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE/2);
    }

    @Override
    public void showRecommendData(List<RecommendBean> list) {
        TypedArray ar = getResources().obtainTypedArray(R.array.recommend_img);
        int i = 0;
        for (RecommendBean data : list){
            if (i <= 7){
                data.setImage(ar.getResourceId(i,0));
            }
            else {
                data.setImage(R.drawable.friend_01);
            }
            i++;
        }
        ar.recycle();
        rlv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        RecommendAdapter adapter = new RecommendAdapter(list);
        rlv.setAdapter(adapter);
        rlv.setHasFixedSize(true);
    }

    class RecommendPagerAdapter extends PagerAdapter{

        List<Integer> list = new ArrayList<>();

        public void setData(List<Integer> list){
            this.list = list;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            int i = position % list.size();
            Integer integer = list.get(i);
            ImageView imageView = new ImageView(container.getContext());
            Glide.with(activity)
                    .load(integer)//加载目录
                    .centerCrop()//填充图片尺寸
                    .into(imageView);//加载控件
//            imageView.setImageResource(integer);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder>{

        List<RecommendBean> list;

        public RecommendAdapter(List<RecommendBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public RecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_list,null));
        }

        @Override
        public void onBindViewHolder(@NonNull RecommendAdapter.ViewHolder holder, int position) {
            RecommendBean data  = list.get(position);
            Glide.with(activity).load(data.getImage()).centerCrop().into(holder.ivHead);
//            holder.ivHead.setImageResource(data.getImage());
            holder.tvTitle.setText(data.getTitle());
            holder.root.setOnClickListener(v -> {
                RecommendDetailsActivity.show(activity,String.valueOf(position));
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.irl_iv_head)
            ImageView ivHead;
            @BindView(R.id.irl_tv_title)
            TextView tvTitle;
            @BindView(R.id.root)
            FrameLayout root;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}