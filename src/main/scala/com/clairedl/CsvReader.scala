package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class CsvReader(file: String) {
  /**
    * Basic loading function
    */
  def load(): List[List[String]] = {
    
    // def headerMatch(): Int = if (header) 1 else 0

    Source
      .fromFile(file)
      .getLines()
      .drop(1)      
      .map { line =>
        val split = line.split(",")
        split.toList
      }
    .toList
  }

  val csv = load()

  def convert(myFunc: Any) = {
    for (line <- csv) (myFunc)
  }

  // def convert(caseClass: Any): List[Any] = {
  //   csv.map(x => caseClass)
  // }

  // def convert(file: String, myCaseClass: List[Any]): List[Any] = {
  //   val csv =  load(file)
  //   val result = csv.map(x => myCaseClass)
  //   result
  // }
  
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
}
