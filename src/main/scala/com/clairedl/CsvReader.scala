package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object CsvReader {
  /**
    * Basic loading function
    */
  def load(file: String, delimiter: String = ",", header: Boolean = true): List[List[String]] = {
    
    def headerMatch(): Int = if (header) 1 else 0

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

  /**
    * Loads multiple files of the same format
    */
  // def loadMultiple(files: List[String]): List[List[String]] = {
  //   var result = new ListBuffer[List[List[String]]].empty
  //   for (file <- files) {
  //     val loadedFile = load(file)
  //     result += loadedFile
  //   }
  //   result.toList.flatten
  // }

  def withQuotes(s: String): String = {s""" "${s}" """.trim()}

  // val testMultiple = test.loadMultiple(List("Plants.csv", "Plants2.csv"))

  // for (i <- testMultiple) {
  //   garden += 
  // }
  // println(garden.toList)

}
