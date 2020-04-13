package com.clairedl.scala

import scala.collection.mutable.ListBuffer
object Main extends App {
  case class Plants(name: String, value: Double, alive: Boolean)
  val test = new CsvReader
  
  // val testLoad = test.load("Plants.csv")
  // var result2 = new ListBuffer[Plants].empty
  // for (i <- testLoad) {
  //   result2 += Plants(i(0).toString(), i(1).toDouble, i(2).toBoolean)
  // }
  // println(result2.toList)

  val testMultiple = test.loadMultiple(List("Plants.csv", "Plants2.csv"))
  var result3 = new ListBuffer[Plants].empty
  for (i <- testMultiple) {
    result3 += Plants(i(0).toString(), i(1).toDouble, i(2).toBoolean)
  }
  println(result3.toList)

  // case class Music(title: String, band: String, cd: Boolean, songs: Int)
  // val collection = new CsvReader
  // val collectionLoad = collection.load("test2.csv")
  // var collectionToCC = new ListBuffer[Music]
  // for (i <- collectionLoad) {
  //   collectionToCC += Music(s""" "${i(0)}" """.trim(), i(1), i(2).toBoolean, i(3).toInt)
  // }
  // println(collectionToCC.toList)
  // val collectionCC = collectionToCC.toList
  
  // println(collectionToCC(1).getClass())
}
