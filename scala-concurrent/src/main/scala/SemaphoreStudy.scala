import java.util.concurrent.Semaphore

// Semaphore は、何かのリソースにアクセスさせる際にその数を制限したい時に用いる並行処理部品
object SemaphoreStudy extends App{
  val semaphore = new Semaphore(3)

  for(i <- 1 to 100){
    new Thread(()=> {
      try{
        semaphore.acquire()
        Thread.sleep(3000)
        println(s"Thread finished. ${i}")
      } finally{
        semaphore.release()
      }
    }).start()
  }

}
