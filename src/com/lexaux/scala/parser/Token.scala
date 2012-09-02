package com.lexaux.scala.parser

import io.Source


abstract class Token {

}

object Tokens {
  def extractNumber(source: Source): Number = {

    def isBoundaryChar(char: Char) = " ()+-*/".contains(char)

    val sb = new StringBuilder(source.ch)
    var dotAppeared = false
    while (!isBoundaryChar(source.next())) {
      if (!"1234567890.".contains(source.ch)) {
        throw new LexerException(source.pos, "Wrong symbol in the number sequence")
      }
      if (source.ch == '.') {
        if (dotAppeared)
          throw new LexerException(source.pos, "Received dot two times. Only once allowed in number notation.")
        else
          dotAppeared = true
      }
      sb.append()
    }
    Number(sb.toDouble)
  }
}

case class Number(value: Double) extends Token {

}

case class Symbol(name: String) extends Token

case class Sign(signType: Char) extends Token

object LeftBracket extends Token

object RightBracket extends Token

object Unknown extends Token
