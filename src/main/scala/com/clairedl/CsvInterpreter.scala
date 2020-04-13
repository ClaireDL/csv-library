package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class CsvReader( 
    delimiter: String = ",", 
    header: Boolean = true
) {

  /**
    * Basic loading function
    */
  def load(file: String): List[List[String]] = {
    Source
      .fromFile(file)
      .getLines()
      .drop(headerMatch())      
      .map { line =>
        val split = line.split(delimiter)
        split.toList
      }
    .toList
  }

  def loadMultiple(files: List[String]): List[List[String]] = {
    var result = new ListBuffer[List[List[String]]].empty
    for (file <- files) {
      val loadedFile = load(file)
      result += loadedFile
    }
    result.toList.flatten
  }

  private def headerMatch(): Int = if (header) 1 else 0

  // def sortBy(columnNumber: Int): List[List[String]] = {

  }
}
