package com.lexaux.scala.parser

import org.scalatest.PropSpec
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.matchers.ShouldMatchers

/**
  */
class LexerMatrixTest extends PropSpec with TableDrivenPropertyChecks with ShouldMatchers {

  val validExamples =
    Table(
      "Heading",
      "22+3",
      "+33",
      "sin(x)",
      "77+(a(2))"
    )


  property("invoking lexer on correct expression should not rise any exceptions") {
    forAll(validExamples) {
      input => {
        val v = new Lexer(input)
        v.tokenize().size should be > (0)
      }
    }
  }
}
