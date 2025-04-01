package com.example.shijianshenapp.ui.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayma.base.util.CommonUtil;
import com.ayma.base.widget.SpacesItemDecoration;
import com.bumptech.glide.Glide;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseActivity;
import com.example.shijianshenapp.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends BaseActivity<ShopContact.IShopView,ShopPresenter> implements ShopContact.IShopView {

    @BindView(R.id.as_rlv_type)
    RecyclerView rlvType;
    @BindView(R.id.as_rlv_goods)
    RecyclerView rlvGoods;

    private ShopTypeListAdapter typeListAdapter;
    private ShopGoodsListAdapter goodsListAdapter;
    private View goodsView;
    private AlertDialog goodsDialog;
    private int integration;

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.shop);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String number = intent.getStringExtra("integration");
        integration = Integer.parseInt(number);
    }

    @Override
    public ShopPresenter initPresenter() {
        return new ShopPresenter(context);
    }

    public static void show(Context context,String integration){
        Intent intent = new Intent(context,ShopActivity.class);
        intent.putExtra("integration",integration);
        context.startActivity(intent);
    }

    @Override
    public void notifyGoodsList(List<ShopBean> list) {
        if (goodsListAdapter == null){
            goodsListAdapter = new ShopGoodsListAdapter(list);
            rlvGoods.setAdapter(goodsListAdapter);
            rlvGoods.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
            rlvGoods.addItemDecoration(new SpacesItemDecoration(CommonUtil.dip2px(context,5)));
            rlvGoods.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (layoutManager == null){
                        return;
                    }
                    typeListAdapter.setSelectItem(layoutManager.findFirstVisibleItemPosition());
                }
            });
            rlvGoods.setItemAnimator(new DefaultItemAnimator());
        }
        else {
            goodsListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void notifyTypeList(List<ShopBean> list) {
        List<ShopBean> bean = new ArrayList<>();
        String smallName = "";
        for (ShopBean data : list){
            if (!smallName.equals(data.getTypeName())){
                smallName = data.getTypeName();
                bean.add(data);
            }
        }
        typeListAdapter = new ShopTypeListAdapter(bean);
        rlvType.setAdapter(typeListAdapter);
        rlvType.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
    }

    @Override
    public void showGoodsDialog(ShopBean data) {
        if (goodsView == null){
            goodsView = getLayoutInflater().inflate(R.layout.dialog_shop_goods_detail,null,false);
        }
        if (goodsDialog == null){
            goodsDialog = new AlertDialog.Builder(context).setView(goodsView).setCancelable(false).create();
        }

        ImageView ivClose = goodsView.findViewById(R.id.dmseg_iv_close);
        ImageView ivHead = goodsView.findViewById(R.id.dsgd_iv_head);
        TextView tvTitle = goodsView.findViewById(R.id.dsgd_tv_title);
        TextView tvQty = goodsView.findViewById(R.id.dsgd_tv_qty);
        TextView tvTypeName = goodsView.findViewById(R.id.dsgd_tv_type_name);
        TextView tvMessage = goodsView.findViewById(R.id.dsgd_tv_message);

        ivClose.setOnClickListener(v -> {
            goodsDialog.dismiss();
        });

//        Glide.with(context).load(data.getImg()).centerCrop().into(ivHead);
        ivHead.setImageResource(data.getImg());
        tvTitle.setText(data.getGoodsName());
        tvTypeName.setText(data.getTypeName());
        tvQty.setText(String.valueOf(data.getGoodsQty()-data.getSold()));
        tvMessage.setText(data.getGoodsDrtail());

        if (goodsDialog.getWindow() != null){
            goodsDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        if (!isFinishing()){
            goodsDialog.show();
            goodsDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }


    class ShopTypeListAdapter extends RecyclerView.Adapter<ShopTypeListAdapter.ViewHolder>{

        List<ShopBean> list;
        int selectItem = 0;

        public ShopTypeListAdapter(List<ShopBean> list){
            this.list = list;
        }

        public int getSelectItem(){
            return selectItem;
        }

        public void setSelectItem(int selectItem){
            if (selectItem == 0){
                this.selectItem = selectItem;
                notifyDataSetChanged();
            }
        }

        public void setItem(String typeName){
            if (TextUtils.isEmpty(typeName)){
                return;
            }
            for (int i = 0;i < list.size();i++){
                ShopBean data = list.get(i);
                if (TextUtils.isEmpty(data.getTypeName())){
                    continue;
                }
                if (typeName.equals(data.getTypeName())){
                    if (selectItem != i){
                        selectItem = i;
                        notifyDataSetChanged();
                    }
                }
            }
        }

        @NonNull
        @Override
        public ShopTypeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_shop_type_list,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ShopTypeListAdapter.ViewHolder holder, int position) {
            ShopBean data = list.get(position);

            holder.tvTypeName.setText(data.getTypeName());
            holder.tvTypeName.setBackgroundColor(position == selectItem ? getResources().getColor(R.color.tab_selected):getResources().getColor(R.color.tab_unselected));
            holder.root.setOnClickListener(v->{
                selectItem = holder.getLayoutPosition();
                if (goodsListAdapter != null){
                    int typeFirstPosition = goodsListAdapter.getTypeFirstPosition(data.getTypeName());
                    if (typeFirstPosition == -1){
                        return;
                    }
                    rlvGoods.scrollToPosition(typeFirstPosition);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) rlvGoods.getLayoutManager();
                    if (layoutManager != null){
                        layoutManager.scrollToPositionWithOffset(typeFirstPosition,0);
                    }
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.istl_tv_type_name)
            TextView tvTypeName;
            @BindView(R.id.root)
            ConstraintLayout root;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

    class ShopGoodsListAdapter extends RecyclerView.Adapter<ShopGoodsListAdapter.ViewHolder>{

        List<ShopBean> list;
        int lastSearchPosition = -1;

        public ShopGoodsListAdapter(List<ShopBean> list){
            this.list = list;
        }

        public int getTypeFirstPosition(String typeName){
            for (int i = 0;i < list.size();i++){
                ShopBean data = list.get(i);
                if (typeName.equals(data.getTypeName())){
                    return i;
                }
            }
            return -1;
        }

        @NonNull
        @Override
        public ShopGoodsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.item_shop_goods_list,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ShopGoodsListAdapter.ViewHolder holder, int position) {
            ShopBean data = list.get(position);

            Glide.with(context).load(data.getImg()).centerCrop().into(holder.ivImg);
//            holder.ivImg.setImageResource(data.getImg());
            holder.tvGoodsName.setText(data.getGoodsName());
            holder.tvQty.setText(String.valueOf(data.getGoodsQty() - data.getSold()));

            holder.btnBuy.setText(String.format("%s积分",data.getGoodsPrice()));
            holder.btnBuy.setOnClickListener(v->{
                if (data.getGoodsQty() - data.getSold() == 0){
                    showToast("商品库存为0");
                    return;
                }
                if (integration < data.getGoodsPrice()){
                    showToast("您当前的积分不足");
                    return;
                }
                if (!data.isSales()){
                    showAlert(
                            "提示",
                            String.format("你确定消耗%s积分兑换商品：%s吗？", data.getGoodsPrice(), data.getGoodsName()),
                            "取消", null,
                            "确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    presenter.buyGoods(holder.getLayoutPosition());
                                    holder.btnBuy.setEnabled(false);
                                }
                            }
                    );
                    return;
                }
                showToast("该商品只能一天兑换一次哦~");
            });

            if (position > 0){
                if (data.getTypeName() == null || data.getTypeName().equals(list.get(position - 1).getTypeName())){
                    holder.tvTitle.setVisibility(View.GONE);
                }
                else {
                    holder.tvTitle.setVisibility(View.VISIBLE);
                    holder.tvTitle.setText(data.getTypeName());
                }
            }
            else {
                holder.tvTitle.setVisibility(View.VISIBLE);
                holder.tvTitle.setText(data.getTypeName());
            }

            holder.root.setOnClickListener(v->{
//                showToast(data.getGoodsDrtail());
                showGoodsDialog(data);
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.isgl_tv_title)
            TextView tvTitle;
            @BindView(R.id.isgl_iv_img)
            ImageView ivImg;
            @BindView(R.id.isgl_tv_goods_name)
            TextView tvGoodsName;
            @BindView(R.id.isgl_tv_qty)
            TextView tvQty;
            @BindView(R.id.isgl_btn_buy)
            Button btnBuy;
            @BindView(R.id.root)
            ConstraintLayout root;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}