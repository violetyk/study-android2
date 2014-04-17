package jp.violetyk.android.myfirstapp.app;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class Sub1Activity extends Activity {

    // データベースヘルパーの作成
    private DatabaseHelper helper = new DatabaseHelper(this);
    // データベースの宣言
    public static SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);

        Intent intent = getIntent();
        if(intent != null){
            String name  = intent.getStringExtra("name");
            String email = intent.getStringExtra("email");
            ((TextView)findViewById(R.id.textName)).setText(name);
            ((TextView)findViewById(R.id.textEmail)).setText(email);

            db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", name);
            values.put("email", email);
            db.insert("addresses", null, values);

            //ちゃんとはいってる？
            Cursor c = db.rawQuery("SELECT COUNT(_id) FROM addresses;", null);
            c.moveToFirst();
            int count = c.getInt(0);
            c.close();

            String message = String.format("登録しました。現在 %d 件", count);
//            Toast.makeText(this, getString(R.string.message_registered), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sub1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper.close();
    }


}
