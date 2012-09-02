package com.lexaux.scala.parser

import io.Source

object Main extends App {

  def printUsage() {
    var usageFile = Source.fromInputStream(getClass().getClassLoader.getResourceAsStream("com/lexaux/scala/parser/usage.txt"))
    print(usageFile.mkString)
  }

  def performCalculation(statement: String) {
    printf("Working with arguments: '%s'\n", statement)

    try {
      val l = new Lexer(statement)
      println("Starting lexer job.")
      println(l.tokenize().size)
    } catch {
      case lx: LexerException => {
        println
        println("Lexer job failed! See details below")
        println("    " + statement)
        println("    " + (" " * (lx.pos - 1))) + "^^^"
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
