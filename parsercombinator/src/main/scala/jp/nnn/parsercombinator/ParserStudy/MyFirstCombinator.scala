package jp.nnn.parsercombinator

abstract class MyFirstCombinator{

  sealed trait ParserResult[+T]
  case class Success[+T](value: T, next: String)extends ParserResult[T]
  case object Failure extends ParserResult[Nothing]

  type Parser[+T] = String => ParserResult[T]

  def string(literal: String):Parser[String] = input => {
    if(input.startsWith(literal)){
      Success(literal, input.substring(literal.length))
    } else {
      Failure
    }
  }

  /**
    *  string parser
    *  @param literal 文字列
    *  @return
    */
  def s(literal: String):Parser[String] = string(literal)

  // oneOf は、文字である Char 型のシーケンスを受け取り、
  // input の長さが 0 ではなく、渡された文字のどれかに
  // input の最初の文字が該当していれば、パースを成功とみなし、
  // 最初の文字を結果とする Success のインスタンスを作成
  def oneOf(chars: Seq[Char]):Parser[String] = input => {
    if(input.length != 0 &&
      chars.contains(input.head)){
      Success(input.head.toString, input.tail)
    } else {
      Failure
    }
  }

  def select[T, U>: T](left: => Parser[T],right: => Parser[U]): Parser[U] = input =>{
    left(input) match {
      case success@Success(_, _) => success
      case Failure => right(input)
    }
  }

  // 渡されたふたつのパーサーを左側からひとつずつ適用し、
  // 最終的に両方成功したら、両方の結果をタプルとして返すように実装してあります。
  //またどちらかのパースが失敗した場合は、Failure が返ります。
  def combine[T, U](left: Parser[T], right: Parser[U]): Parser[(T, U)] = input => {
    left(input) match {
      case Success(value1, next1) =>
        right(next1) match {
          case Success(value2, next2) =>
            Success((value1, value2), next2)
          case Failure =>
            Failure
        }
      case Failure =>
        Failure
    }
  }

  def map[T, U](parser: Parser[T], function: T => U): Parser[U] = input => {
    parser(input) match {
      case Success(value, next) => Success(function(value), next)
      case Failure => Failure
    }
  }
}