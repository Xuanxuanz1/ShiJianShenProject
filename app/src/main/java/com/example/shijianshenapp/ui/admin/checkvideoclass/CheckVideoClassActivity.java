package com.example.shijianshenapp.ui.admin.checkvideoclass;

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
import com.example.shijianshenapp.bean.TrainBean;

import java.util.List;

public class CheckVideoClassActivity extends BaseActivity<CheckVideoClassContact.ICheckVideoClassView,CheckVideoClassPresenter> implements CheckVideoClassContact.ICheckVideoClassView {

    @BindView(R.id.acvc_rlv)
    RecyclerView rlv;

    private CheckVideoAdapter adapter;
    private View addVideoView;
    private View editVideoView;
    private AlertDialog addVideoDialog;
    private AlertDialog editVideoDialog;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.change_video_class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_video_class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showRightBtn("新增视频", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNewVideoDialog();
            }
        });
    }

    @Override
    public CheckVideoClassPresenter initPresenter() {
        return new CheckVideoClassPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,CheckVideoClassActivity.class));
    }

    @Override
    public void showAddNewVideoDialog() {
        if (addVideoView == null){
            addVideoView = getLayoutInflater().inflate(R.layout.dialog_add_edit_check_video,null,false);
        }

        ImageView ivClose = addVideoView.findViewById(R.id.decv_iv_close);
        EditText etTitle = addVideoView.findViewById(R.id.decv_et_video_title);
        EditText etMessage = addVideoView.findViewById(R.id.decv_et_message);
        EditText etTime = addVideoView.findViewById(R.id.decv_et_time);
        EditText etLikeNumber = addVideoView.findViewById(R.id.decv_et_like_number);
        EditText etWatchNumber = addVideoView.findViewById(R.id.decv_et_watch_number);
        Button btnSubmit = addVideoView.findViewById(R.id.decv_btn_save);

        if (addVideoDialog == null){
            addVideoDialog = new AlertDialog.Builder(context).setView(addVideoView).setCancelable(false).create();
        }

        ivClose.setOnClickListener(v -> {
            etTitle.setText("");
            etMessage.setText("");
            etTime.setText("");
            etLikeNumber.setText("");
            etWatchNumber.setText("");
            addVideoDialog.dismiss();
        });

        btnSubmit.setOnClickListener(v -> {
            presenter.addNewVideo(
                    etTitle.getText().toString(),
                    etMessage.getText().toString(),
                    etTime.getText().toString().trim(),
                    etLikeNumber.getText().toString().trim(),
                    etWatchNumber.getText().toString().trim()
            );
            addVideoDialog.dismiss();
        });

        if (addVideoDialog.getWindow() != null){
            addVideoDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            addVideoDialog.show();
        }
    }

    @Override
    public void showEditVideoDialog(int position,TrainBean data) {
        if (editVideoView == null){
            editVideoView = getLayoutInflater().inflate(R.layout.dialog_edit_check_video,null,false);
        }

        ImageView ivClose = editVideoView.findViewById(R.id.decv_iv_close);
        EditText etTitle = editVideoView.findViewById(R.id.decv_et_video_title);
        EditText etMessage = editVideoView.findViewById(R.id.decv_et_message);
        EditText etTime = editVideoView.findViewById(R.id.decv_et_time);
        EditText etLikeNumber = editVideoView.findViewById(R.id.decv_et_like_number);
        EditText etWatchNumber = editVideoView.findViewById(R.id.decv_et_watch_number);
        Button btnSubmint = editVideoView.findViewById(R.id.decv_btn_save);

        if (editVideoDialog == null){
            editVideoDialog = new AlertDialog.Builder(context).setView(editVideoView).setCancelable(false).create();
        }

        etTitle.setText(data.getTitle());
        etMessage.setText(data.getMessage());
        etTime.setText(data.getTime());
        etLikeNumber.setText(String.valueOf(data.getLikeNumber()));
        etWatchNumber.setText(data.getWatchNumber());
        ivClose.setOnClickListener(v->{
            editVideoDialog.dismiss();
        });

        btnSubmint.setOnClickListener(v->{
            presenter.editVideo(
                    position,
                    etTitle.getText().toString(),
                    etMessage.getText().toString(),
                    etTime.getText().toString().trim(),
                    etLikeNumber.getText().toString().trim(),
                    etWatchNumber.getText().toString().trim()
            );
            editVideoDialog.dismiss();
        });

        if (editVideoDialog.getWindow() != null){
            editVideoDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            editVideoDialog.show();
        }
    }

    @Override
    public void notifyCheckVideoList(List<TrainBean> list) {
        if (adapter == null){
            adapter = new CheckVideoAdapter(list);
            rlv.setAdapter(adapter);
            rlv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    class CheckVideoAdapter extends RecyclerView.Adapter<CheckVideoAdapter.ViewHolder>{

        List<TrainBean> list;

        public CheckVideoAdapter(List<TrainBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public CheckVideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_check_video_list,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull CheckVideoAdapter.ViewHolder holder, int position) {
            TrainBean data = list.get(position);
            if (position % 2 == 0){
                holder.root.setBackgroundColor(getResources().getColor(R.color.main_bg));
            }
            else {
                holder.root.setBackgroundColor(getResources().getColor(R.color.white));
            }

            holder.tvTitle.setText(data.getTitle());

            holder.tvEdit.setOnClickListener(v->{
                showEditVideoDialog(position,data);
            });

            holder.tvDelet.setOnClickListener(v->{
                presenter.deletVideo(position);
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.icvl_tv_title)
            TextView tvTitle;
            @BindView(R.id.icvl_tv_edit)
            TextView tvEdit;
            @BindView(R.id.icvl_tv_delet)
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