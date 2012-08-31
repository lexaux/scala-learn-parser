package com.lexaux.scala.parser

import io.Source

object Main extends App {

  def printUsage() {
    var usageFile = Source.fromInputStream(getClass().getClassLoader.getResourceAsStream("com/lexaux/scala/parser/usage.txt"))
    print(usageFile.mkString)
  }

  def performCalculation(statement: String) {
    printf("Working with arguments: '%s'", statement)
    var l = new Lexer(statement)
    println (l.tokenize().size)
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
