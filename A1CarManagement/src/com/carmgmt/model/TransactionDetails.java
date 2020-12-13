package com.carmgmt.model;

public class TransactionDetails {
    int transactionid;
    int userid;
    int carid;

    public TransactionDetails() {
    }

    public TransactionDetails(int transactionid, int userid, int carid) {
        this.transactionid = transactionid;
        this.userid = userid;
        this.carid = carid;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }
}
