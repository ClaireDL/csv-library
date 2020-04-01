package com.clairedl.scala

import scala.collection.mutable.ListBuffer

object Main extends App {
  case class Plants(name: String, value: Double, alive: Boolean) extends CaseClass
  val test = CsvInterpreter("Plants.csv")
  
  val testWithTypes = test.loadWithType(List("String", "Double", "Boolean"))
  var result1 = new ListBuffer[Plants]()
  for (i <- testWithTypes) {
    // println(i(0).getClass(), i(1).getClass(), i(2).getClass())
    result1 += Plants(i(0).toString(), i(1).toString().toDouble, i(2).toString().toBoolean)
  }
  println(result1.toList)

  val testWithoutTypes = test.load()
  var result2 = new ListBuffer[Plants]()
  for (i <- testWithoutTypes) {
    result2 += Plants(i(0).toString(), i(1).toDouble, i(2).toBoolean)
  }
  println(result2.toList)

  case class Music(title: String, band: String, cd: Boolean, songs: Int)
  val collection = CsvInterpreter("test2.csv").load()
  var collectionCC = new ListBuffer[Music]
  for (i <- collection) {
    collectionCC += Music(s""" "${i(0)}" """.trim(), i(1), i(2).toBoolean, i(3).toInt)
  }
  println(collectionCC.toList)
}
