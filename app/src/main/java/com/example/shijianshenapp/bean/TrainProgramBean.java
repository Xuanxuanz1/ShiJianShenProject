package com.example.shijianshenapp.bean;

import java.io.Serializable;

public class TrainProgramBean implements Serializable {
    private int trainImg;
    private String trainName;
    private String trainTime;
    private boolean isTrain;
    public static boolean isTrainOff = false;

    public boolean isTrain() {
        return isTrain;
    }

    public void setTrain(boolean train) {
        isTrain = train;
    }

    public int getTrainImg() {
        return trainImg;
    }

    public void setTrainImg(int trainImg) {
        this.trainImg = trainImg;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    public static boolean isIsTrainOff() {
        return isTrainOff;
    }

    public static void setIsTrainOff(boolean isTrainOff) {
        TrainProgramBean.isTrainOff = isTrainOff;
    }
}
