package com.example.shijianshenapp.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.ui.find.FindFragment;
import com.example.shijianshenapp.ui.recommend.RecommendFragment;
import com.example.shijianshenapp.ui.train.TrainFragment;
import com.example.shijianshenapp.ui.user.UserFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.am_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.am_view_pager)
    ViewPager viewPager;

    private List<String> title;
//    private int head[] = {R.mipmap.circle,R.mipmap.love,R.mipmap.circle,R.mipmap.love};
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }

    private void initData(){
        title = new ArrayList<>();
        title.add(getString(R.string.recommend));
        title.add(getString(R.string.train));
        title.add(getString(R.string.find));
        title.add(getString(R.string.user));

        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new TrainFragment());
        fragments.add(new FindFragment());
        fragments.add(new UserFragment());

    }

    private void initView(){
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);

//        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(pagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);

//        for (int i = 0;i < tabLayout.getTabCount();i++){
//            tabLayout.getTabAt(i).setCustomView(pagerAdapter.getTabView(i));
//        }
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()){
//                    case 0:
//                        tab.getCustomView().findViewById(R.id.iv).setBackgroundResource(R.mipmap.circle);
//                        break;
//                    case 1:
//                        tab.getCustomView().findViewById(R.id.iv).setBackgroundResource(R.mipmap.love);
//                        break;
//                    case 2:
//                        tab.getCustomView().findViewById(R.id.iv).setBackgroundResource(R.mipmap.circle);
//                        break;
//                    case 3:
//                        tab.getCustomView().findViewById(R.id.iv).setBackgroundResource(R.mipmap.love);
//                        break;
//                }
//                TextView textView = tab.getCustomView().findViewById(R.id.tv);
//                textView.setTextColor(getResources().getColor(R.color.tab_selected));
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                switch (tab.getPosition()){
//                    case 0:
//                        tab.getCustomView().findViewById(R.id.iv).setBackgroundResource(R.mipmap.recommend_unclick);
//                        break;
//                    case 1:
//                        tab.getCustomView().findViewById(R.id.iv).setBackgroundResource(R.mipmap.train_unclick);
//                        break;
//                    case 2:
//                        tab.getCustomView().findViewById(R.id.iv).setBackgroundResource(R.mipmap.recommend_unclick);
//                        break;
//                    case 3:
//                        tab.getCustomView().findViewById(R.id.iv).setBackgroundResource(R.mipmap.recommend_unclick);
//                        break;
//                }
//                TextView textView = tab.getCustomView().findViewById(R.id.tv);
//                textView.setTextColor(getResources().getColor(R.color.tab_unselected));
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }
//
//    class PagerAdapter extends FragmentPagerAdapter{
//
//        public PagerAdapter(@NonNull FragmentManager fm) {
//            super(fm);
//        }
//
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            Fragment f = new MyFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("title",title[position]);
//            f.setArguments(bundle);
//            return f;
//        }
//
//        @Override
//        public int getCount() {
//            return title.length;
//        }
//
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return title[position];
//        }
//
//        public View getTabView(int position){
//            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_tab,null);
//            ImageView iv = v.findViewById(R.id.iv);
//            TextView tv = v.findViewById(R.id.tv);
//            iv.setBackgroundResource(head[position]);
//            tv.setText(title[position]);
//            if (position == 0){
//                tv.setTextColor(v.getResources().getColor(R.color.tab_selected));
//            }
//            return v;
//        }
//    }
}