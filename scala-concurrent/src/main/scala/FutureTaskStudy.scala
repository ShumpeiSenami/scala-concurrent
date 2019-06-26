import java.util.concurrent.FutureTask

object FutureTaskStudy extends App{

  // 1000 ミリ秒後に文字列を出力し、最後は2525 を返す
  val futureTask = new FutureTask[Int](() =>{
    Thread.sleep(1000)
    println("FutureTask finished")
    2525
  })
  new Thread(futureTask).start()

  new Thread(()=> {
    val result = futureTask.get()
    println(s"result: ${result}")
  }).start()

}
