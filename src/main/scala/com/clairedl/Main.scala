package com.clairedl.scala

import scala.collection.mutable.ListBuffer

object Main extends App {
  // tests loading of a csv file into a known case class
  // val test1 = new UserCsvLoader("User.csv")
  // val loaded1 = test1.load()
  // for (i <- loaded1) {
  //   println(i)
  // }

  // tests functions of CsvInterpreter
  // println("Testing CsvInterpreter")
  // val test2 = CsvInterpreter("User.csv", ",", false)
  // val loaded2 = test2.load()
  // for (i <- loaded2) {
  //   println(i)
  // }

  println("Testing CSV interpreter functions")
  case class Plants(name: String, value: Double, alive: Boolean) extends CaseClass
  val example = Plants("myPlant", 2.50, true)
  val test3 = CsvInterpreter("Plants.csv", example)
  println(s"these are the parameters")
  val test3Parameters = test3.getClassParameters(example)

  val loaded3 = test3.load()
  
  println("This should be the csv loaded")
  for (i <- loaded3) {
    println(i)
  }

  // case class Toolbox(numberTools: Int, status: Boolean, value: Double) extends CaseClass

  // val boxClaire = Toolbox(2, false, 20.50)
  // val boxNew = Array(3, true, 40.50)
  // println(boxClaire)
}
