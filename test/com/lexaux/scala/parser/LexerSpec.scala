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

  it should "distinguish numbers which are integer and floating point" in {
    val lexer = new Lexer("11+22.3")
    val retStack = lexer.tokenize()
    retStack.size should equal(3)
    retStack.pop() should equal(Number(11))
    retStack.pop()
    retStack.pop() should equal(Number(22.3))
  }

  it should "throw LexerException if found 2 dots in a number" in {
    val lexer = new Lexer("222.3.3")
    evaluating(lexer.tokenize()) should produce[LexerException]
  }

  it should "throw LexerException if the identifier is starting with the digit (or a number has a symbol)" in {
    val lexer = new Lexer("123a")
    evaluating(lexer.tokenize()) should produce[LexerException]
  }

  it should "understand signs of all the operations +-/*" in {
    val lexer = new Lexer("+-*/")
    lexer.tokenize().size should equal(4)
  }

  it should "throw LexerException with the correct position if found wront symbol" in {
    val lexer = new Lexer("12+33+4~1")
    try {
      lexer.tokenize()
    } catch {
      case lx: LexerException => {
        lx.pos should equal(7)
      }
      case _ => fail("nothing here")
    }
  }


}
