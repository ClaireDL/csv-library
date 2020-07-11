package com.clairedl.scala

import scala.collection.mutable.ListBuffer

object CellFormatter {
  // Get the width of each cell in a line
  protected def getCellWidth(line: Map[String, String]): Map[String, Int] = {
      line.transform((key, value) => value.length().toInt)
  }

  // Get the headers' width
  protected def getHeaderWidth(line: Map[String, String]): Map[String, Int] = {
    line.transform((key, value) => key.length().toInt)
  }

  // Gets the cell width for the whole content in the table
  protected def getTableCellWidth(input: List[Map[String, String]]): List[Map[String, Int]] = {
    // Contains the cell size for each line in the final table
    var result =  new ListBuffer[Map[String, Int]]()
    for (line <- input) { result += getCellWidth(line) }
    result.toList
  }

  // Gets the biggest cell width across each column
  protected def maxWidth(unformattedWidth: List[Map[String, Int]]): Map[String, Int]  = {
    unformattedWidth
      // .flatten
      // .groupMap(_._1)(_._2)
      // .map(x => x._2.max)
      .flatten
      .groupBy(_._1)
      .map(x => x._2.max)
      .toMap
  }

  // Generates a String with the same repeated character
  protected def generateCharacter(n: Int, c: Char): String = (for (i <- 1 to n) yield c).mkString

  // Adds white spaces to equalise cell width
  protected def addWhiteSpace(cell: Map[String, String], maxWidth: Map[String, Int]): Map[String, String] = {
    val columnWidth = getCellWidth(cell)

    val whiteSpaces = columnWidth.flatMap {
        // Matches the columns for both tables and returns the absolute difference between the cells' width
        case (k, v) => maxWidth.get(k).map(w => Map((k, generateCharacter((v - w).abs, ' ')))).get
      }

    cell.flatMap {
      case (k, v) => whiteSpaces.get(k).map(w => Map((k, v + w))).get
    }
  }

  /**
  * Formats input so that content in each column has same width
  */
  def formatInput(table: List[Map[String, String]]): List[Map[String, String]] = {
    val originalWidth = getTableCellWidth(table)
    val toMatch = maxWidth(originalWidth)
    table.map{x => addWhiteSpace(x, toMatch)}
  }
}
