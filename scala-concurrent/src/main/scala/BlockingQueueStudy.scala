import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

object BlockingQueueStudy extends App{

  // 10 の容量を持つブロッキングキューを作成
  val blockingQueue = new ArrayBlockingQueue[Runnable](10)
  val finishedCount = new AtomicInteger(0)
  var threads = Seq[Thread]()

  for(i <- 1 to 10){
    val t = new Thread(()=>{
      try{
        // ブロック発生処理
        while(true){
          val runnable = blockingQueue.take()
          runnable.run()
        }
      } catch {
        // インタラプテッドステータスの復元
        case _: InterruptedException =>
      }
    })
    t.start()
    threads = threads :+ t
  }

  for(i <- 1 to 100){
    blockingQueue.put(() => {
      Thread.sleep(1000)
      println(s"Runnnble: ${i} finished.")
      finishedCount.incrementAndGet()
    })
  }

  while(finishedCount.get() != 100)Thread.sleep(1000)
  threads.foreach(_.interrupt())

}
