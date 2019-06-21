object ThreadStudy extends App {
  //Thread.currentThread() メソッドは、現在の処理が実行しているスレッドのインスタンスを取得するメソッド
  println(Thread.currentThread().getName)

  val thread = new Thread(() => {
    Thread.sleep(1000)
    println(Thread.currentThread().getName)
  })
  thread.start()

  println("main thread finished.")

}
