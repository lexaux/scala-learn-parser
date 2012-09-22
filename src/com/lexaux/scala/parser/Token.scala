package com.lexaux.scala.parser

import io.Source


abstract class Token {

}

case class Number(value: Double) extends Token {
  override def toString = "NUMBER(" + value + ")"
}

case class Symbol(name: String) extends Token {
  override def toString = "SYMBOL(" + name + ")"
}

case class Sign(signType: Char) extends Token {
  override def toString = "SIGN(" + signType + ")"
}

object LeftBracket extends Token {
  override def toString = "LBRACKET"
}

object RightBracket extends Token {
  override def toString = "RBRACKET"
}

object Unknown extends Token {
  override def toString = "UNKNOWN!"
}
