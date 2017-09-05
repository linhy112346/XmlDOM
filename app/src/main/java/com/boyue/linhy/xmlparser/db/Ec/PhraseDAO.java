package com.boyue.linhy.xmlparser.db.Ec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boyue.linhy.xmlparser.beans.Ec.PhraseBean;
import com.boyue.linhy.xmlparser.db.DBHelper;

public class PhraseDAO {
    private DBHelper mDBHelper;
    private Context  context;

    public PhraseDAO(Context context) {
        // TODO Auto-generated constructor stub
        mDBHelper = DBHelper.newInstance(context);
        this.context = context;
    }

    public synchronized void insert(PhraseBean phraseBean) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("insert into " + mDBHelper.phrase_info + "( entryId,phrase_word) values(?,?)",
                   new Object[]{phraseBean.getEntryId(),
                                phraseBean.getPhrase_word(),
                                });
        db.close();
    }
}
