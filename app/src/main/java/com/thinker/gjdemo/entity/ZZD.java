package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ZZD {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //种植地名称
    @ColumnInfo(name = "zzdName")
    public String zzdName;
    //种植地位置
    @ColumnInfo(name = "zzdLocation")
    public String zzdLocation;
    //种植地面积
    @ColumnInfo(name = "zzdArea")
    public String zzdArea;
    //承包人
    @ColumnInfo(name = "zzdCBR")
    public String zzdCBR;
    //柑橘品种
    @ColumnInfo(name = "zzdGJ")
    public String zzdGJ;
    //种植数量
    @ColumnInfo(name = "zzdSL")
    public String zzdSL;

    public int getId() {
        return id;
    }

    public String getZzdName() {
        return zzdName;
    }

    public String getZzdLocation() {
        return zzdLocation;
    }

    public String getZzdArea() {
        return zzdArea;
    }

    public String getZzdCBR() {
        return zzdCBR;
    }

    public String getZzdGJ() {
        return zzdGJ;
    }

    public String getZzdSL() {
        return zzdSL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setZzdName(String zzdName) {
        this.zzdName = zzdName;
    }

    public void setZzdLocation(String zzdLocation) {
        this.zzdLocation = zzdLocation;
    }

    public void setZzdArea(String zzdArea) {
        this.zzdArea = zzdArea;
    }

    public void setZzdCBR(String zzdCBR) {
        this.zzdCBR = zzdCBR;
    }

    public void setZzdGJ(String zzdGJ) {
        this.zzdGJ = zzdGJ;
    }

    public void setZzdSL(String zzdSL) {
        this.zzdSL = zzdSL;
    }

    public ZZD(String zzdName, String zzdLocation, String zzdArea, String zzdCBR, String zzdGJ, String zzdSL) {
        this.zzdName = zzdName;
        this.zzdLocation = zzdLocation;
        this.zzdArea = zzdArea;
        this.zzdCBR = zzdCBR;
        this.zzdGJ = zzdGJ;
        this.zzdSL = zzdSL;
    }
}
