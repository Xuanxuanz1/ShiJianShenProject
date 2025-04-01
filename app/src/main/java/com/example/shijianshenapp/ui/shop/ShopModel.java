package com.example.shijianshenapp.ui.shop;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.util.ThreadUtil;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.ShopBean;
import com.example.shijianshenapp.config.Constants;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShopModel extends BaseModel implements IShopModel {

    private List<ShopBean> shopList = new ArrayList<>();
    public ShopModel(Context context) {
        super(context);
    }


    @Override
    public void fetchGoodsData(ModelCallBack modelCallBack) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<ShopBean> bean = Operator.order("typeName asc").find(ShopBean.class);
                    if (bean.isEmpty()){
                        List<ShopBean> newList = new ArrayList<>();
                        ShopBean data1 = new ShopBean();
                        data1.setImg(R.drawable.friend_01);
                        data1.setTypeName(Constants.TYPE_NAME_MAP.get("0"));
                        data1.setGoodsName("5元优质健身课程抵扣卷");
                        data1.setGoodsDrtail("红包仅限在App客户端使用，该优惠券满99元可用。");
                        data1.setGoodsQty(98);
                        data1.setSold(11);
                        data1.setGoodsPrice(298);
                        data1.setSales(false);
                        newList.add(data1);

                        ShopBean data2 = new ShopBean();
                        data2.setImg(R.drawable.goods_01);
                        data2.setTypeName(Constants.TYPE_NAME_MAP.get("0"));
                        data2.setGoodsName("宅神新世纪福音战士EVA头像)");
                        data2.setGoodsDrtail("不会真的有人喜欢这个吧？！美少女不香？");
                        data2.setGoodsQty(6);
                        data2.setSold(3);
                        data2.setGoodsPrice(40);
                        data2.setSales(false);
                        newList.add(data2);

                        ShopBean data3 = new ShopBean();
                        data3.setImg(R.drawable.goods_02);
                        data3.setTypeName(Constants.TYPE_NAME_MAP.get("0"));
                        data3.setGoodsName("深夜模式主题");
                        data3.setGoodsDrtail("昏暗的灯亮着，孤单的我失魂落魄，习惯了一个人，一切像剪辑的电影一瞬间就知道了结果。");
                        data3.setGoodsQty(2);
                        data3.setSold(0);
                        data3.setGoodsPrice(1);
                        data3.setSales(false);
                        newList.add(data3);

                        ShopBean data4 = new ShopBean();
                        data4.setImg(R.drawable.goods_05);
                        data4.setTypeName(Constants.TYPE_NAME_MAP.get("1"));
                        data4.setGoodsName("滴滴出行8元优惠卷");
                        data4.setGoodsDrtail("滴滴出行优惠券，订单满38可用");
                        data4.setGoodsQty(80);
                        data4.setSold(16);
                        data4.setGoodsPrice(60);
                        data4.setSales(false);
                        newList.add(data4);

                        ShopBean data5 = new ShopBean();
                        data5.setImg(R.drawable.goods_06);
                        data5.setTypeName(Constants.TYPE_NAME_MAP.get("1"));
                        data5.setGoodsName("如棋出行3元优惠券");
                        data5.setGoodsDrtail("如棋出行优惠券，订单满19可用");
                        data5.setGoodsQty(100);
                        data5.setSold(40);
                        data5.setSales(false);
                        newList.add(data5);

                        ShopBean data6 = new ShopBean();
                        data6.setImg(R.drawable.friend_01);
                        data6.setTypeName(Constants.TYPE_NAME_MAP.get("2"));
                        data6.setGoodsName("健身精选课程体验卡");
                        data6.setGoodsDrtail("适用用本APP，可体验精选课程");
                        data6.setGoodsQty(30);
                        data6.setSold(10);
                        data6.setGoodsPrice(60);
                        data6.setSales(false);
                        newList.add(data6);

                        ShopBean data7 = new ShopBean();
                        data7.setImg(R.drawable.goods_07);
                        data7.setTypeName(Constants.TYPE_NAME_MAP.get("2"));
                        data7.setGoodsName("7元网易云黑胶会员月卡优惠券卷");
                        data7.setGoodsDrtail("产品说明：网易云音乐黑胶会员月卡仅支持网易云音乐手机端、电脑端、ipad端");
                        data7.setGoodsQty(4112);
                        data7.setSold(1295);
                        data7.setGoodsPrice(59);
                        data7.setSales(false);
                        newList.add(data7);

                        ShopBean data8 = new ShopBean();
                        data8.setImg(R.drawable.goods_03);
                        data8.setTypeName(Constants.TYPE_NAME_MAP.get("3"));
                        data8.setGoodsName("健身鸡腿80g");
                        data8.setGoodsDrtail("玩铁猩猩玩铁猩猩 去皮鸡腿即食 健身即食 健身鸡腿 健身餐美食 方便即食 80g*6袋");
                        data8.setGoodsQty(40);
                        data8.setSold(13);
                        data8.setGoodsPrice(50);
                        data8.setSales(false);
                        newList.add(data8);

                        ShopBean data9 = new ShopBean();
                        data9.setImg(R.drawable.goods_04);
                        data9.setTypeName(Constants.TYPE_NAME_MAP.get("3"));
                        data9.setGoodsName("0零脂肪荞麦面");
                        data9.setGoodsDrtail("0零脂肪荞麦方便面非油炸免煮荞麦挂面泡面饼健身代餐速食60g*10");
                        data9.setGoodsQty(118);
                        data9.setSold(49);
                        data9.setGoodsPrice(20);
                        data9.setSales(false);
                        newList.add(data9);

                        ShopBean data10 = new ShopBean();
                        data10.setImg(R.drawable.goods_08);
                        data10.setTypeName(Constants.TYPE_NAME_MAP.get("4"));
                        data10.setGoodsName("PROIRON彩色浸塑哑铃");
                        data10.setGoodsDrtail("PROIRON彩色浸塑哑铃 男女士健身器材家用哑铃锻炼手臂训练 雅灰色5kg*2");
                        data10.setGoodsQty(88);
                        data10.setSold(19);
                        data10.setGoodsPrice(143);
                        data10.setSales(false);
                        newList.add(data10);

                        ShopBean data11 = new ShopBean();
                        data11.setImg(R.drawable.goods_09);
                        data11.setTypeName(Constants.TYPE_NAME_MAP.get("4"));
                        data11.setGoodsName("负重水袋负重能量包深蹲水袋");
                        data11.setGoodsDrtail("负重水袋负重能量包深蹲水袋男士练臂肌爆发力量训练沙包运动包牛角包 家用健身器材 透明款（1-20kg）");
                        data11.setGoodsQty(200);
                        data11.setSold(154);
                        data11.setGoodsPrice(175);
                        data11.setSales(false);
                        newList.add(data11);

                        ShopBean data12 = new ShopBean();
                        data12.setImg(R.drawable.goods_10);
                        data12.setTypeName(Constants.TYPE_NAME_MAP.get("5"));
                        data12.setGoodsName("兰博基尼5元代金券");
                        data12.setGoodsDrtail("购买兰博基尼跑车支付时出示该代金券即可抵扣5元");
                        data12.setGoodsQty(6);
                        data12.setSold(1);
                        data12.setGoodsPrice(1);
                        data12.setSales(false);
                        newList.add(data12);

                        ShopBean data13 = new ShopBean();
                        data13.setImg(R.drawable.goods_11);
                        data13.setTypeName(Constants.TYPE_NAME_MAP.get("5"));
                        data13.setGoodsName("麦当劳优惠券");
                        data13.setGoodsDrtail("秘制鸡腿麦趣饭+巨无霸+麦乐鸡+黑芝麻珍珠奶茶+可口可乐 优惠价53元");
                        data13.setGoodsQty(70);
                        data13.setSold(22);
                        data13.setGoodsPrice(53);
                        data13.setSales(false);
                        newList.add(data13);

                        shopList.addAll(newList);
                        Operator.saveAll(shopList);
                        baseHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                modelCallBack.requestSuccess();
                            }
                        });
                        return;
                    }
//                    Collections.sort(bean, new Comparator<ShopBean>() {
//                        @Override
//                        public int compare(ShopBean o1, ShopBean o2) {
//                            return o1.getTypeName().compareTo(o2.getTypeName());
//                        }
//                    });
                    shopList.addAll(bean);
                    baseHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            modelCallBack.requestSuccess();
                        }
                    });
                }catch (Exception e){
                    baseHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            modelCallBack.requestError(generateThrowable(e));
                        }
                    });
                    e.printStackTrace();
                }
            }
        });

//    Operator.deleteAll(ShopBean.class);
//    modelCallBack.requestSuccess();
    }

    @Override
    public void buyGoods(int position, ModelCallBack modelCallBack) {
        try {
            ShopBean data = shopList.get(position);
            data.setSold(data.getSold() + 1);
            data.setSales(true);
            Operator.saveAll(shopList);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public List<ShopBean> getGoodsList() {
        return shopList;
    }
}
