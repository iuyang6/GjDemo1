package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CSD {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //种植地名称
    @ColumnInfo(name = "zzdName")
    public String zzdName;
    //柑橘品种
    @ColumnInfo(name = "GJPZ")
    public String GJPZ;
    //成熟度级别
    @ColumnInfo(name = "CSDJB")
    public String CSDJB;
    //估计成熟数量
    @ColumnInfo(name = "GJSL")
    public String GJSL;

    public void setId(int id) {
        this.id = id;
    }

    public void setZzdName(String zzdName) {
        this.zzdName = zzdName;
    }

    public void setGJPZ(String GJPZ) {
        this.GJPZ = GJPZ;
    }

    public void setCSDJB(String CSDJB) {
        this.CSDJB = CSDJB;
    }

    public void setGJSL(String GJSL) {
        this.GJSL = GJSL;
    }

    public int getId() {
        return id;
    }

    public String getZzdName() {
        return zzdName;
    }

    public String getGJPZ() {
        return GJPZ;
    }

    public String getCSDJB() {
        return CSDJB;
    }

    public String getGJSL() {
        return GJSL;
    }

    public CSD(String zzdName, String GJPZ, String CSDJB, String GJSL) {
        this.zzdName = zzdName;
        this.GJPZ = GJPZ;
        this.CSDJB = CSDJB;
        this.GJSL = GJSL;
    }
}
