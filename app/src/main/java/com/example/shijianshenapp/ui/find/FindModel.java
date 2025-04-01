package com.example.shijianshenapp.ui.find;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BaseModel;
import com.example.shijianshenapp.bean.TrainBean;

import org.litepal.Operator;

import java.util.ArrayList;
import java.util.List;

public class FindModel extends BaseModel implements IFindModel {

    private List<TrainBean> list = new ArrayList<>();

    public FindModel(Context context) {
        super(context);
    }

    @Override
    public void fetchVideoData(ModelCallBack modelCallBack) {
        try{
            List<TrainBean> bean = Operator.findAll(TrainBean.class);
            if (bean.isEmpty()){
                modelCallBack.requestError(generateThrowable("数据为空"));
                return;
            }
            list.addAll(bean);
            modelCallBack.requestSuccess();
        }catch (Exception e){
            modelCallBack.requestError(generateThrowable(e));
            e.printStackTrace();
        }
    }

    @Override
    public List<TrainBean> getVideoList() {
        return list;
    }
}
