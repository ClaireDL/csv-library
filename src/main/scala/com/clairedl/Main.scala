package com.clairedl.scala

import scala.collection.mutable.ListBuffer

object Main extends App {
  // tests loading of a csv file into a known case class
  // val test1 = new UserCsvLoader("User.csv")
  // val loaded1 = test1.load()
  // for (i <- loaded1) {
  //   println(i)
  // }

  println("Testing CSV interpreter functions")
  case class Plants(name: String, value: Double, alive: Boolean) extends CaseClass
  val example = Plants("myPlant", 2.50, true)
  val test3 = CsvInterpreter("Plants.csv", example, ",", true).loadSimple()
  // val test3Parameters = test3.getClassParameters(example)
  // val loaded3 = test3.load()

  val caseClassCsv = {
    val result: ListBuffer[Plants] = ListBuffer()
    for (i <- test3) {
      result += Plants(i(0), i(1).toDouble, i(2).toBoolean)
    }
    result.toList
  }

  for (i<- caseClassCsv) println(i)
}
