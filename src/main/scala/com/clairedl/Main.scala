package com.clairedl.scala

object Main extends App {
  val test1 = new CsvLoader("test1.csv", true)
  val loaded = test1.load()
  // println(loaded)
  for (i <- loaded) {
    println(i)
  }
}
