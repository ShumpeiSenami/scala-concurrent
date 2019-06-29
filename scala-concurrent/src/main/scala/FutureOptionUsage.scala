import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

/**
  *  ３０００ミリ秒以内でランダムに待ち、その待つ時間が１０００ミリ秒以内である場合は例外を投げて処理を失敗させるFutureを持つ
  *  その後、成功している場合（待ち時間＞１０００ミリ秒）なら待った時間を表示
  *  失敗した場合（待ち時間＜＝１０００）なら待ち時間を表示
  */
object FutureOptionUsage extends App {

  val random = new Random()
  val waitMaxMillSec = 3000

  val futureMilliSec: Future[Int] = Future{
    val waitMillSec = random.nextInt(waitMaxMillSec);
    if(waitMillSec < 1000) throw new RuntimeException(s"waitMilliSec is ${waitMillSec}")
    Thread.sleep(waitMillSec)
    waitMillSec
  }

  val futureSec: Future[Double] = futureMilliSec.map(i => i.toDouble / 1000)

  futureSec onComplete{
    case Success(waitSec) => println(s"Success! ${waitSec} sec")
    case Failure(t) => println(s"Failure: ${t.getMessage}")
  }

  Thread.sleep(3000)

}
