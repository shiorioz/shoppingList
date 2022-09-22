package com.example.list;

public class Data {
    private String textMemo;
    private String genre;
    private boolean delflg;

    public String getTextMemo() {
        return textMemo;
    }

    public void setTextMemo(String textMemo) {
        this.textMemo = textMemo;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public boolean isDelflg() {
        return delflg;
    }

    public void setDelflg(boolean delflg) {
        this.delflg = delflg;
    }
}
