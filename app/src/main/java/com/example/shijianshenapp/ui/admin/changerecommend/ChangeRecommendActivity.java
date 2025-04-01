package com.example.shijianshenapp.ui.admin.changerecommend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.RecommendBean;
import com.example.shijianshenapp.bean.RecommendDetailBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class ChangeRecommendActivity extends BaseActivity<ChangeRecommendContact.IChangeRecommendView,ChangeRecommendPresenter> implements ChangeRecommendContact.IChangeRecommendView {

    @BindView(R.id.acr_rlv)
    RecyclerView rlv;
    private ChangeRecommendAdapter adapter;
    private AlertDialog addRecommednDialog;
    private View addRecommendView;
    private AlertDialog editRecommendDialog;
    private View editRecommendView;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.change_recommend);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_recommend;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showRightBtn("新增发布资讯", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<RecommendBean> list = new ArrayList<>();
//                RecommendBean data = new RecommendBean("测试",R.drawable.friend_01);
//                list.add(data);
//                Operator.saveAll(list);
                showAddRecomendDialog();
            }
        });
    }

    @Override
    public ChangeRecommendPresenter initPresenter() {
        return new ChangeRecommendPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,ChangeRecommendActivity.class));
    }

    @Override
    public void notifyChangeRecommend(List<RecommendBean> list) {
        if (adapter == null){
            adapter = new ChangeRecommendAdapter(list);
            rlv.setAdapter(adapter);
            rlv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showAddRecomendDialog() {
        if (addRecommendView == null){
            addRecommendView = getLayoutInflater().inflate(R.layout.dialog_change_new_recommend,null,false);
        }
        ImageView ivClose = addRecommendView.findViewById(R.id.dcnr_iv_close);
        EditText etTitle = addRecommendView.findViewById(R.id.dcnr_tv_title);
        EditText etMessage = addRecommendView.findViewById(R.id.dcnr_et_message);
        Button btnSend = addRecommendView.findViewById(R.id.dcnr_btn_send);

        if (addRecommednDialog == null){
            addRecommednDialog = new AlertDialog.Builder(context).setView(addRecommendView).setCancelable(false).create();
        }

        ivClose.setOnClickListener(v->{
            etTitle.setText("");
            etMessage.setText("");
            addRecommednDialog.dismiss();
        });

        btnSend.setOnClickListener(v->{
            if (TextUtils.isEmpty(etTitle.getText())){
                showToast("文章不能没有标题，就像西方不能没有耶路撒冷");
                return;
            }
            if (TextUtils.isEmpty(etMessage.getText())){
                showToast("没有内容的文章，跟没有梦想的咸鱼有什么区别呢？");
                return;
            }
            presenter.addNewRecommend(etTitle.getText().toString(),etMessage.getText().toString());
            addRecommednDialog.dismiss();
        });

        if (addRecommednDialog.getWindow() != null){
            addRecommednDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            addRecommednDialog.show();
        }
    }

    @Override
    public void showEditRecommendDialog(int postition,RecommendDetailBean data) {
        if (editRecommendView == null){
            editRecommendView = getLayoutInflater().inflate(R.layout.dialog_edit_recommend,null,false);
        }

        ImageView ivClose = editRecommendView.findViewById(R.id.der_iv_close);
        EditText etTitle  = editRecommendView.findViewById(R.id.der_tv_title);
        EditText etMessage = editRecommendView.findViewById(R.id.der_et_message);
        Button btnSend = editRecommendView.findViewById(R.id.der_btn_send);

        if (editRecommendDialog == null){
            editRecommendDialog = new AlertDialog.Builder(context).setView(editRecommendView).setCancelable(false).create();
        }

        etTitle.setText(data.getTitle());
        etMessage.setText(data.getMessage());

        ivClose.setOnClickListener(v -> {
            editRecommendDialog.dismiss();
        });

        btnSend.setOnClickListener(v->{
            if (TextUtils.isEmpty(etTitle.getText())){
                showToast("文章不能没有标题，就像西方不能没有耶路撒冷");
                return;
            }
            if (TextUtils.isEmpty(etMessage.getText())){
                showToast("没有内容的文章，跟没有梦想的咸鱼有什么区别呢？");
                return;
            }
            presenter.editRecommend(postition,etTitle.getText().toString().trim(),etMessage.getText().toString());
            editRecommendDialog.dismiss();
        });

        if (editRecommendDialog.getWindow() != null){
            editRecommendDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            editRecommendDialog.show();
        }
    }


    class ChangeRecommendAdapter extends RecyclerView.Adapter<ChangeRecommendAdapter.ViewHolder>{

        List<RecommendBean> list;

        public ChangeRecommendAdapter(List<RecommendBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public ChangeRecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_change_recommend_list,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChangeRecommendAdapter.ViewHolder holder, int position) {
            RecommendBean data = list.get(position);
            if (position % 2 == 0){
                holder.root.setBackgroundColor(getResources().getColor(R.color.main_bg));
            }
            else {
                holder.root.setBackgroundColor(getResources().getColor(R.color.white));
            }
            holder.tvTitle.setText(data.getTitle());

            holder.tvEdit.setOnClickListener(v -> {
                showEditRecommendDialog(holder.getLayoutPosition(),presenter.getRecommendDetail(position));
            });

            holder.tvDelet.setOnClickListener(v -> {
                presenter.deletRecommend(position);
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.icrl_tv_title)
            TextView tvTitle;
            @BindView(R.id.icrl_tv_edit)
            TextView tvEdit;
            @BindView(R.id.icrl_tv_delet)
            TextView tvDelet;
            @BindView(R.id.root)
            LinearLayout root;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}