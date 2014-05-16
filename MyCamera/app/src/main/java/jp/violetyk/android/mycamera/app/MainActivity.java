package jp.violetyk.android.mycamera.app;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {

    // カメラの宣言
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        //------------------------------------
        // インテントを指定して内蔵カメラを起動する
        //------------------------------------
        // インテントを作成する
        Intent intent = new Intent();
        // インテントのアクションを指定
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        // 標準カメラアプリケーションのアクティビティを起動
        startActivity(intent);
        // アクティビティを閉じる
        finish();
 */
 /*
        //------------------------------------
        // 内蔵カメラの設定を調べる
        // AndroidManifest.xmlにパーミッションが必要
        // <uses-permission android:name="android.permission.CAMERA" />
        //------------------------------------
        // カメラをオープン
        Camera camera = Camera.open();
        // カメラのパラメータを取得
        Camera.Parameters params = camera.getParameters();
        // テキストビューへ、パラメータ文字列をセット
        TextView textView = (TextView) findViewById(R.id.parameters);
        textView.setText(params.flatten());
        // カメラリリース
        camera.release();
 */

        // フルスクリーンの指定（画面上部のアイコンや時計を非表示にする）
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // カメラプレビュー画面の設定（サーフェイスビューのセット）
        SurfaceView cameraPreview = (SurfaceView)findViewById(R.id.preview);

        // サーフェイスホルダー生成 previewCallbackはこのクラスのメンバ変数として下記に実装されている
        cameraPreview.getHolder().addCallback(this.previewCallback);

        // サーフェイスホルダーのタイプを設定(外部バッファの使用)
        cameraPreview.getHolder().setType(
                SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // 撮影ボタンの設定
        Button button = (Button) findViewById(R.id.button);

        // 撮影ボタンのリスナー登録
        button.setOnClickListener(shutterButtonListener);

    }

    private SurfaceHolder.Callback previewCallback =  new SurfaceHolder.Callback() {
        // サーフェイス生成処理
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            // カメラ初期化
            try {
                // カメラのオープン
                camera = Camera.open();

                // Android2.1までは横方向にしか対応していない
                // Android2.1までの端末は、AndroidManifest.xmlで、android:screenOrientation="landscape"を指定する必要がある。

                camera.setDisplayOrientation(90);


                // プレビューディスプレイのセット
                camera.setPreviewDisplay(surfaceHolder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // サーフェイス変更処理
        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            // プレビューを一旦停止
            camera.stopPreview();

            // カメラのパラメータを取得
            Camera.Parameters params = camera.getParameters();

            // パラメータにプレビュー表示のサイズを設定
            params.setPreviewSize(params.getPreviewSize().width, params.getPreviewSize().height);

            // 設定したパラメータをセット
            camera.setParameters(params);

            // プレビュー開始
            camera.startPreview();
        }

        // サーフェイス解放処理

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            // プレビュー停止
            camera.stopPreview();

            // 解放
            camera.release();
        }
    };
    

    // 撮影ボタンリスナー
    private OnClickListener shutterButtonListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            // 写真の撮影
            camera.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] bytes, Camera camera) {
                    try {
                        save(bytes);
                        camera.startPreview();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    private void save(byte[] data) throws Exception {

        File dataDir = this.getFilesDir();
        if (dataDir.exists() == false) {
            dataDir.mkdirs();
        }

        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
        String path = dataDir + "/" + dateFormat.format(today) + ".jpg";
        Log.d(getPackageName(), path);
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(data);
        fos.close();
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
