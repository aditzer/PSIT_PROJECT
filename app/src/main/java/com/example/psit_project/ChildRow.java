package com.example.psit_project;

public class ChildRow {
    private int ch_id, grp_id;
    private String code = "";
    private String fid="";

    public ChildRow(int grp_id, int ch_id, String code,String fid) {
        super();
        this.ch_id = ch_id;
        this.grp_id = grp_id;
        this.code = code;
        this.fid=fid;
    }

    public String getFid() { return fid; }

    public void setFid(String fid) { this.fid = fid; }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGrp_id() {
        return grp_id;
    }

    public void setGrp_id(int grp_id) {
        this.grp_id = grp_id;
    }

    public int getCh_id() {
        return ch_id;
    }

    public void setCh_id(int ch_id) {
        this.ch_id = ch_id;
    }
}
