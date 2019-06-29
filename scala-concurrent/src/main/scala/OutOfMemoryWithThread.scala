
// 実行すると OutOfMemoryError 発生する
// スレッドは数千個が限界でスレッドを１０万作成が原因
import java.util.concurrent.atomic.AtomicInteger

object OutOfMemoryWithThread {

  val counter = new AtomicInteger(0)

  while (true) {
    new Thread(() => {
      println(counter.incrementAndGet())
      Thread.sleep(100000)
    }).start()
  }
}
