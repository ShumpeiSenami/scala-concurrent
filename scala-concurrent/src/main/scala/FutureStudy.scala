import scala.concurrent.Future

// 関数型プログラミングとマルチスレッドの平行処理プログラミングを暗黙的に結びつけてくれる implicit メソッド
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

object FutureStudy extends App {

  val s = "Hello"
  val f:Future[String] = Future{
    // 1000ミリ秒待機し　”Hello"　と　”future!" を文字列結合処理する
    Thread.sleep(1000)
    println(s"[ThreadName] In Future: ${Thread.currentThread.getName}")
    s + " future! "
  }
  // future で現れる中身の要素をパターンマッチし得られる結果が String型であれば
  // コンソールに出力するという関数を適用する
  f.foreach{ case s: String =>
    println(s"[ThreadName] In Success: ${Thread.currentThread.getName}")
    println(s)
  }

  println(f.isCompleted)  //false

  Await.ready(f, 5000 millisecond) // Hello future!

  println(s"[ThreadName] In App: ${Thread.currentThread.getName}")
  println(f.isCompleted)  // true

  val f2: Future[String] = Future{
    Thread.sleep(1000)
    throw new RuntimeException("わざと失敗")
  }

  f2.failed.foreach{
    case e: Throwable =>
      println(e.getMessage)
  }

  println(f2.isCompleted)  //false
  Thread.sleep(5000)  // わざと失敗
  println(f2.isCompleted)  //true

}
