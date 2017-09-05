package com.boyue.linhy.xmlparser.beans.Ec;

/**
 * Created by linhy on 17-6-21.
 */

public class PhraseBean {
    //entry (word, homo?, (style|gram|pron|variant|variant-pron|pos|inflection?)*, sense*, idiom*, runon*, phrase*)>
    private int    entryId     = 0;
    private String phrase_word = "null";

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setPhrase_word(String phrase_word) {
        this.phrase_word = phrase_word;
    }

    public String getPhrase_word() {
        return phrase_word;
    }

    public void set(String name, String value) {
        switch (name) {
            case "phrase-word":
                setPhrase_word(value);
                break;
        }
    }

    public void resetPhrase() {
        entryId = 0;
        phrase_word = "null";
    }
}
