package jp.violetyk.android.apilistview.app;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http");
        builder.authority("api.nanapi.jp");
        builder.path("/v1/recipeSearchDetails/");
        builder.appendQueryParameter("key", "yourapikey");
        builder.appendQueryParameter("format", "json");
        String uri = builder.toString();

        HttpGet get = new HttpGet(uri);

        class MyCallback implements AsyncHttpRequestCallback {
            private Activity context;
            public MyCallback(Activity context) {
                this.context = context;
            }
            @Override
            public void execute(String result) {
                ArrayList<String> titles = new ArrayList<String>();

                try {
                    JSONObject rootObject = new JSONObject(result);
                    JSONArray recipeArray = rootObject.getJSONObject("response").getJSONArray("recipes");
                    for (int i = 0; i < recipeArray.length(); i++) {
                        JSONObject jsonObject = recipeArray.getJSONObject(i);
                        titles.add(jsonObject.getString("title"));
                    }

                    ArrayAdapter<String> la = new ArrayAdapter<String>(
                            this.context,
                            android.R.layout.simple_list_item_1,
                            (String[])titles.toArray(new String[0])
                    );
                    ListView lv = (ListView)this.context.findViewById(R.id.listView);
                    lv.setAdapter(la);

                } catch (Exception e) {

                }

            }
        }

        AsyncHttpRequest task = new AsyncHttpRequest(new MyCallback(this));
        task.execute(get);


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



}
