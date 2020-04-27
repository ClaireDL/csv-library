package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source
import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object CsvReader {
  abstract class Converter {
    def convert(line: List[String]): Any
  }
  
  def loadConvert(filepath: String, converter: Converter): List[Any] = {
    Source
      .fromFile(filepath)
      .getLines()
      .drop(1)      
      .map { line =>
        val split = line.split(",")
        converter.convert(split.toList)        
      }
    .toList
  }

  // Solution with functional programming
  // def loadConvert[A](filepath: String, converter: List[String] => A): List[A] = {
  //   Source
  //     .fromFile(filepath)
  //     .getLines()
  //     .drop(1)      
  //     .map { line =>
  //       val split = line.split(",")
  //       converter(split.toList)        
  //     }
  //   .toList
  // }
}
