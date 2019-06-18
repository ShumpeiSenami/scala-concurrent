package jp.nnn.parsercombinator.ParserStudy

object ParserStudy {

  sealed trait ParseResult[+T]
  case class Success[+T](value: T, next: String) extends ParseResult[T]
  case object Failure extends ParseResult[Nothing]

  type Parser[+T] = String => ParseResult[T]

  def trueParser: Parser[Boolean] = input =>
    if (input.startsWith("true")){
      Success(true, input.substring("true".length))
    } else {
      Failure
    }

  def falseParser: Parser[Boolean] = input =>
    if(input.startsWith("false")){
      Success(false, input.substring("false".length))
    } else{
      Failure
    }
  // trueParser と　falseParserの合成（パーサーコンビネーター)
  def booleanParser: Parser[Boolean] = input =>
    trueParser(input) match{
        //trueParser でパースして結果がSuccessであればそのまま返す、失敗なら続いてfalseParserで試みる
      case success@Success(_, _) => success
      case Failure => falseParser(input)
    }

}
