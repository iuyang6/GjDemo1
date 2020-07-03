package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CZJL {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //种植地名称
    @ColumnInfo(name = "zzdName")
    public String zzdName;
    //柑橘品种
    @ColumnInfo(name = "GJPZ")
    public String GJPZ;
    //采摘数量
    @ColumnInfo(name = "CJSL")
    public String CJSL;
    //采摘时间
    @ColumnInfo(name = "CjTime")
    public String CjTime;

    public int getId() {
        return id;
    }

    public String getZzdName() {
        return zzdName;
    }

    public String getGJPZ() {
        return GJPZ;
    }

    public String getCJSL() {
        return CJSL;
    }

    public String getCjTime() {
        return CjTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setZzdName(String zzdName) {
        this.zzdName = zzdName;
    }

    public void setGJPZ(String GJPZ) {
        this.GJPZ = GJPZ;
    }

    public void setCJSL(String CJSL) {
        this.CJSL = CJSL;
    }

    public void setCjTime(String cjTime) {
        CjTime = cjTime;
    }

    public CZJL(String zzdName, String GJPZ, String CJSL, String cjTime) {
        this.zzdName = zzdName;
        this.GJPZ = GJPZ;
        this.CJSL = CJSL;
        CjTime = cjTime;
    }
}
