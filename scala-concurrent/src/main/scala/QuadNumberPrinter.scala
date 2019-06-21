object QuadNumberPrinter extends App {
  //スレッド数を４とする
  for(i <- 1 to 4){
    new Thread(() => for(j <- 1 to 10 ) println(s"thread ${i}: ${j}")).start()
  }

}
