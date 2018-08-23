package virtualsystems.com.br.firstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_test";
    public static final Integer DATABASE_VERSION = 1;

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCmd = "CREATE TABLE tb_test (id INTEGER PRIMARY KEY, name TEXT)";
        db.execSQL(sqlCmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            String sqlCmdDrop = "DROP TABLE IF EXISTS tb_test";
            String sqlCmd = "CREATE TABLE tb_test (id INTEGER PRIMARY KEY, name TEXT)";
            db.execSQL(sqlCmdDrop);
            db.execSQL(sqlCmd);
        }
    }
}
