package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CKJL {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //选择的仓库名称
    @ColumnInfo(name = "ckName")
    public String ckName;
    //选择的出库材料
    @ColumnInfo(name = "clName")
    public String clName;
    //出库时间
    @ColumnInfo(name = "ckTime")
    public String ckTime;
    //出库数量
    @ColumnInfo(name = "ckSL")
    public String ckSL;
    //领用人
    @ColumnInfo(name = "LYR")
    public String LYR;

    public int getId() {
        return id;
    }

    public String getCkName() {
        return ckName;
    }

    public String getClName() {
        return clName;
    }

    public String getCkTime() {
        return ckTime;
    }

    public String getCkSL() {
        return ckSL;
    }

    public String getLYR() {
        return LYR;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCkName(String ckName) {
        this.ckName = ckName;
    }

    public void setClName(String clName) {
        this.clName = clName;
    }

    public void setCkTime(String ckTime) {
        this.ckTime = ckTime;
    }

    public void setCkSL(String ckSL) {
        this.ckSL = ckSL;
    }

    public void setLYR(String LYR) {
        this.LYR = LYR;
    }

    public CKJL(String ckName, String clName, String ckTime, String ckSL, String LYR) {
        this.ckName = ckName;
        this.clName = clName;
        this.ckTime = ckTime;
        this.ckSL = ckSL;
        this.LYR = LYR;
    }
}
