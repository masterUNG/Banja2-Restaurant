package appewtc.masterung.banja2restaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 2/29/16 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public MyManage(Context context) {

        //Create Database
        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();

    }   // Constructor

}   // Main Class
