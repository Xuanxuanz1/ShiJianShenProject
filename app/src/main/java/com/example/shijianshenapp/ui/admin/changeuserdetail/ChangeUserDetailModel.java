package com.example.shijianshenapp.ui.admin.changeuserdetail;

import android.content.Context;

import com.ayma.base.mvp.ModelParamCallBack;
import com.ayma.base.util.ThreadUtil;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.UserBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class ChangeUserDetailModel extends BaseModel implements IChangeUserDetailModel {
    public ChangeUserDetailModel(Context context) {
        super(context);
    }

    @Override
    public void fetchUser(ModelParamCallBack<List<UserBean>> modelParamCallBack) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    List<UserBean> list = Operator.findAll(UserBean.class);
                    List<UserBean> temp = new ArrayList<>();
                    for (UserBean bean : list){
                        if (bean.getUserName().equals("admin")){
                            temp.add(bean);
                        }
                    }
                    list.removeAll(temp);
                    list.add(0,temp.get(0));
                    baseHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            modelParamCallBack.requestSuccess(list);
                        }
                    });
                }catch (Exception e){
                    baseHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            modelParamCallBack.requestError(generateThrowable(e));
                        }
                    });
                    e.printStackTrace();
                }
            }
        });
    }
}
