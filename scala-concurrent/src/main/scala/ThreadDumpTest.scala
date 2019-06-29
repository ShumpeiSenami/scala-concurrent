import java.time
import java.time.LocalDateTime
import java.util.concurrent.{Executors, TimeUnit}


object ThreadDumpTest extends App{

  val es = Executors.newScheduledThreadPool(3)
  es.scheduleAtFixedRate(()=> {
    println(s"ThreadNmae:${Thread.currentThread().getName}time.LocalDateTime:${LocalDateTime.now()}")
  }, 0L, 1L, TimeUnit.SECONDS)

}
