package com.example.shijianshenapp.ui.find.circle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayma.base.util.DateTimeUtils;
import com.bumptech.glide.Glide;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.CommentBean;
import com.example.shijianshenapp.bean.FriendCircleBean;
import com.example.shijianshenapp.bean.UserBean;
import com.example.shijianshenapp.ui.find.FindContact;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class FindCircleActivity extends BaseActivity<FindCircleContact.IFindCircleView,FindCirclePresenter> implements FindCircleContact.IFindCircleView {

    @BindView(R.id.afc_tva_name)
    TextView tvUserName;
    @BindView(R.id.afc_rlv)
    RecyclerView rlvData;
    @BindView(R.id.afc_abl)
    AppBarLayout appBarLayout;
    @BindView(R.id.afc_iv_head)
    ImageFilterView imageFilterView;

    private FindCircleAdapter adapter;
    private FindCircleCommentAdapter commentAdapter;
    private View sendCircleView;
    private AlertDialog sendCircleDialog;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.find_circle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_circle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvUserName.setText(UserBean.loginName);
        showRightBtn("去发表", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showToast("发表");
                showSendCircleDialog();
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()){
                    imageFilterView.setVisibility(View.GONE);
//                    collapsingToolbarLayout.setVisibility(View.GONE);
                    tvUserName.setVisibility(View.GONE);
                }
                else {
                    imageFilterView.setVisibility(View.VISIBLE);
                    tvUserName.setVisibility(View.VISIBLE);
//                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public FindCirclePresenter initPresenter() {
        return new FindCirclePresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,FindCircleActivity.class));
    }

    @Override
    public void showSendCircleDialog() {
        if (sendCircleView == null){
            sendCircleView = getLayoutInflater().inflate(R.layout.dialog_find_circle_send,null,false);
        }
        EditText etMessage = sendCircleView.findViewById(R.id.dfcs_et_message);
        Button btnSend = sendCircleView.findViewById(R.id.dfcs_btn_send);
        ImageView ivClose = sendCircleView.findViewById(R.id.dfcs_iv_close);

        if (sendCircleDialog == null){
            sendCircleDialog = new AlertDialog.Builder(context).setView(sendCircleView).setCancelable(false).create();
        }
        ivClose.setOnClickListener(v->{
            if (!TextUtils.isEmpty(etMessage.getText())){
                showAlert("提示",
                        "你尚有编辑内容未发布，是否确认退出？",
                        "取消", null,
                        "确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                etMessage.setText("");
                                sendCircleDialog.dismiss();
                            }
                        }
                );
                return;
            }
            sendCircleDialog.dismiss();
        });

        btnSend.setOnClickListener(v -> {
            if (TextUtils.isEmpty(etMessage.getText())){
                showTipsOnTop("您的发布内容为空");
                return;
            }
            presenter.addItem(etMessage.getText().toString());
            etMessage.setText("");
            sendCircleDialog.dismiss();
        });
        if (sendCircleDialog.getWindow() != null){
            sendCircleDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            sendCircleDialog.show();
        }
    }

    @Override
    public void notifyCircle(List<FriendCircleBean> list) {
        if (adapter == null){
            adapter = new FindCircleAdapter(list);
            rlvData.setAdapter(adapter);
            rlvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    class FindCircleAdapter extends RecyclerView.Adapter<FindCircleAdapter.ViewHolder>{

        List<FriendCircleBean> list;

        public FindCircleAdapter(List<FriendCircleBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public FindCircleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_find_circle,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull FindCircleAdapter.ViewHolder holder, int position) {
            FriendCircleBean data = list.get(holder.getLayoutPosition());
//            List<Integer> img = new ArrayList<>();
//            TypedArray ar = getResources().obtainTypedArray(R.array.head);
//            for (int i = 0;i < list.size();i++){
//                img.add(ar.getResourceId(i,0));
//            }
//            ar.recycle();

            holder.tvName.setText(data.getUserName());
            holder.tvMessage.setText(data.getMessage());
            holder.tvTime.setText(data.getTime());
            holder.tvDelet.setVisibility(View.GONE);
            if (data.getCommentBeans() == null || data.getCommentBeans().isEmpty()){
                holder.clComment.setVisibility(View.GONE);
            }
            if (data.getCommentBeans() != null){
                commentAdapter = new FindCircleCommentAdapter(data.getCommentBeans());
                holder.rlv.setAdapter(commentAdapter);
                holder.rlv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            }

            if (data.isLike()){
                holder.ivLike.setImageResource(R.mipmap.love_click);
                holder.tvLikeNumber.setVisibility(View.VISIBLE);
                holder.tvLikeNumber.setText(String.valueOf(data.getLikeNumber()+1));
            }
            if (!data.isLike()){
                holder.ivLike.setImageResource(R.mipmap.love_unclick);
                if (data.getLikeNumber() == 0){
                    holder.tvLikeNumber.setVisibility(View.GONE);
                }
                else {
                    holder.tvLikeNumber.setVisibility(View.VISIBLE);
                    holder.tvLikeNumber.setText(String.valueOf(data.getLikeNumber()));
                }
            }
            holder.ivLike.setOnClickListener(v -> {
                data.setLike(!data.isLike());
                notifyCircle(list);
            });
            holder.btnSendComment.setOnClickListener(v -> {
                if (TextUtils.isEmpty(holder.etComment.getText())){
                    showToast("请输入评论内容");
                    return;
                }
                holder.clComment.setVisibility(View.VISIBLE);
                CommentBean bean = new CommentBean();
                bean.setCommentName(UserBean.loginName);
                bean.setComment(holder.etComment.getText().toString());
                List<CommentBean> commentBeans = new ArrayList<>(data.getCommentBeans());
                commentBeans.add(bean);
                data.setCommentBeans(commentBeans);
                commentAdapter = new FindCircleCommentAdapter(commentBeans);
                holder.rlv.setAdapter(commentAdapter);
                holder.rlv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                holder.etComment.setText("");
                hideSoftKeyboard();
            });
            if (data.getUserName().equals(UserBean.loginName)){
                holder.tvDelet.setVisibility(View.VISIBLE);
                holder.tvDelet.setOnClickListener(v->{
                    showAlert("提示",
                            "是否删除本条朋友圈？",
                            "取消", null,
                            "删除", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    presenter.delItem(holder.getLayoutPosition());
                                }
                            }
                    );
                });
//                holder.ivHead.setImageResource(R.drawable.head_01);
                Glide.with(context).load(R.drawable.head_01).centerCrop().into(holder.ivHead);
            }
            else {
//                holder.ivHead.setImageResource(img.get(holder.getLayoutPosition()));
                Glide.with(context).load(data.getHead()).centerCrop().into(holder.ivHead);
            }
        }

        @Override
        public int getItemCount() {
            return list == null ? 0:list.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.ifc_tv_name)
            TextView tvName;
            @BindView(R.id.ifc_tv_message)
            TextView tvMessage;
            @BindView(R.id.ifc_tv_time)
            TextView tvTime;
            @BindView(R.id.ifc_iv_like)
            ImageView ivLike;
            @BindView(R.id.ifc_tv_like_number)
            TextView tvLikeNumber;
            @BindView(R.id.ifc_iv_head)
            ImageView ivHead;
            @BindView(R.id.ifc_tv_delet)
            TextView tvDelet;
            @BindView(R.id.ifc_cl_comment)
            ConstraintLayout clComment;
            @BindView(R.id.ifc_rlv)
            RecyclerView rlv;
            @BindView(R.id.ifc_et_comment)
            EditText etComment;
            @BindView(R.id.ifc_btn_sned_comment)
            Button btnSendComment;
            @BindView(R.id.root)
            ConstraintLayout root;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

    class FindCircleCommentAdapter extends RecyclerView.Adapter<FindCircleCommentAdapter.ViewHolder>{

        List<CommentBean> list;

        public FindCircleCommentAdapter(List<CommentBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public FindCircleCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_circle_comment,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull FindCircleCommentAdapter.ViewHolder holder, int position) {
            CommentBean data = list.get(position);
            holder.tvCommentName.setText(data.getCommentName());
            holder.tvCommentMessage.setText(data.getComment());
        }

        @Override
        public int getItemCount() {
            return list == null ? 0:list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.icc_tv_name)
            TextView tvCommentName;
            @BindView(R.id.icc_tv_message)
            TextView tvCommentMessage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}