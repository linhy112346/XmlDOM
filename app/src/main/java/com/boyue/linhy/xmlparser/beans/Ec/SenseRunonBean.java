package com.boyue.linhy.xmlparser.beans.Ec;

public class SenseRunonBean {
    //sense (sense-word?, (pos|style|gram)*, abbr?, def?, antonym?)

    private int    runonId    = 0;
    private String sense_word = "null";
    private String pos        = "null";
    private String style      = "null";
    private String gram       = "null";
    private String abbr       = "null";
    private String def        = "null";
    private String antonym    = "null";

    public void setRunonId(int runonId) {
        this.runonId = runonId;
    }

    public int getRunonId() {
        return runonId;
    }

    public void setSense_word(String sense_word) {
        this.sense_word = sense_word;
    }

    public String getSense_word() {
        return sense_word;
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
        if (this.style.equals("null")) {
            this.style = style;
        } else {
            this.style = this.style + "," + style;
        }
    }

    public String getStyle() {
        return style;
    }

    public void setGram(String gram) {
        if (this.gram.equals("null")) {
            this.gram = gram;
        } else {
            this.gram = this.gram + "," + gram;
        }
    }

    public String getGram() {
        return gram;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getDef() {
        return def;
    }

    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }

    public String getAntonym() {
        return antonym;
    }

    //sense (sense-word?, (pos|style|gram)*, abbr?, def?, antonym?)
    public void resetSense() {
        runonId = 0;
        sense_word = "null";
        pos = "null";
        style = "null";
        gram = "null";
        abbr = "null";
        def = "null";
        antonym = "null";
    }

    public void set(String name, String value) {
        switch (name) {
            case "sense-word":
                setSense_word(value);
                break;
            case "pos":
                setPos(value);
                break;
            case "style":
                setStyle(value);
                break;
            case "gram":
                setGram(value);
                break;
            case "abbr":
                setAbbr(value);
                break;
            case "def":
                setDef(value);
                break;
            case "antonym":
                setAntonym(value);
                break;
        }
    }
}
