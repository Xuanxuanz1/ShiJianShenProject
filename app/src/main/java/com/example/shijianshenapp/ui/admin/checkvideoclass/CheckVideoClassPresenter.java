package com.example.shijianshenapp.ui.admin.checkvideoclass;

import android.content.Context;

import com.ayma.base.mvp.ModelCallBack;
import com.example.shijianshenapp.base.BasePresenter;

public class CheckVideoClassPresenter extends BasePresenter<CheckVideoClassContact.ICheckVideoClassView,CheckVideoClassModel> implements CheckVideoClassContact.IChekcVideoClassPresenter {
    public CheckVideoClassPresenter(Context context) {
        super(context);
    }

    @Override
    protected CheckVideoClassModel initModel() {
        return new CheckVideoClassModel(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fetchVideoList();
    }

    @Override
    public void fetchVideoList() {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在加载数据");
        model.fetchVideoData(new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyCheckVideoList(model.getVideoList());
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
    public void editVideo(int position, String title, String message, String time, String likeNumber, String watchNumber) {
        if (!isViewAttach()){
            return;
        }
        model.editVideo(position, title, message, time, likeNumber, watchNumber, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.notifyCheckVideoList(model.getVideoList());
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
    public void addNewVideo(String title, String message, String time, String likeNumber, String watchNumber) {
        if (!isViewAttach()){
            return;
        }
        model.addVideo(title, message, time, likeNumber, watchNumber, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.notifyCheckVideoList(model.getVideoList());
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

    @Override
    public void deletVideo(int position) {
        if (!isViewAttach()){
            return;
        }
        view.showLoading("正在删除视频数据");
        model.deletVideo(position, new ModelCallBack() {
            @Override
            public void requestSuccess() {
                if (!isViewAttach()){
                    return;
                }
                view.hideLoading();
                view.notifyCheckVideoList(model.getVideoList());
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
}
