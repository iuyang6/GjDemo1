package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NYZF {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //执法名称
    @ColumnInfo(name = "zName")
    public String zName;

    @ColumnInfo(name = "zUser")
    public String zUser;
    //执法地点
    @ColumnInfo(name = "zLocation")
    public String zLocation;

    //执法描述
    @ColumnInfo(name = "zDetail")
    public String zDetail;
    //执法时间
    @ColumnInfo(name = "zTime")
    public String zTime;

    public NYZF(String zName, String zUser, String zLocation, String zDetail, String zTime) {
        this.zName = zName;
        this.zUser = zUser;
        this.zLocation = zLocation;
        this.zDetail = zDetail;
        this.zTime = zTime;
    }

    public String getzUser() {
        return zUser;
    }

    public void setzUser(String zUser) {
        this.zUser = zUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setzName(String zName) {
        this.zName = zName;
    }

    public void setzLocation(String zLocation) {
        this.zLocation = zLocation;
    }

    public void setzDetail(String zDetail) {
        this.zDetail = zDetail;
    }

    public void setzTime(String zTime) {
        this.zTime = zTime;
    }

    public int getId() {
        return id;
    }

    public String getzName() {
        return zName;
    }

    public String getzLocation() {
        return zLocation;
    }

    public String getzDetail() {
        return zDetail;
    }

    public String getzTime() {
        return zTime;
    }
}
