import java.util.concurrent.CyclicBarrier
// バリアは、 CyclicBarrier という実装のものが利用されます。
// この部品は、他のスレッドが全員揃ったらスタートできるといういう並行部品です。
// 何かイベントが起きるまでスレッドを待たせるという点に関してはラッチによく似ていますが、全員が揃わないと実行しないという点が異なります。
// ラッチと異なり、リセットをして再度使うことができるようになっています。
// その為、並列反復アルゴリズムの並行処理の実装などに用いられます。
//


object BarrierStudy extends App {

  val barrier = new CyclicBarrier(4, ()=> {
    println("Barrier Action!")
  })

  for ( i <- 1 to 6){
    new Thread(()=>{
      println(s"Thread started. ${i}")
      Thread.sleep(300)
      barrier.await()
      Thread.sleep(300)
      println(s"Thread finished. ${i}")
    }).start()
  }

  Thread.sleep(5000)
  System.exit(0)

}
