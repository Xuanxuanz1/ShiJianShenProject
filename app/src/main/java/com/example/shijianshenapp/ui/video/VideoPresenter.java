package com.example.shijianshenapp.ui.video;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class VideoPresenter extends BasePresenter<VideoContact.IVideoView,VideoModel> implements VideoContact.IVideoPresenter {
    public VideoPresenter(Context context) {
        super(context);
    }

    @Override
    protected VideoModel initModel() {
        return new VideoModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void fetchVideoData(int position) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载视频数据");
        model.fetchVideoData(new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.init(model.getTrainList(position));
                view.notifyTrainSportList(model.getTrainPro(position));
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }

    @Override
    public void trainSuccess(int position,int clickPosition) {
        if (!isViewAttach()){
            return;
        }
        model.trainSuccess(position, clickPosition, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.notifyTrainSportList(model.getTrainPro(position));
            }

            @Override
            public void requestError(Throwable t) {
                if (!isViewAttach()){
                    return;
                }
                view.showSingleAlertNoAction(t.getMessage());
            }

            @Override
            public void requestFailure(Throwable t) {
                requestError(t);
            }
        });
    }
}
