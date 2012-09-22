package com.lexaux.scala.parser

import collection.mutable
import io.Source

class LexerException(val pos: Int, val message: String) extends Exception

/**
  */
class Lexer(inputString: String) {

  var inputStringPosition = 0

  // assume we have a 10 char string. Also, index is zero-based. So, for the case of our string the last index which
  // 'hasNext' would return true would be 8. 9 is the last one, and so has no next.
  def hasNext = inputStringPosition < inputString.length - 1

  def currentChar = inputString.charAt(inputStringPosition)

  def nextChar(): Char = {
    if (hasNext) {
      inputStringPosition += 1
    }
    currentChar
  }

  def prevChar() {
    if (inputStringPosition == 0) {
      throw new IllegalArgumentException
    } else {
      inputStringPosition -= 1
    }
  }

  def isSpace(c: Char): Boolean = """\t\r\n\f """ contains c

  val DigitPattern = """([0123456789])""".r

  val LetterPattern = """([a-zA-Z])""".r

  val SignPattern = """([\+-/\*])""".r

  def extractIdentifier(source: Source): Token = Unknown

  def extractNumber(): Number = {
    val allowedNumberChars = "1234567890."
    val allowedBoundaryChars = "()-+=*/ "
    def isBoundaryChar(char: Char) = allowedBoundaryChars.contains(char)

    // assuming that the first char here is a digit if we finally came here?
    val sb = new StringBuilder()
    sb.append(currentChar)
    var dotAppeared = false

    while (hasNext && !isBoundaryChar(nextChar())) {
      if (!allowedNumberChars.contains(currentChar)) {
        throw new LexerException(inputStringPosition, "Wrong symbol in the number sequence")
      }
      if (currentChar == '.') {
        if (dotAppeared)
          throw new LexerException(inputStringPosition, "Received dot two times. Only once allowed in number notation.")
        else
          dotAppeared = true
      }
      sb.append(currentChar)
    }
    // rewind back by one if this was boundary
    Number(sb.toDouble)
  }

  def tokenize(): mutable.Stack[Token] = {
    val tokenStack = new mutable.Stack[Token]
    if (inputString.length == 0) {
      throw new LexerException(0, "Empty statement")
    }

    // the first thing.
    do {
      while (hasNext && isSpace(currentChar)) {
        nextChar()
      }

      currentChar.toString match {
        case ")" => {
          tokenStack.push(RightBracket)
          nextChar()
        }

        case "(" => {
          tokenStack.push(LeftBracket)
          nextChar()
        }

        case SignPattern(char) => {
          tokenStack.push(Sign(currentChar))
          nextChar()
        }

        case DigitPattern(char) => {
          // it should advance iterator itself
          tokenStack.push(extractNumber())
        }

        case LetterPattern(char) => {
          tokenStack.push(Unknown)
        }
        case _ => {
          throw new LexerException(inputStringPosition, "Unknown symbol. Lexing failed.")
        }
      }
    } while (hasNext)
    tokenStack
  }
}
