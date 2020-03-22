package com.clairedl.scala

object Main extends App {
  // tests loading of a csv file into a know case class
  val test1 = new KnownCsvLoader("User.csv")
  val loaded1 = test1.load()
  for (i <- loaded1) {
    println(i)
  }

  // tests functions of CsvInterpreter
  println("Testing CsvInterpreter")
  val test2 = CsvInterpreter("User.csv", ",", false)
  val loaded2 = test2.split()
  for (i <- loaded2) {
    println(i)
  }

  // tests functions of StringConverter
  val test3 = StringConverter.isInt("5")
  println(s"Testing for int $test3")
  val test4 = StringConverter.isBoolean("false")
  println(s"Testing for Boolean $test4")
  val test5 = StringConverter.isDouble("text")
  println(s"Testing for Double $test5")
  val test6 = StringConverter.isDouble("5.32")
  println(s"Testing for Double $test6")
  val test7 = StringConverter.setType2(5.3)
  println(s"Testing for all type tests $test7")
  println(s"$test7 is a " + test7.getClass)

}
