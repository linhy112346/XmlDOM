package com.boyue.linhy.xmlparser.db.Ec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boyue.linhy.xmlparser.beans.Ec.SenseEntryBean;
import com.boyue.linhy.xmlparser.db.DBHelper;

public class SenseEntryDAO {
    private DBHelper mDBHelper;
    private Context  context;

    public SenseEntryDAO(Context context) {
        // TODO Auto-generated constructor stub
        mDBHelper = DBHelper.newInstance(context);
        this.context = context;
    }

    public synchronized void insert(SenseEntryBean senseEntryBean) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        //entry (word, homo?, (style|gram|pron|variant|variant-pron|pos|inflection?)*, sense*, idiom*, runon*, phrase*)>
        db.execSQL("insert into " + mDBHelper.sense_entry_info + "( entryId,sense_word,pos,style,gram,abbr,def,antonym) values(?,?,?,?,?,?,?,?)",
                   new Object[]{senseEntryBean.getEntryId(),
                                senseEntryBean.getSense_word(),
                                senseEntryBean.getPos(),
                                senseEntryBean.getStyle(),
                                senseEntryBean.getGram(),
                                senseEntryBean.getAbbr(),
                                senseEntryBean.getDef(),
                                senseEntryBean.getAntonym()});
        db.close();
    }

  /*  //
    public synchronized int getIdForTable(String word, String pinyin) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + mDBHelper.entry_info + " where word = ? and pinyin= ? ",
                                    new String[]{word,
                                                 pinyin});
        //        boolean isExists = cursor.moveToNext();
        int id = -1;
        while (cursor.moveToNext()) {
            id = (cursor.getInt(cursor.getColumnIndex("_ID")));
        }

        cursor.close();
        db.close();
        return id;
    }*/

}
