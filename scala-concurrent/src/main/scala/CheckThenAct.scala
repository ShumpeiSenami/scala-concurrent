/** チェック・ゼン・アクトというパターンで生じる並行処理の問題
  * 何かの情報をチェックして、そのあと、何かしらの実行をする(アクト)というパターン
  * 具体的には競り合い状態、といいます
  */

object CheckThenAct extends App{
  for(i <- 1 to 100){
    new Thread(() => println(SingletonProvider.get)).start()
  }

}

object SingletonProvider{
  private[this] var singleton: BigObject = null
  // this.synchronized を記述することでアトミックな処理として実装される
  // this.synchronized{}構文でthisというインスタンスでロッックを取得
  // ロックとはsの処理が指定したインスタンスで一つしか実行されない様に鍵をかけること
  def get: BigObject = this.synchronized{
    // singleton が null なら BigObjectクラスのインスタンスを生成しフィールドに代入
    if(singleton == null){
      singleton = new BigObject()
    }
    singleton
  }
}

//インスタンス化に1000ミリ秒かかる様設定
class BigObject(){
  Thread.sleep(1000)
}

