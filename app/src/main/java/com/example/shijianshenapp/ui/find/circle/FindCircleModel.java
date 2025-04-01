package com.example.shijianshenapp.ui.find.circle;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.ayma.base.util.DateTimeUtils;
import com.ayma.base.util.ThreadUtil;
import com.example.shijianshenapp.R;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.CommentBean;
import com.example.shijianshenapp.bean.FriendCircleBean;
import com.example.shijianshenapp.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class FindCircleModel extends BaseModel implements IFindCircleModel {

    private List<FriendCircleBean> friendCircleBeanList = new ArrayList<>();

    public FindCircleModel(Context context) {
        super(context);
    }

    @Override
    public void fetchCircleData(ModelCallBack modelCallBack) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                List<CommentBean> commentBeanList = new ArrayList<>();
                FriendCircleBean data = new FriendCircleBean();
                data.setHead(R.drawable.head_05);
                data.setUserName("林萌");
                data.setMessage("不怕万人阻挡，就怕自己投降，将来的你一定会感激现在拼命的自己，自己选择的路，再艰难，跪着也要走完。");
                data.setTime("2022-12-29- 14:28");
                data.setLike(false);
                data.setLikeNumber(998);
                CommentBean bean = new CommentBean();
                bean.setCommentName("Doki");
                bean.setComment("一起加油！");
                commentBeanList.add(bean);
                CommentBean bean1 = new CommentBean();
                bean1.setCommentName("凉兮");
                bean1.setComment("只要不放弃，你永远都是强者！");
                commentBeanList.add(bean1);
                data.setCommentBeans(commentBeanList);
                friendCircleBeanList.add(data);

                List<CommentBean> commentBeanList1 = new ArrayList<>();
                FriendCircleBean data1 = new FriendCircleBean();
                data1.setHead(R.drawable.head_03);
                data1.setUserName("xuanxuan");
                data1.setMessage("世界上最难的两件事：一是把自己的思想装进别人的脑袋，二是把别人的钱装进自己的口袋。");
                data1.setTime("2022-12-27 14:37");
                data1.setLike(false);
                data1.setLikeNumber(777);
                CommentBean bean2 = new CommentBean();
                bean2.setCommentName("欢欢");
                bean2.setComment("爱情使人忘记时间，时间也使人忘记爱情。");
                commentBeanList1.add(bean2);
                CommentBean bean3 = new CommentBean();
                bean3.setCommentName("渡边博子");
                bean3.setComment("如果幸福是浮云，如果痛苦似星辰。那我的生活真是万里无云，漫天繁星。");
                commentBeanList1.add(bean3);
                data1.setCommentBeans(commentBeanList1);
                friendCircleBeanList.add(data1);

                List<CommentBean> commentBeanList2 = new ArrayList<>();
                FriendCircleBean data2 = new FriendCircleBean();
                data2.setHead(R.drawable.head_04);
                data2.setUserName("落心");
                data2.setMessage("健身，成功不成功其实很简单，就看你有没有决心！");
                data2.setTime("2022-12-20 14:37");
                data2.setLike(true);
                data2.setLikeNumber(99);
                CommentBean bean4 = new CommentBean();
                bean4.setCommentName("hgxjncgf");
                bean4.setComment("健康是智慧的条件，是愉悦的秘");
                commentBeanList2.add(bean4);
                data2.setCommentBeans(commentBeanList2);
                friendCircleBeanList.add(data2);
                baseHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        modelCallBack.requestSuccess();
                    }
                });
            }
        });

    }

    @Override
    public void delItem(int position,ModelCallBack modelCallBack) {
        friendCircleBeanList.remove(position);
        modelCallBack.requestSuccess();
    }

    @Override
    public void addItem(String message, ModelCallBack modelCallBack) {
        FriendCircleBean data = new FriendCircleBean();
        data.setUserName(UserBean.loginName);
        data.setMessage(message);
        data.setTime(DateTimeUtils.getDateToyyyyMMddHHmm());
        data.setLike(false);
        data.setLikeNumber(0);
        List<CommentBean> beans = new ArrayList<>();
        data.setCommentBeans(beans);
        friendCircleBeanList.add(0,data);
        modelCallBack.requestSuccess();
    }

    @Override
    public List<FriendCircleBean> getCircleDate() {
        return friendCircleBeanList;
    }
}
