package com.lexaux.scala.parser

abstract class Token {

}

case class Number(value: Double) extends Token

case class Func(name: String) extends Token

case class Sign(signType: Char) extends Token

object LeftBracket extends Token

object RightBracket extends Token

object Unknown extends Token
