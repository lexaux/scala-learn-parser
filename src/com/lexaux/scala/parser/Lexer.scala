package com.lexaux.scala.parser

import collection.mutable
import io.Source

/**
 */
class Lexer(s: String) {

  var lexerSource = Source.fromString(s)

  def hasNextToken(): Boolean = lexerSource.hasNext

  def isSpace(c: Char): Boolean = " \\t\\n\\f" contains c

  def getNextToken(): Token = {
    while(isSpace(lexerSource.ch)) {
      lexerSource.next()
    }

    new Token()
  }



  def tokenize(): mutable.Stack[Token] = {
    val resultStack = new mutable.Stack[Token]()

    while (hasNextToken()) {
      resultStack.push(getNextToken())
    }
    resultStack
  }
}
