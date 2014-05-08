package jp.violetyk.android.hellowidget.app;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 */
public class HelloAppWidget extends AppWidgetProvider {

    // AndroidManifest.xmlのインテントフィルタに指定した、ボタンクリックのインテント
    final String filter = "jp.violetyk.android.hellowidget.app.BUTTON_CLICKED";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // 例1: ウィジェットのクリックイベントを取得する
        // hello_app_widget.xmlをレイアウトとしたRemoteViewsオブジェクトを生成する
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.hello_app_widget);

        // 自分のみ受け取るインテントのインスタンスを生成
        Intent intent1 = new Intent(filter);
        // PendingIntentのインスタンスを生成
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, 0);

        // RemoteViewオブジェクトのボタンに、PendingIntentを紐付ける
        views.setOnClickPendingIntent(R.id.button1, pendingIntent1);


        // 例2: ウィジェットからアクティビティを起動する
        // urlを指定してPendingIntentのインスタンスを生成、RemoteViewオブジェクトのボタンにPendingIntentを紐付ける
        Uri uri = Uri.parse("http://www.google.com");
        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, 0);
        views.setOnClickPendingIntent(R.id.button2, pendingIntent2);

        // ウィジェットを更新
        appWidgetManager.updateAppWidget(appWidgetIds, views);
//        // ウイジェットを更新。複数ウィジェットがある場合には、全部更新。
//        final int N = appWidgetIds.length;
//        for (int i=0; i<N; i++) {
//            Log.d(context.getString(R.string.app_name), Integer.toString(i));
//            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
//        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        // ウィジェットがクリックされた際にログに出力
        String action = intent.getAction();
        if (action.equals(filter)) {
            Log.d(context.getString(R.string.app_name), "uhoho");

        }
    }



    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
            int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.hello_app_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}


