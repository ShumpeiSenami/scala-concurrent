import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.postfixOps
import scala.util.{Failure, Success, Random}

// 3000ミリ秒以内でランダムに待ち、　first,secondどちらも500ミリ秒以上待てばsuccessを返す
// どちらかでも　５００ミリ秒未満しか待たなければ例外を投げ失敗、Failure を返す
object CompositeFuture extends App {

  val random = new Random()
  val waitMaxMilliSec = 3000

  def waitRandom(futureName:String):Int ={
    val waitMilliSec = random.nextInt(waitMaxMilliSec);
    if(waitMilliSec < 500) throw new RuntimeException(s"${futureName} waitMillSec is ${waitMilliSec}")
    Thread.sleep(waitMilliSec)
    waitMilliSec
  }
  val futureFirst: Future[Int] = Future{
    waitRandom("first")
  }
  val futureSecond: Future[Int] = Future{
    waitRandom("second")
  }
 // for 式で合成された Future のインスタンスを取得する部分
  // これによってfutureFirst と futureSecond という２つの　Future を合成したFuture を得ることが可能
  val compositeFuture:Future[(Int, Int)] = for{
    first <- futureFirst
    second <- futureSecond
  } yield(first,second)
  // 上記で合成した Future インスタンスをOption型で利用可能
  compositeFuture onComplete{
    case Success((first, second)) => println(s"Success! first:${first} second:${second}")
    case Failure(t) => println(s"Failure: ${t.getMessage}")
  }

  Thread.sleep(5000)

}
