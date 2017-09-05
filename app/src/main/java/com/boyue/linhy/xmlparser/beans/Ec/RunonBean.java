package com.boyue.linhy.xmlparser.beans.Ec;

/**
 * Created by linhy on 17-6-21.
 */

public class RunonBean {
    //runon (runon-word, pron?, pos*, style?, sense*)
    private int    entryId    = 0;
    private String runon_word = "null";
    private String pron       = "null";
    private String pos        = "null";
    private String style      = "null";

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setRunon_word(String runon_word) {
        this.runon_word = runon_word;
    }

    public String getRunon_word() {
        return runon_word;
    }

    public void setPron(String pron) {
        this.pron = pron;
    }

    public String getPron() {
        return pron;
    }

    public void setPos(String pos) {
        if (this.pos.equals("null")) {
            this.pos = pos;
        } else {
            this.pos = this.pos + "," + pos;
        }
    }

    public String getPos() {
        return pos;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public void set(String name, String value) {
        switch (name) {
            case "runon-word":
                setRunon_word(value);
                break;
            case "pron":
                setPron(value);
                break;
            case "pos":
                setPos(value);
                break;
            case "style":
                setStyle(value);
                break;
        }
    }

    public void resetRunon() {
        entryId = 0;
        runon_word = "null";
        pron = "null";
        pos = "null";
        style = "null";
    }
}
