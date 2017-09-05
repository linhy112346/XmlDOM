package com.boyue.linhy.xmlparser.beans.Ec;

/**
 * Created by linhy on 17-6-21.
 */

public class EntryBean {
    //entry (word, homo?, (style|gram|pron|variant|variant-pron|pos|inflection?)*, sense*, idiom*, runon*, phrase*)>

    private String subdict      = "null";
    private String word         = "null";
    private String homo         = "null";
    private String style        = "null";
    private String gram         = "null";
    private String pron         = "null";
    private String variant      = "null";
    private String variant_pron = "null";
    private String pos          = "null";
    private String inflection   = "null";

    public void setSubdict(String subdict) {
        this.subdict = subdict;
    }

    public String getSubdict() {
        return subdict;
    }

    public void setPron(String pron) {
        if (this.pron.equals("null")) {
            this.pron = pron;
        } else {
            this.pron = this.pron + "," + pron;
        }
    }

    public String getPron() {
        return pron;
    }

    public void setVariant(String variant) {
        if (this.variant.equals("null")) {
            this.variant = variant;
        } else {
            this.variant = this.variant + "," + variant;
        }
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant_pron(String variant_pron) {
        if (this.variant_pron.equals("null")) {
            this.variant_pron = variant_pron;
        } else {
            this.variant_pron = this.variant_pron + "," + variant_pron;
        }
    }

    public String getVariant_pron() {
        return variant_pron;
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

    public void setInflection(String inflection) {
        if (this.inflection.equals("null")) {
            this.inflection = inflection;
        } else {
            this.inflection = this.inflection + "," + inflection;
        }
    }

    public String getInflection() {
        return inflection;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setHomo(String homo) {
        this.homo = homo;
    }

    public String getHomo() {
        return homo;
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
    //entry (word, homo?, (style|gram|pron|variant|variant-pron|pos|inflection?)*, sense*, idiom*, runon*, phrase*)>

    public void set(String name, String value) {
        switch (name) {
            case "subdict":
                setSubdict(value);
                break;
            case "word":
                setWord(value);
                break;
            case "homo":
                setHomo(value);
                break;
            case "style":
                setStyle(value);
                break;
            case "gram":
                setGram(value);
                break;
            case "pron":
                setPron(value);
                break;
            case "variant":
                setVariant(value);
                break;
            case "variant-pron":
                setVariant_pron(value);
                break;
            case "pos":
                setPos(value);
                break;
            case "inflection":
                setInflection(value);
                break;
        }
    }

    public void resetEntry() {
        subdict = "null";
        word = "null";
        homo = "null";
        style = "null";
        gram = "null";
        pron = "null";
        variant = "null";
        variant_pron = "null";
        pos = "null";
        inflection = "null";
    }
}
