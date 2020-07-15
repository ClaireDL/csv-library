package com.clairedl.scala

import scala.io.Source
import com.clairedl.scala.CsvReader._
import scala.util.Random._
import scala.collection.mutable
import com.clairedl.scala.TableFormatter._

object Main extends App {

  case class Name(phyllum: String, subphyllum: String)
  case class Plant(name: Name, value: Double, alive: Boolean)
  class ConvertCsvLineToPlant extends Converter[Plant] {
    def convert(line: List[String]): Plant = {
      Plant(Name(line(0), line(1)), line(2).toDouble, line(3).toBoolean)
    }
  }

  val converter = new ConvertCsvLineToPlant
  val garden = loadConvert("Plants.csv", converter)
  // garden.foreach(println)
}
