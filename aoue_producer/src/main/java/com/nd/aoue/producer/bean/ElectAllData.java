package com.nd.aoue.producer.bean;

import com.nd.aoue.common.bean.Data;
import com.nd.aoue.common.util.CSVUtil;

/**
 * 联系人
 */
public class ElectAllData extends Data {

    private String cmte_id;
    private String cand_id;
    private String cand_nm;
    private String contbr_nm;
    private String contbr_city;
    private String contbr_st;
    private String contbr_zip;
    private String contbr_employer;
    private String contbr_occupation;
    private String contb_receipt_amt;
    private String contb_receipt_dt;
    private String receipt_desc;
    private String memo_cd;
    private String memo_text;
    private String form_tp;
    private String file_num;

    @Override
    public void setValue(Object val) {
        context = (String) val;  // 读取：所有表头
        System.out.println(val);
        String[] split = CSVUtil.split(context);
        setCmte_id(split[0]);
        setCand_id(split[1]);
        setCand_nm(split[2]);
        setContbr_nm(split[3]);
        setContbr_city(split[4]);
        setContbr_st(split[5]);
        setContbr_zip(split[6]);
        setContbr_employer(split[7]);
        setContbr_occupation(split[8]);
        setContb_receipt_amt(split[9]);
        setContb_receipt_dt(split[10]);
        setReceipt_desc(split[11]);
        setMemo_cd(split[12]);
        setMemo_text(split[13]);
        setForm_tp(split[14]);
        setFile_num(split[15]);
    }

    @Override
    public String toString() {
        return  cmte_id + ',' + cand_id + ',' +
                cand_nm + ',' + contbr_nm + ',' +
                contbr_city + ',' + contbr_st + ',' +
                contbr_zip + ',' + contbr_employer + ',' +
                contbr_occupation + ',' + contb_receipt_amt + ',' +
                contb_receipt_dt + ',' + receipt_desc + ',' +
                memo_cd + ',' + memo_text + ',' +
                form_tp + ',' + file_num + ',' + '\t';
    }

    public String getCmte_id() {
        return cmte_id;
    }

    public void setCmte_id(String cmte_id) {
        this.cmte_id = cmte_id;
    }

    public String getCand_id() {
        return cand_id;
    }

    public void setCand_id(String cand_id) {
        this.cand_id = cand_id;
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

    public String getContbr_city() {
        return contbr_city;
    }

    public void setContbr_city(String contbr_city) {
        this.contbr_city = contbr_city;
    }

    public String getContbr_st() {
        return contbr_st;
    }

    public void setContbr_st(String contbr_st) {
        this.contbr_st = contbr_st;
    }

    public String getContbr_zip() {
        return contbr_zip;
    }

    public void setContbr_zip(String contbr_zip) {
        this.contbr_zip = contbr_zip;
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

    public String getReceipt_desc() {
        return receipt_desc;
    }

    public void setReceipt_desc(String receipt_desc) {
        this.receipt_desc = receipt_desc;
    }

    public String getMemo_cd() {
        return memo_cd;
    }

    public void setMemo_cd(String memo_cd) {
        this.memo_cd = memo_cd;
    }

    public String getMemo_text() {
        return memo_text;
    }

    public void setMemo_text(String memo_text) {
        this.memo_text = memo_text;
    }

    public String getForm_tp() {
        return form_tp;
    }

    public void setForm_tp(String form_tp) {
        this.form_tp = form_tp;
    }

    public String getFile_num() {
        return file_num;
    }

    public void setFile_num(String file_num) {
        this.file_num = file_num;
    }
}
