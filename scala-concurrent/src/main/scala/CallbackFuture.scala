import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, Promise}
import scala.util.Random


//  doSomething メソッドで成功した時と失敗した時の２つのコールバック関数をを受け取るクラス
class CallbackSomething {
  val random = new Random()

  def doSomething(onSuccess: Int => Unit, onFailure: Throwable => Unit):Unit ={
    val i = random.nextInt(10)
    if(i < 5)onSuccess(i) else onFailure(new RuntimeException(i.toString))
  }
}

// doSomething メソッド内で CallbackクラスのdoSomething メソッドを呼び出し
// Promise に対して、 success と failure メソッドを呼び出し、
// そこから　Future のインスタンスを呼び出している
class FutureSomething{
  val callbackSomething = new CallbackSomething

  def doSomething(): Future[Int] = {
    val promise = Promise[Int]
    callbackSomething.doSomething(i => promise.success(i), t => promise.failure(t))
    promise.future
  }
}

// コールバックを利用するオブジェクト
// CallbackSomething をFuture に変換した物を利用してFuture の合成を行い、その結果を出力する
object CallbackFuture extends App{
  val futureSomething = new FutureSomething

  val iFuture = futureSomething.doSomething()
  val jFuture = futureSomething.doSomething()

  val iplusj = for{
    i <- iFuture
    j <- jFuture
  } yield i + j

  val result = Await.result(iplusj, Duration.Inf)
  println(result)
}
