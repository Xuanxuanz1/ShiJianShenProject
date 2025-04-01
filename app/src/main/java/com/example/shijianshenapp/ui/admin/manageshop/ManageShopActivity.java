package com.example.shijianshenapp.ui.admin.manageshop;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.ShopBean;
import com.example.shijianshenapp.config.Constants;

import java.util.ArrayList;
import java.util.List;

public class ManageShopActivity extends BaseActivity<ManageShopContact.IManageShopView,ManageShopPresenter> implements ManageShopContact.IManageShopView {

    @BindView(R.id.ams_rlv)
    RecyclerView rlv;

    private ManageShopAdapter manageShopAdapter;
    private AlertDialog editGoodsDialog;
    private AlertDialog addGoodsDialog;
    private View editGoodsView;
    private View addGoodsView;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.manage_shop);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manage_shop;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showRightBtn("添加商品", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddGoodsDialog();
            }
        });
    }

    @Override
    public ManageShopPresenter initPresenter() {
        return new ManageShopPresenter(context);
    }

    public static void show(Context context){
        context.startActivity(new Intent(context,ManageShopActivity.class));
    }

    @Override
    public void notifyShopList(List<ShopBean> list) {
        if (manageShopAdapter == null){
            manageShopAdapter = new ManageShopAdapter(list);
            rlv.setAdapter(manageShopAdapter);
            rlv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        }
        else {
            manageShopAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showGoodsEditDialog(int position,ShopBean data) {
        if (editGoodsView == null){
            editGoodsView = getLayoutInflater().inflate(R.layout.dialog_manage_shop_edit_goods,null,false);
        }
        if (editGoodsDialog == null){
            editGoodsDialog = new AlertDialog.Builder(context).setView(editGoodsView).setCancelable(false).create();
        }

        ImageView ivClose = editGoodsView.findViewById(R.id.dmseg_iv_close);
        EditText etGoodsName = editGoodsView.findViewById(R.id.dmseg_et_goods_name);
        EditText etQty = editGoodsView.findViewById(R.id.dmseg_et_goods_qty);
        EditText etSold = editGoodsView.findViewById(R.id.dmseg_et_sold);
        EditText etPrice = editGoodsView.findViewById(R.id.dmseg_et_goods_price);
        Button btnSave = editGoodsView.findViewById(R.id.dmseg_btn_save);
        TextView spTypeName = editGoodsView.findViewById(R.id.dmseg_sp_type_name);
        Button btnDel = editGoodsView.findViewById(R.id.dmseg_btn_del);

        spTypeName.setText(data.getTypeName());
        spTypeName.setEnabled(false);
        etGoodsName.setText(data.getGoodsName());
        etQty.setText(String.valueOf(data.getGoodsQty()));
        etSold.setText(String.valueOf(data.getSold()));
        etPrice.setText(String.valueOf(data.getGoodsPrice()));

        ivClose.setOnClickListener(v -> {
            editGoodsDialog.dismiss();
        });

        btnSave.setOnClickListener(v -> {
            if (checkGoodsMessage(etGoodsName,etQty,etSold,etPrice,false)){
                presenter.editGoods(
                        position,
                        etGoodsName.getText().toString().trim(),
                        etQty.getText().toString().trim(),
                        etPrice.getText().toString().trim(),
                        etSold.getText().toString().trim()

                );
            }
        });

        btnDel.setOnClickListener(v -> {
            presenter.delGoods(position);
            editGoodsDialog.dismiss();
        });

        if (editGoodsDialog.getWindow() != null){
            editGoodsDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            editGoodsDialog.show();
        }
    }

    @Override
    public void showAddGoodsDialog() {
        if (addGoodsView == null){
            addGoodsView = getLayoutInflater().inflate(R.layout.dialog_manage_shop_add_goods,null,false);
        }
        if (addGoodsDialog == null){
            addGoodsDialog = new AlertDialog.Builder(context).setView(addGoodsView).setCancelable(false).create();
        }

        ImageView ivClose = addGoodsView.findViewById(R.id.dmsag_iv_close);
        EditText etGoodsName = addGoodsView.findViewById(R.id.dmsag_et_goods_name);
        EditText etQty = addGoodsView.findViewById(R.id.dmsag_et_goods_qty);
        EditText etPrice = addGoodsView.findViewById(R.id.dmsag_et_goods_price);
        Button btnSave = addGoodsView.findViewById(R.id.dmsag_btn_save);
        Spinner spTypeName = addGoodsView.findViewById(R.id.dmsag_sp_type_name);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Constants.TYPE_NAME);
        spTypeName.setAdapter(arrayAdapter);

        ivClose.setOnClickListener(v -> {
            etGoodsName.setText("");
            etQty.setText("");
            etPrice.setText("");
            addGoodsDialog.dismiss();
        });

        btnSave.setOnClickListener(v->{
            if (checkGoodsMessage(etGoodsName,etQty,null,etPrice,true)){
                presenter.addGoods(
                        etGoodsName.getText().toString().trim(),
                        etQty.getText().toString().trim(),
                        etPrice.getText().toString().trim(),
                        "0",
                        spTypeName.getSelectedItem().toString()
                        );
                etGoodsName.setText("");
                etQty.setText("");
                etPrice.setText("");
                addGoodsDialog.dismiss();
            }
        });

        if (addGoodsDialog.getWindow() != null){
            addGoodsDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            addGoodsDialog.show();
        }
    }

    @Override
    public boolean checkGoodsMessage(EditText etGoodsName, EditText etQty, EditText etSold, EditText etPrice,boolean isAdd) {
        if (TextUtils.isEmpty(etGoodsName.getText())){
            showToast("商品名称为空");
            return false;
        }
        if (TextUtils.isEmpty(etQty.getText())){
            showToast("商品数量为空");
            return false;
        }
        if (!isAdd && TextUtils.isEmpty(etSold.getText())){
            showToast("已售商品为空");
            return false;
        }
        if (TextUtils.isEmpty(etPrice.getText())){
            showToast("所需兑换积分为空");
            return false;
        }
        return true;
    }

    class ManageShopAdapter extends RecyclerView.Adapter<ManageShopAdapter.ViewHolder>{

        List<ShopBean> list;

        public ManageShopAdapter(List<ShopBean> list){
            this.list = list;
        }

        @NonNull
        @Override
        public ManageShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_manage_shop_list,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ManageShopAdapter.ViewHolder holder, int position) {
            ShopBean data = list.get(position);
            holder.tvGoodsName.setText(data.getGoodsName());
            holder.tvQty.setText(String.valueOf(data.getGoodsQty() - data.getSold()));
            holder.tvGoodsPrice.setText(String.valueOf(data.getGoodsPrice()));

            holder.tvEdit.setOnClickListener(v -> {
                showGoodsEditDialog(position,data);
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.imsl_tv_goods_name)
            TextView tvGoodsName;
            @BindView(R.id.imsl_tv_qty)
            TextView tvQty;
            @BindView(R.id.imsl_tv_goods_price)
            TextView tvGoodsPrice;
            @BindView(R.id.imsl_tv_edit)
            TextView tvEdit;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}