package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CK {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //仓库名称
    @ColumnInfo(name = "ckName")
    public String ckName;
    //仓库地点
    @ColumnInfo(name = "ckLocation")
    public String ckLocation;
    //仓库描述
    @ColumnInfo(name = "ckDetail")
    public String ckDetail;
    //仓库负责人
    @ColumnInfo(name = "ckFuZeRen")
    public String ckFuZeRen;
    //仓库保管员
    @ColumnInfo(name = "ckKeeper")
    public String ckKeeper;

    public int getId() {
        return id;
    }

    public String getCkName() {
        return ckName;
    }

    public String getCkLocation() {
        return ckLocation;
    }

    public String getCkDetail() {
        return ckDetail;
    }

    public String getCkFuZeRen() {
        return ckFuZeRen;
    }

    public String getCkKeeper() {
        return ckKeeper;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCkName(String ckName) {
        this.ckName = ckName;
    }

    public void setCkLocation(String ckLocation) {
        this.ckLocation = ckLocation;
    }

    public void setCkDetail(String ckDetail) {
        this.ckDetail = ckDetail;
    }

    public void setCkFuZeRen(String ckFuZeRen) {
        this.ckFuZeRen = ckFuZeRen;
    }

    public void setCkKeeper(String ckKeeper) {
        this.ckKeeper = ckKeeper;
    }

    public CK(String ckName, String ckLocation, String ckDetail, String ckFuZeRen, String ckKeeper) {
        this.ckName = ckName;
        this.ckLocation = ckLocation;
        this.ckDetail = ckDetail;
        this.ckFuZeRen = ckFuZeRen;
        this.ckKeeper = ckKeeper;
    }
}
