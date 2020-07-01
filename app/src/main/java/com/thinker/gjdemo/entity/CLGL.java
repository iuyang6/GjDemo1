package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CLGL {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //材料名称
    @ColumnInfo(name = "clmc")
    public String clmc;

    @ColumnInfo(name = "jldw")
    public String jldw;
    //执法地点
    @ColumnInfo(name = "syzt")
    public String syzt;

    public int getId() {
        return id;
    }

    public String getClmc() {
        return clmc;
    }

    public String getJldw() {
        return jldw;
    }

    public String getSyzt() {
        return syzt;
    }

    public void setClmc(String clmc) {
        this.clmc = clmc;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public void setSyzt(String syzt) {
        this.syzt = syzt;
    }

    public CLGL(String clmc, String jldw, String syzt) {
        this.clmc = clmc;
        this.jldw = jldw;
        this.syzt = syzt;
    }
}
