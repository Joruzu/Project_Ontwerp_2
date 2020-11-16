package com.example.sarnamibasicalphav1;

public class TableInfo {
    private String tIdName;
    private String tCardName;

    public TableInfo(String tIdName, String tCardName) {
        this.tIdName = tIdName;
        this.tCardName = tCardName;
    }

    public String gettIdName() {
        return tIdName;
    }

    public void settIdName(String tIdName) {
        this.tIdName = tIdName;
    }

    public String gettCardName() {
        return tCardName;
    }

    public void settCardName(String tCardName) {
        this.tCardName = tCardName;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tIdName='" + tIdName + '\'' +
                ", tCardName='" + tCardName + '\'' +
                '}';
    }
}
