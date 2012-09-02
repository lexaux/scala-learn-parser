package com.lexaux.scala.parser

import collection.mutable
import io.Source

class LexerException(val pos: Int, val message: String) extends Exception

/**
 */
class Lexer(s: String) {

  var lexerSource: Source = Source.fromString(s)

  def isSpace(c: Char): Boolean = " \\t\\n\\f" contains c

  def getNextToken(): Token = {

    while (isSpace(lexerSource.ch)) {
      lexerSource.next()
    }

    val DigitPattern = """([0123456789])""".r
    val LetterPattern = """([a-zA-Z])""".r
    val SignPattern = """\+-/\*""".r
    val char = lexerSource.ch
    char.toString match {
      case ")" => {
        lexerSource.next()
        RightBracket
      }

      case "(" => {
        lexerSource.next()
        LeftBracket
      }

      case SignPattern(char) => {
        lexerSource.next()
        Sign(lexerSource.ch)
      }

      case DigitPattern(char) => {
        Tokens.extractNumber(lexerSource)
      }
      case LetterPattern(char) => {
        lexerSource.next()
        Unknown
      }
      case _ => {
        lexerSource.next()
        Unknown
      }
    }
  }


  def tokenize(): mutable.Stack[Token] = {
    val tokenStack = new mutable.Stack[Token]
    lexerSource.next()
    do {
      tokenStack.push(getNextToken())
    } while (lexerSource.hasNext)

    tokenStack
  }
}
