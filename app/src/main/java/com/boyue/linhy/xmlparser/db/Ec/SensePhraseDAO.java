package com.boyue.linhy.xmlparser.db.Ec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boyue.linhy.xmlparser.beans.Ec.SensePhraseBean;
import com.boyue.linhy.xmlparser.db.DBHelper;

public class SensePhraseDAO {
    private DBHelper mDBHelper;
    private Context  context;

    public SensePhraseDAO(Context context) {
        // TODO Auto-generated constructor stub
        mDBHelper = DBHelper.newInstance(context);
        this.context = context;
    }

    public synchronized void insert(SensePhraseBean sensePhraseBean) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("insert into " + mDBHelper.sense_phrase_info + "( phraseId,sense_word,pos,style,gram,abbr,def,antonym) values(?,?,?,?,?,?,?,?)",
                   new Object[]{sensePhraseBean.getPhraseId(),
                                sensePhraseBean.getSense_word(),
                                sensePhraseBean.getPos(),
                                sensePhraseBean.getStyle(),
                                sensePhraseBean.getGram(),
                                sensePhraseBean.getAbbr(),
                                sensePhraseBean.getDef(),
                                sensePhraseBean.getAntonym()});
        db.close();
    }
}
