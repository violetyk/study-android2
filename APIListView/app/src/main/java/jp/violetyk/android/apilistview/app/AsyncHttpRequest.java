package jp.violetyk.android.apilistview.app;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.ByteArrayOutputStream;

/*
 * AsyncTask<型1, 型2,型3>
 *
 *   型1 … Activityからスレッド処理へ渡したい変数の型
 *          ※ Activityから呼び出すexecute()の引数の型
 *          ※ doInBackground()の引数の型
 *
 *   型2 … 進捗度合を表示する時に利用したい型
 *          ※ onProgressUpdate()の引数の型
 *
 *   型3 … バックグラウンド処理完了時に受け取る型
 *          ※ doInBackground()の戻り値の
 *          ※ onPostExecute()の引数の型
 *
 *   ※ それぞれ不要な場合は、Voidを設定すれば良い
 */
//public class AsyncHttpRequest extends AsyncTask<Uri.Builder, Void, String> {
public class AsyncHttpRequest extends AsyncTask<HttpUriRequest, Void, String> {

    private AsyncHttpRequestCallback callback;

    public AsyncHttpRequest(AsyncHttpRequestCallback callback) {
        this.callback = callback;
    }

    // 非同期で処理される部分。必ずオーバーライドする。
    @Override
    protected String doInBackground(HttpUriRequest... httpRequest) {

        String result = new String();

        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpRequest[0]);
        } catch (Exception e) {
            return result;
        }

        int status = httpResponse.getStatusLine().getStatusCode();

        if (HttpStatus.SC_OK == status) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(outputStream);
                result = outputStream.toString();
            } catch (Exception e) {
            }
        }


        return result;
    }


    // このメソッドは非同期処理の終わった後に呼び出されます
    @Override
    protected void onPostExecute(String result) {
        this.callback.execute(result);
    }
}