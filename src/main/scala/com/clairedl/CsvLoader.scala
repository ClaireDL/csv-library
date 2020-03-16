package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source

class KnownCsvLoader(file: String, header: Boolean = true, delimiter: String = ",") {
  def load(): List[Any] = {
     Source
      .fromFile(file)
      .getLines()      
      .drop(headerMatch())
      .map { line =>
        val split = line.split(delimiter)
        User(split(0).toInt, split(1), split(2).toInt, split(3).toBoolean)
      }
    .toList
    }

  private def headerMatch(): Int = {
    header match {
      case true   => 1
      case false  => 0
    }
  } 
}


