package com.lexaux.scala.parser

import io.Source
import collection.mutable

object Main extends App {

  def printUsage() {
    var usageFile = Source.fromInputStream(getClass().getClassLoader.getResourceAsStream("com/lexaux/scala/parser/usage.txt"))
    print(usageFile.mkString)
  }

  def printLexerOutput(stack: mutable.Stack[Token]) {
    println("this is the lexer output:")
    for (token <- stack.reverse) {
      println(token)
    }
  }

  def performCalculation(statement: String) {
    printf("Working with arguments: '%s'\n", statement)

    try {
      val l = new Lexer(statement)
      println("Starting lexer job.")
      val tokenizedOutput = l.tokenize()
      println(tokenizedOutput.size)
      printLexerOutput(tokenizedOutput)
    } catch {
      case lx: LexerException => {
        println()
        println("Lexer job failed! See details below")
        println()
        println("    " + statement)
        val sb = new StringBuilder()
        sb.append("    ")
        for (x <- 0 to lx.pos -1 ) {
          sb.append(" ")
        }
        sb.append("^")
        println(sb.toString())
        println(lx.pos)
        println("Reported error: " + lx.message)
      }
    }
  }

  override def main(args: Array[String]) {
    if (args.isEmpty || args.contains("-?")) {
      printUsage()
      return
    }

    var inputString = args(0)
    performCalculation(inputString)
  }
}
