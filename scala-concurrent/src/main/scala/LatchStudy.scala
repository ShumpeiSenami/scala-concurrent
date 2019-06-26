import java.util.concurrent.CountDownLatch

object LatchStudy extends App{
  val latch = new CountDownLatch(3)

  for( i <- 1 to 3){
    new Thread(()=> {
      println(s"Finished and countDown! ${i}")
      // coundDown メソッドでラッチのカウントを下げる（この場合３つのスレッドの仕事）
      latch.countDown()
    }).start()
  }

  new Thread(()=>{
    // awaitメソッドでラッチのカウントが０になると実行される
    latch.await()
    println("All tasks finished.")
  }).start()
}
