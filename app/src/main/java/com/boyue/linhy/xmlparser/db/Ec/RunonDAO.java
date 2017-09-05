package com.boyue.linhy.xmlparser.db.Ec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boyue.linhy.xmlparser.beans.Ec.RunonBean;
import com.boyue.linhy.xmlparser.db.DBHelper;

public class RunonDAO {
    private DBHelper mDBHelper;
    private Context  context;

    public RunonDAO(Context context) {
        // TODO Auto-generated constructor stub
        mDBHelper = DBHelper.newInstance(context);
        this.context = context;
    }


    //  runon (runon-word, pron?, pos*, style?, sense*)
    public synchronized void insert(RunonBean runonBean) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.execSQL("insert into " + mDBHelper.runon_info + "( entryId,runon_word,pron,pos,style) values(?,?,?,?,?)",
                   new Object[]{runonBean.getEntryId(),
                                runonBean.getRunon_word(),
                                runonBean.getPron(),
                                runonBean.getPos(),
                                runonBean.getStyle()});
        db.close();
    }
}
