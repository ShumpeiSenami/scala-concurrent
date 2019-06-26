object MemoryVisibility extends App {
 @volatile var number = 0
 @volatile var ready = false

  new Thread(()=> {
    while(!ready){
      Thread.`yield`()
    }
    println(number)
  }).start()
// マルチスレッドでは変数の更新順が変わることがあり、それを「順序変え（reordering)]という
  //rady が先に　true になると　number が 0 のまま返えしてしまうことがある
  number = 2525
  ready = true

}

 //object MemoryVisibility extends App {
 //var ready = false
 //private[this] def getNumber: Int = synchronized { number }
 //private[this] def getReady: Boolean = synchronized { ready }

 // new Thread(() => {
 //   while (!getReady) {
 //    Thread.`yield`()
 //   }
 //   println(getNumber)
 // }).start()

  //synchronized {
  //number = 2525
  //  ready = true
  //}
  //}
  // var number = 0
