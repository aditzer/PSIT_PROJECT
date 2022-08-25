package com.example.psit_project;

import java.util.ArrayList;

public class ParentRow {

    private String name;
    private ArrayList<ChildRow> childRowList = new ArrayList<ChildRow>();

    public ParentRow(String name, ArrayList<ChildRow> childRowList) {
        super();
        this.name = name;
        this.childRowList = childRowList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildRow> getChildList() {
        return childRowList;
    }

    public void setCountryList(ArrayList<ChildRow> childRowList) {
        this.childRowList = childRowList;
    }
}
