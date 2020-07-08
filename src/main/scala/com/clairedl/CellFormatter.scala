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

  // Gets the cell width for all the lines in the table
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

  protected def whiteSpaceGenerator(cellWidth: Map[String, Int], maxWidth: Map[String, Int]) = {
    // Generates the white spaces that are needed
    def generateWhiteSpace(original: Map[String, Int], comparator: Map[String, Int]): Map[String, String] = {
      // Generates n white spaces
      def whiteSpaces(n: Int): String = (for (i <- 1 to n) yield " ").mkString

      original.flatMap {
          // Matches the columns for both tables and returns the absolute difference between the cells' width
          case (k, v) => comparator.get(k).map(w => Map((k, whiteSpaces((v - w).abs)))).get
        }
    }

    // Adds white spaces to equalise cell width
    def addWhiteSpace(cell: Map[String, String]): Map[String, String] = {
      generateWhiteSpace(getCellWidth(cell), maxWidth)
      cell.flatMap {
        case (k, v) => whiteSpaces.get(k).map(w => Map((k, v + w))).get
      }
    }
  }

  def formatLines(table: List[Map[String, String]]) = {
    val cellWidth = getTableCellWidth(table)
    val maxSize = maxWidth(cellWidth)
    for (line <- table) { println(whiteSpaceGenerator(getCellWidth(line), maxSize)) }
    // table.map(x => whiteSpaceGenerator(getCellWidth(x), maxSize))

  }
}
