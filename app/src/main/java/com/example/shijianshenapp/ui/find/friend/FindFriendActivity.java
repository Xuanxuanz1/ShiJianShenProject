package com.example.shijianshenapp.ui.find.friend;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.FindBean;
import com.example.shijianshenapp.weiget.TanStackLayout;

import java.util.ArrayList;
import java.util.List;

public class FindFriendActivity extends BaseActivity<FindFriendContact.IFindFriendView,FindFriendPresenter> implements FindFriendContact.IFindFriendView {

    @BindView(R.id.aff_tsl)
    TanStackLayout tanStackLayout;

    private List<FindBean> findList;
    private String name[];
    private String acatarUrls[];

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.find_friend);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_friend;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findList = new ArrayList<>();
        name = getResources().getStringArray(R.array.find_names);
//        TypedArray ar = getResources().obtainTypedArray(R.array.avatar_urls);
        acatarUrls = getResources().getStringArray(R.array.avatar_urls);
        for (int i = 0;i < name.length;i++){
//            acatarUrls[i] = ar.getResourceId(i,0);
            FindBean data = new FindBean(name[i],acatarUrls[i]);
            findList.add(data);
        }
//        ar.recycle();
        tanStackLayout.setDatas(findList);
    }

    @Override
    public FindFriendPresenter initPresenter() {
        return new FindFriendPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,FindFriendActivity.class));
    }
}