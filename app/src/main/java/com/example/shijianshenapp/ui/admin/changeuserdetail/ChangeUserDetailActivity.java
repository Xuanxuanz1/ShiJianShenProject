package com.example.shijianshenapp.ui.admin.changeuserdetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.UserBean;
import com.example.shijianshenapp.ui.changeuser.ChangeUserActivity;

import java.util.List;

public class ChangeUserDetailActivity extends BaseActivity<ChangeUserDetailContact.IChangeUserDetailView,ChangeUserDetailPresenter> implements ChangeUserDetailContact.IChangeUserDetailView {

    @BindView(R.id.acud_rlv)
    RecyclerView rlv;
    private ChangeUserDetailAdapter adapter;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.change_user_detail);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_user_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ChangeUserDetailPresenter initPresenter() {
        return new ChangeUserDetailPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,ChangeUserDetailActivity.class));
    }

    @Override
    public void notifyChangeUserList(List<UserBean> list) {
        if (adapter == null){
            adapter = new ChangeUserDetailAdapter(list);
            rlv.setAdapter(adapter);
            rlv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    class ChangeUserDetailAdapter extends RecyclerView.Adapter<ChangeUserDetailAdapter.ViewHolder>{

        List<UserBean> list;

        public ChangeUserDetailAdapter(List<UserBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public ChangeUserDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_change_user_detail,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChangeUserDetailAdapter.ViewHolder holder, int position) {
            UserBean data = list.get(position);
            holder.tvUserName.setText(data.getUserName());
            holder.tvUserIdentity.setText(data.getUserName().equals("admin")?"管理员":"用户");
            holder.tvShopIntegration.setText(String.valueOf(data.getIntegration()));
            holder.tvEdit.setOnClickListener(v->{
                ChangeUserActivity.show(context,data.getUserName());
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.icud_tv_user_name)
            TextView tvUserName;
            @BindView(R.id.icud_tv_user_identity)
            TextView tvUserIdentity;
            @BindView(R.id.icud_tv_shop_integration)
            TextView tvShopIntegration;
            @BindView(R.id.icud_tv_edit)
            TextView tvEdit;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}