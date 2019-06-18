package jp.nnn.parsercombinator

object Booleanparsere extends MyFirstCombinator{

  def trueParser:Parser[Boolean] = map(s("true"), {_: String => true })
  def falseParser: Parser[Boolean] = map(s("false"), {_: String => false })

  def apply(input:String):ParserResult[Boolean] =
    select(trueParser, falseParser)(input)
}