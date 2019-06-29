import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Promise, Future}
import scala.concurrent.duration._


object PromiseStudy extends App {

  val promiseGetInt: Promise[Int] = Promise[Int]
  val futureByPromise: Future[Int] = promiseGetInt.future // PromiseからFutureを作ることができる

  // Promise が解決された時に実行される処理をFutureを使って書くことができる
  val mappedFuture = futureByPromise.map{ i =>
    println(s"Success! i: ${i}")
  }

  // 別スレッドで何か思い処理をして、終わったら Promise に値を渡す
  Future{
    Thread.sleep(300)
    promiseGetInt.success(1)
  }

  Await.ready(mappedFuture, 5000.millisecond)

}
