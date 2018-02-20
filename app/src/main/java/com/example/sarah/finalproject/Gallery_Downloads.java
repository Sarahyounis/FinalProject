package com.example.sarah.finalproject;

import android.widget.ImageView;

/**
 * Created by Sarah on 9/20/2017.
 */

public class Gallery_Downloads {

    private long id;
    private int imv;
    private String strNote;
    private String filePath=null;

    public Gallery_Downloads(String filePath, String strNote) {
        this.strNote = strNote;
        this.filePath = filePath;
    }

    public Gallery_Downloads(long id, String strNote, String filePath) {
        this.id = id;
        this.imv = imv;
        this.strNote = strNote;
        this.filePath = filePath;
    }

    public Gallery_Downloads(int imv, String strNote) {
        this.imv = imv;
        this.strNote = strNote;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getImv() {
        return imv;
    }

    public String getStrNote() {
        return strNote;
    }

    public void setImv(int imv) {
        this.imv = imv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStrNote(String strNote) {
        this.strNote = strNote;
    }
}
