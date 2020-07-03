package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SFGL {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //种植地名称
    @ColumnInfo(name = "zzdName")
    public String zzdName;
    //肥料名称
    @ColumnInfo(name = "FLMC")
    public String FLMC;
    //肥料数量
    @ColumnInfo(name = "FLSL")
    public String FLSL;
    //施肥时间
    @ColumnInfo(name = "SFTime")
    public String SFTime;

    public int getId() {
        return id;
    }

    public String getZzdName() {
        return zzdName;
    }

    public String getFLMC() {
        return FLMC;
    }

    public String getFLSL() {
        return FLSL;
    }

    public String getSFTime() {
        return SFTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setZzdName(String zzdName) {
        this.zzdName = zzdName;
    }

    public void setFLMC(String FLMC) {
        this.FLMC = FLMC;
    }

    public void setFLSL(String FLSL) {
        this.FLSL = FLSL;
    }

    public void setSFTime(String SFTime) {
        this.SFTime = SFTime;
    }

    public SFGL(String zzdName, String FLMC, String FLSL, String SFTime) {
        this.zzdName = zzdName;
        this.FLMC = FLMC;
        this.FLSL = FLSL;
        this.SFTime = SFTime;
    }
}
