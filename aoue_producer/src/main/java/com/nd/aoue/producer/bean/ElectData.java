package com.nd.aoue.producer.bean;

//通话记录日志类
public class ElectData {

    private String cand_nm;  // 候选人名字
    private String contbr_nm;//投票人名字
    private String contbr_st;  // 投票人所在州
    private String contbr_employer;  // 投票人的雇佣者
    private String contbr_occupation;  // 投票人职业
    private String contb_receipt_amt;  // 投票人捐赠金额
    private String contb_receipt_dt;  // 投票日期

    public ElectData(String cand_nm, String contbr_nm, String contbr_st, String contbr_employer,
                     String contbr_occupation, String contb_receipt_amt, String contb_receipt_dt) {
        this.cand_nm = cand_nm;
        this.contbr_nm = contbr_nm;
        this.contbr_st = contbr_st;
        this.contbr_employer = contbr_employer;
        this.contbr_occupation = contbr_occupation;
        this.contb_receipt_amt = contb_receipt_amt;
        this.contb_receipt_dt = contb_receipt_dt;
    }

    @Override
    public String toString() {
        return  cand_nm + ',' + contbr_nm + ','  + contbr_st + ',' +
                contbr_employer + ',' + contbr_occupation + ',' +
                contb_receipt_amt + ',' + contb_receipt_dt + '\t';
    }

    public String getCand_nm() {
        return cand_nm;
    }

    public void setCand_nm(String cand_nm) {
        this.cand_nm = cand_nm;
    }

    public String getContbr_nm() {
        return contbr_nm;
    }

    public void setContbr_nm(String contbr_nm) {
        this.contbr_nm = contbr_nm;
    }

    public String getContbr_st() {
        return contbr_st;
    }

    public void setContbr_st(String contbr_st) {
        this.contbr_st = contbr_st;
    }

    public String getContbr_employer() {
        return contbr_employer;
    }

    public void setContbr_employer(String contbr_employer) {
        this.contbr_employer = contbr_employer;
    }

    public String getContbr_occupation() {
        return contbr_occupation;
    }

    public void setContbr_occupation(String contbr_occupation) {
        this.contbr_occupation = contbr_occupation;
    }

    public String getContb_receipt_amt() {
        return contb_receipt_amt;
    }

    public void setContb_receipt_amt(String contb_receipt_amt) {
        this.contb_receipt_amt = contb_receipt_amt;
    }

    public String getContb_receipt_dt() {
        return contb_receipt_dt;
    }

    public void setContb_receipt_dt(String contb_receipt_dt) {
        this.contb_receipt_dt = contb_receipt_dt;
    }
}
