package com.boyue.linhy.xmlparser.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper
        extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME     = "myecdict.db";
    public static       String entry_info        = "entry_info";
    public static       String sense_entry_info  = "sense_entry_info";
    public static       String idiom_info        = "idiom_info";
    public static       String sense_idiom_info  = "sense_idiom_info";
    public static       String runon_info        = "runon_info";
    public static       String sense_runon_info  = "sense_runon_info";
    public static       String phrase_info       = "phrase_info";
    public static       String sense_phrase_info = "sense_phrase_info";

    private static final int      VERSION  = 1;
    private static       DBHelper dbHelper = null;

    public static DBHelper newInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public DBHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE " + entry_info + "(_ID INTEGER PRIMARY KEY autoincrement, subdict TEXT, word TEXT, homo TEXT, style TEXT, gram TEXT, pron TEXT, variant TEXT, variant_pron TEXT, pos TEXT, inflection TEXT)");
        db.execSQL("CREATE TABLE " + sense_entry_info + "(_ID INTEGER PRIMARY KEY autoincrement, entryId INTEGER, sense_word TEXT, pos TEXT, style TEXT, gram TEXT, abbr TEXT, def TEXT, antonym TEXT)");
        db.execSQL("CREATE TABLE " + idiom_info + "(_ID INTEGER PRIMARY KEY autoincrement, entryId INTEGER, idiom_word TEXT)");
        db.execSQL("CREATE TABLE " + sense_idiom_info + "(_ID INTEGER PRIMARY KEY autoincrement, idiomId INTEGER, sense_word TEXT, pos TEXT, style TEXT, gram TEXT, abbr TEXT, def TEXT, antonym TEXT)");
        db.execSQL("CREATE TABLE " + runon_info + "(_ID INTEGER PRIMARY KEY autoincrement, entryId INTEGER, runon_word TEXT, pron TEXT, pos TEXT, style TEXT)");
        db.execSQL("CREATE TABLE " + sense_runon_info + "(_ID INTEGER PRIMARY KEY autoincrement, runonId INTEGER, sense_word TEXT, pos TEXT, style TEXT, gram TEXT, abbr TEXT, def TEXT, antonym TEXT)");
        db.execSQL("CREATE TABLE " + phrase_info + "(_ID INTEGER PRIMARY KEY autoincrement, entryId INTEGER, phrase_word TEXT)");
        db.execSQL("CREATE TABLE " + sense_phrase_info + "(_ID INTEGER PRIMARY KEY autoincrement, phraseId INTEGER, sense_word TEXT, pos TEXT, style TEXT, gram TEXT, abbr TEXT, def TEXT, antonym TEXT)");

        //        db.execSQL("CREATE TABLE " + categroy_entry_info + "(_ID INTEGER PRIMARY KEY autoincrement, wordId INTEGER, pos TEXT)");
        //        db.execSQL("CREATE TABLE " + example_categroy_info + "(_ID INTEGER PRIMARY KEY autoincrement, senseId INTEGER, ex TEXT, pinyin TEXT, tran TEXT)");
        //        db.execSQL("CREATE TABLE " + sense_categroy_info + "(_ID INTEGER PRIMARY KEY autoincrement, categroyId INTEGER, style TEXT, field TEXT, def TEXT, tran TEXT, isExampleExist INTEGER)");
        //        db.execSQL("CREATE TABLE " + example_entry_info + "(_ID INTEGER PRIMARY KEY autoincrement, senseId INTEGER, ex TEXT, pinyin TEXT, tran TEXT)");
        //        db.execSQL("CREATE TABLE " + sub_info + "(_ID INTEGER PRIMARY KEY autoincrement, word TEXT, see TEXT)");

        /* db.execSQL("CREATE TABLE " + user_info + "(_ID INTEGER PRIMARY KEY autoincrement, userid INTEGER, username TEXT,realname TEXT,nickname TEXT ,schoolid INTEGER,schoolname TEXT,classid INTEGER,classnum INTEGER,gradeid INTEGER,birthdate TEXT ,sex INTEGER,level INTEGER,isaudit INTEGER,headimg TEXT,token TEXT)");
        //userid,bookid,bookname,progress,downloaded,md5,netstatus,filepath,filesize,fielcover
        db.execSQL("CREATE TABLE " + book_info + "(_ID INTEGER PRIMARY KEY autoincrement,userid INTEGER, bookid INTEGER, bookname TEXT, progress INTEGER,isfinished INTEGER,md5 TEXT,netstatus INTEGER,filepath TEXT,filesize TEXT,bookurl TEXT,coverurl TEXT,spineIndex INTEGER,pageIndex INTEGER,fontSize INTEGER,pageMargin INTEGER,isOut INTERGER,lasttime LONG,isquestion INTEGER )");

        db.execSQL("CREATE TABLE " + alound_info + "(_ID INTEGER PRIMARY KEY autoincrement,filename TEXT, filepath TEXT,userid INTEGER, bookid INTEGER, lessonid INTEGER,type INTEGER,score INTEGER)");


        db.execSQL("CREATE TABLE " + follow_info + "(_ID INTEGER PRIMARY KEY autoincrement,filename TEXT , filepath TEXT,userid INTEGER, bookid INTEGER, lessonid INTEGER,type INTEGER,score INTEGER)");

        //创建存储埋点日志的数据库
        db.execSQL("CREATE TABLE " + tracking_info + "(_ID INTEGER PRIMARY KEY autoincrement," +
                "userId INTEGER,bookId LONG, bookName TEXT,bookAuthor TEXT,startTime LONG, stopTime LONG,percent INTEGER, pageStartIndex INTEGER,spineStartIndex INTEGER,pageStopIndex INTEGER,spineStopIndex INTEGER)");

        db.execSQL("CREATE TABLE " + newinstallapk_info + "(_ID INTEGER PRIMARY KEY autoincrement," +
                "userId INTEGER,time LONG, packageName TEXT,appName TEXT)");
        db.execSQL("CREATE TABLE " + importbook_info + "(_ID INTEGER PRIMARY KEY autoincrement,userid INTEGER,filename TEXT ,filepath TEXT,filecover TEXT,md5 TEXT,importstatus INTEGER,filesize TEXT,filetype TEXT)");
        db.execSQL("CREATE TABLE " + readbook_info + "(_ID INTEGER PRIMARY KEY autoincrement,userid INTEGER,bookname TEXT ,lastreadtime TEXT ,author TEXT,readnum LONG, progress INTEGER ,readtime LONG)");

        db.execSQL("CREATE TABLE " + reader_info + "(_ID INTEGER PRIMARY KEY autoincrement,userid INTEGER, bookid INTEGER, bookname TEXT, progress INTEGER,md5 TEXT,filepath TEXT,spineIndex INTEGER,pageIndex INTEGER)");
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS " + entry_info);
        db.execSQL("DROP TABLE IF EXISTS " + sense_entry_info);
        db.execSQL("DROP TABLE IF EXISTS " + idiom_info);
        db.execSQL("DROP TABLE IF EXISTS " + sense_idiom_info);
        db.execSQL("DROP TABLE IF EXISTS " + runon_info);
        /*db.execSQL("DROP TABLE IF EXISTS " + book_info);
        db.execSQL("DROP TABLE IF EXISTS " + user_info);
        db.execSQL("DROP TABLE IF EXISTS " + alound_info);
        db.execSQL("DROP TABLE IF EXISTS " + follow_info);
        db.execSQL("DROP TABLE IF EXISTS " + tracking_info);
        db.execSQL("DROP TABLE IF EXISTS " + newinstallapk_info);
        db.execSQL("DROP TABLE IF EXISTS " + importbook_info);
        db.execSQL("DROP TABLE IF EXISTS " + readbook_info);
        db.execSQL("DROP TABLE IF EXISTS " + reader_info);*/
        onCreate(db);
    }

    private void updateUserInfo(SQLiteDatabase db, int newVersion) {

        switch (newVersion) {
            case 2:
                String CREATE_BOOK = "create table user_info(_ID INTEGER PRIMARY KEY autoincrement, userid INTEGER, username TEXT,realname TEXT,nickname TEXT ,schoolid INTEGER,schoolname TEXT,classid INTEGER,classnum INTEGER,gradeid INTEGER,birthdate TEXT ,sex INTEGER,level INTEGER,isaudit INTEGER,headimg TEXT,token TEXT)";

                String CREATE_TEMP_BOOK = "alter table user_info rename to temp_user_info";
                //                INSERT INTO X.TABLE(fieldname1, fieldname2) SELECT fieldname1, fieldname2 FROM Y.TABLE
                String INSERT_DATA = "insert into user_info  select  *,'' from  temp_user_info";
                String DROP_BOOK = "drop table  temp_user_info";
                db.execSQL(CREATE_TEMP_BOOK);

                db.execSQL(CREATE_BOOK);

                db.execSQL(INSERT_DATA);

                db.execSQL(DROP_BOOK);

                break;
        }


    }

}
