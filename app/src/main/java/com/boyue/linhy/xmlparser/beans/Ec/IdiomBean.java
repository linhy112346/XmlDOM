package com.boyue.linhy.xmlparser.beans.Ec;

/**
 * Created by linhy on 17-6-21.
 */

public class IdiomBean {
    //entry (word, homo?, (style|gram|pron|variant|variant-pron|pos|inflection?)*, sense*, idiom*, runon*, phrase*)>
    private int    entryId    = 0;
    private String idiom_word = "null";

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setIdiom_word(String idiom_word) {
        this.idiom_word = idiom_word;
    }

    public String getIdiom_word() {
        return idiom_word;
    }

    public void set(String name, String value) {
        switch (name) {
            case "idiom-word":
                setIdiom_word(value);
                break;
        }
    }

    public void resetIdiom() {
        entryId = 0;
        idiom_word = "null";
    }
}
