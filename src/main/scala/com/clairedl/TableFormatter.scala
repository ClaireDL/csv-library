package com.clairedl.scala

import scala.collection.mutable.ListBuffer
import com.clairedl.scala.CellFormatter._

/**
* After converting a list of case classes to strings, the program allows to format the input as a table
*/
object TableFormatter {
  abstract class CaseClassConverter[A] {
    def convert(input: A): Map[String, String]
  }

  def convertToTable[A](input: List[A], converter: CaseClassConverter[A]): List[Map[String, String]] = {
    input.map(x => converter.convert(x))
  }

  def formatAsTable(input: List[Map[String, String]]): List[Map[String, String]] = {
    // Step 1: adds header
    val header = input(0).transform((key, value) => key.toUpperCase())
    val unformattedTable = header :: input
    // Step 2: add to each cell the necessary white spaces to match max cell size
    formatInput(unformattedTable)
  }
}
