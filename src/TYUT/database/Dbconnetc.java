package TYUT.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbconnetc extends SQLiteOpenHelper {
 
	private static final String DB_NAME = "mydata.db"; //���ݿ�����
    private static final int version = 1; //���ݿ�汾
     
    public  Dbconnetc(Context context) {
        super(context, DB_NAME, null, version);
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists user(username varchar(20) not null , password varchar(60) not null );";          
        db.execSQL(sql);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
 
    }
 
}