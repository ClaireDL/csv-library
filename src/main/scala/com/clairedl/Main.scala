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

  //
  // Working on transforming List of case classes into a table
  //
  val herbarium = List(
    Plant(Name("Dianthus", "caryophyllus"), 2.50, true),
    Plant(Name("Rosa","Damascena"), 10, false),
    Plant(Name("Iris","germanica"), 3, true)
  )

  class ConvertPlantToString extends CaseClassConverter[Plant]{
    def convert(plant: Plant) = {
      val phyllum = plant.name.phyllum
      val subphyllum = plant.name.subphyllum
      val value = plant.value.toString()
      val alive = plant.alive.toString()

      Map(("phyllum", phyllum), ("subphyllum", subphyllum), ("value", value), ("alive", alive))
    }
  }

  val converter2 = new ConvertPlantToString
  val herbariumTable = convertToTable(herbarium, converter2)
  println("This is the original list")
  println(herbariumTable.mkString("\n"))

  // Working with formatter
  println("Working with formatter")
  val table = formatAsTable(herbariumTable)
  // println(table.mkString("\n"))
  println(table)
}
