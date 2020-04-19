package com.clairedl.scala

import scala.collection.mutable.ListBuffer
import com.clairedl.scala.CsvReader._
import scala.io.Source
object Main extends App {

  case class Name(phyllum: String, subphyllum: String)
  case class Plant(name: Name, value: Double, alive: Boolean)

  def convertCsvLineToPlant(line: List[String]): Plant = {
    Plant(Name(line(0).toString(), line(1).toString()), line(2).toDouble, line(3).toBoolean)
  }

  def loadConvert[A](filepath: String, converter: List[String] => A): List[A] = {
    Source
      .fromFile(filepath)
      .getLines()
      .drop(1)      
      .map { line =>
        val split = line.split(",")
        converter(split.toList)        
      }
    .toList
  }

  val garden = loadConvert("Plants.csv", convertCsvLineToPlant)

  garden.foreach(println)
}
