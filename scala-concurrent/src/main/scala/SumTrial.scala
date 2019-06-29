// 1から10000000 までのリストを作成しlist のsumメソッドを用いてその合計を求める
// ※シングルスレッド

object SumTrial extends App{

  val length = 10000000
  val list = (for(i <- 1 to length)yield  i.toLong).toList

  val start = System.currentTimeMillis()
  val sum = list.sum
  val time = System.currentTimeMillis() - start

  println(sum)
  println(s"time ${time} msec")

}
