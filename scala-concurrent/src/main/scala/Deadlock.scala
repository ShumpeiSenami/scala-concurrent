object Deadlock extends App {

  var now: Long = 0L

  val threadA = new Thread(() => synchronized{
    Thread.sleep(1000)
    now = System.currentTimeMillis()
  })

  val threadB = new Thread(() => synchronized{
    while(now == 0L){
      Thread.sleep(1000)
    }
    println(now)
  })

  threadA.start()
  threadB.start()

}
