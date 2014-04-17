package jp.violetyk.android.myfirstapp.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kagaya on 2014/04/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "myfirstapp.db";
    private static int	DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        // ストレージ（ローカルファイル）にDBを作成
        // 第二引数がnullならメモリ上に作成する。
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * テーブルを作成するメソッド。データ作成時、テーブルが無いときに呼ばれる。
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS addresses(");
        sb.append(" _id INTEGER primary key autoincrement");
        sb.append(",name TEXT");
        sb.append(",email TEXT");
        sb.append(")");
        String sql = sb.toString();
        db.execSQL(sql);
    }

    /**
     * データベースをアップデートするときに呼ばれる
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//			db.execSQL("DROP TABLE IF EXISTS docs");
//			onCreate(db);
    }
}
