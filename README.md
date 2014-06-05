study-android2
==============

Android開発の勉強

## MyFirstApp

- ボタンを押したときのイベントリスナーの設定
- Intentをつかって、Activity間のパラメータの受け渡し
- Toastでメッセージの表示
- SQLiteでデータの永続化
  - SQLite初期化
  - DB作成
  - Insert
  - Select
  - ActivityのDestroy時にクローズ


## APIListView

- JSONを返すAPIをコール
- AsyncTaskを使って非同期処理
- ArrayAdapterを介してListViewに渡して表示

## HelloWidget

- ホーム画面にウィジェットの設置
  - AndroidManifest.xmlに<receiver>の記述
  - res/xml/hello_app_widget_info.xmlに起動するウィジェットに関する設定
  - res/layout/hello_app_widget.xmlにウィジェットのデザイン
  - Activityの代わりにAppWidgetProviderをつかう
- ウィジェットのクリックイベントを取得する
  - RemoteViewsオブジェクトを使って、ウィジェットのボタンにPendingIntentを紐付ける
- ウィジェットからアクティビティを起動する

## MyCamera
- 端末内蔵のカメラ起動して撮影
- 画像を保存
- 画像一覧画面
- 画像個別ビュー
  - 個別ビューで通信とか何かできてもいいかも

## PagerTabStrip
- Google Play ストア風のアプリ
  - http://dev.classmethod.jp/smartphone/android/android-tips-36-pagertabstrip/

## UseAndroidBootstrap
- Android-Bootstrapを使って見るテスト
  - https://github.com/Bearded-Hen/Android-Bootstrap/
