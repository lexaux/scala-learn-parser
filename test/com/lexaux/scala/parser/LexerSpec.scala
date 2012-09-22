package com.lexaux.scala.parser

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class LexerSpec extends FlatSpec with ShouldMatchers {
  "Lexer " should "throw an exception if supplied an empty string" in {
    val lexer = new Lexer("")
    evaluating {
      lexer.tokenize()
    } should produce[LexerException]
  }

  it should "return a stack of single element for simple expression like '1' " in {
    val lexer = new Lexer("1")
    val retStack = lexer.tokenize()
    retStack.size should equal(1)
    retStack.pop() should equal(Number(1))
  }
}
