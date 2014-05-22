package jp.violetyk.android.mycamera.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by kagaya on 2014/05/20.
 */
public class ImageAdapter extends BaseAdapter {
    private Context myContext;
//    private int[] imgs;
    private ArrayList<String> imageList;

//    public ImageAdapter(Context myContext,int[] imgs) {
    public ImageAdapter(Context myContext, ArrayList<String> imageList) {
//        this.imgs = imgs;
        this.imageList = imageList;
        this.myContext = myContext;
    }

    public int getCount() {
        return imageList.size();
//        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        Log.d("getItem", String.valueOf(position));
        return this.imageList.get(position);
//        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        // ImageView　インスタンスを生成
        ImageView imageView = new ImageView(this.myContext);

        // リソースファイルを取得
        Resources res = this.myContext.getResources();

        // ビットマップに変更
        String path = imageList.get(position);

        /*
        カメラで撮影した高画質画像を読み込もうとしたときに起きるエラーの回避

        java.lang.OutOfMemoryError
        at android.graphics.BitmapFactory.nativeDecodeStream(Native Method)
        */

        // ビットマップ作成オブジェクトの設定
        BitmapFactory.Options bmfOptions = new BitmapFactory.Options();
        // ARGBでそれぞれ0～127段階の色を使用（メモリ対策）
        bmfOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;
        // 画像を1/20サイズに縮小（メモリ対策）
        bmfOptions.inSampleSize = 20;
        // システムメモリ上に再利用性の無いオブジェクトがある場合に勝手に解放（メモリ対策）
        bmfOptions.inPurgeable = true;
        // 現在の表示メトリクスの取得
        DisplayMetrics dm = res.getDisplayMetrics();
        // ビットマップのサイズを現在の表示メトリクスに合わせる（メモリ対策）
        bmfOptions.inDensity = dm.densityDpi;
        // 画像ファイルオブジェクトとビットマップ作成オブジェクトから、ビットマップオブジェクト作成
        Bitmap bitmap = BitmapFactory.decodeFile(path,bmfOptions);

//        Bitmap bitmap = BitmapFactory.decodeFile(path);
//        Bitmap bitmap = BitmapFactory.decodeFile();
//        Bitmap bitmap = BitmapFactory.decodeResource(res, imgs[position]);


        // 画像をセットする
        imageView.setImageBitmap(bitmap);

        // スケールタイプをセット
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        // レイアウトパラメータをセット
        imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));

        return imageView;
    }
}
