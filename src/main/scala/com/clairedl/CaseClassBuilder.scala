package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source
import scala.collection.mutable.ListBuffer

case class User(id: Int, name: String, age: Int, employed: Boolean) {
  override def toString(): String = s"""$id, "$name", $age, $employed"""
}

case class DataOrganiser(file: String, delimiter: String = ",", header: Boolean = true) {
  /**
  * Splits csv file into columns and lines, by line
  */
  def split(): List[List[Any]] = {
    Source
      .fromFile(file)
      .getLines()
      .map { line =>
        val split = line.split(delimiter)
        generateOutput(split, split.length)
      }
    .toList
    }

  /**
  * Takes the split csv file and returns a list of lists
  */
  def generateOutput(row: Array[String], length: Int): List[Any] = {
    var output = new ListBuffer[Any]()
    for ( i <- 0 to (length - 1) ) {
      output += StringConverter.setType1(row(i))
    }
    val outputList = output.toList
    outputList
  }
}