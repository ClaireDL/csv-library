package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source

/**
  * Predefined case class to load a csv file as a list of Case classes
  */
class UserCsvLoader(file: String, header: Boolean = true, delimiter: String = ",") {
  def load(): List[Any] = {
     Source
      .fromFile(file)
      .getLines()      
      .drop(headerMatch())
      .map { line =>
        val split = line.split(delimiter)
        User(split(0).toInt, split(1), split(2).toInt, split(3).toLowerCase().toBoolean)
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


