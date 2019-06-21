
// １万スレッドを同時に動かすことはできない
// １０００スレッドを１つの目安とする
object TenThousandNamePrinter  extends App {
  //1万スレッドを作成し、それを1000ミリ秒後に自分のスレッド名を出力する
  // for(i <- 1 to 10000){
  for(i <- 1 to 10){
    new Thread(()=> {
      Thread.sleep(1000)
      println(Thread.currentThread().getName)
    }).start()
  }

}
