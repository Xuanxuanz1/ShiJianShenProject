package com.ayma.base.data;

public class CrashInfoBean {
    private String description;
    private String phoneModel;
    private String appVersion;
    private String occurTime;
    private String androidVersion;

    public CrashInfoBean(String description, String phoneModel, String appVersion, String occurTime, String androidVersion) {
        this.description = description;
        this.phoneModel = phoneModel;
        this.appVersion = appVersion;
        this.occurTime = occurTime;
        this.androidVersion = androidVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }
}
