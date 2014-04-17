package jp.violetyk.android.myfirstapp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button_submit);
        btn.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    //インターフェイスを実装 implements OnClickListener
    public void onClick(View v) {

        String name = ((TextView)findViewById(R.id.editTextName)).getText().toString();
        String email = ((TextView)findViewById(R.id.editTextEmail)).getText().toString();

        Intent i = new Intent();
        i.setClassName("jp.violetyk.android.myfirstapp.app", "jp.violetyk.android.myfirstapp.app.Sub1Activity");
        i.putExtra("name", name);
        i.putExtra("email", email);


        startActivity(i);
    }
}
