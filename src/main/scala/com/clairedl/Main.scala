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
  val test3 = CsvInterpreter2("Plants.csv", ",", true)
  val test3Loaded = test3.load()
  println(test3Loaded)
  
  println("testing the type setting function")
  val testingWithTypes = test3.loadWithType(List("String", "Double", "Boolean"))
  println(testingWithTypes)
  val result = testingWithTypes.foreach {
    _ match {
      case List(x: String, y: Double, z: Boolean) => {
        Plants(x, y, z)
        
      }
    }
  }
  println(result)
}
