package com.thinker.gjdemo.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RKJL {
    @PrimaryKey(autoGenerate = true)
    public int id;
    //选择的仓库名称
    @ColumnInfo(name = "ckName")
    public String ckName;
    //选择的入库材料
    @ColumnInfo(name = "clName")
    public String clName;
    //入库时间
    @ColumnInfo(name = "rkTime")
    public String rkTime;
    //材料单价
    @ColumnInfo(name = "clDanJia")
    public String clDanJia;
    //材料总金额
    @ColumnInfo(name = "clZJE")
    public String clZJE;
    //材料保质期
    @ColumnInfo(name = "clBZQ")
    public String clBZQ;
    //材料采购人
    @ColumnInfo(name = "clCGR")
    public String clCGR;
    //材料验收人
    @ColumnInfo(name = "clYSR")
    public String clYSR;

    public int getId() {
        return id;
    }

    public String getCkName() {
        return ckName;
    }

    public String getClName() {
        return clName;
    }

    public String getRkTime() {
        return rkTime;
    }

    public String getClDanJia() {
        return clDanJia;
    }

    public String getClZJE() {
        return clZJE;
    }

    public String getClBZQ() {
        return clBZQ;
    }

    public String getClCGR() {
        return clCGR;
    }

    public String getClYSR() {
        return clYSR;
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

    public void setRkTime(String rkTime) {
        this.rkTime = rkTime;
    }

    public void setClDanJia(String clDanJia) {
        this.clDanJia = clDanJia;
    }

    public void setClZJE(String clZJE) {
        this.clZJE = clZJE;
    }

    public void setClBZQ(String clBZQ) {
        this.clBZQ = clBZQ;
    }

    public void setClCGR(String clCGR) {
        this.clCGR = clCGR;
    }

    public void setClYSR(String clYSR) {
        this.clYSR = clYSR;
    }

    public RKJL(String ckName, String clName, String rkTime, String clDanJia, String clZJE, String clBZQ, String clCGR, String clYSR) {
        this.ckName = ckName;
        this.clName = clName;
        this.rkTime = rkTime;
        this.clDanJia = clDanJia;
        this.clZJE = clZJE;
        this.clBZQ = clBZQ;
        this.clCGR = clCGR;
        this.clYSR = clYSR;
    }
}
