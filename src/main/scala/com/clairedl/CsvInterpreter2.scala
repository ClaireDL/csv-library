package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.mutable

case class CsvInterpreter2(
    file: String, 
    delimiter: String = ",", 
    header: Boolean = true
    ) {

  /**
  * Base loading function
  */
  def load(): List[List[String]] = {
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
  * Loads csv file as a list of user defined types
  */
  def loadWithType(parameterTypes: List[String]) = {
    val csv = load()
    // csv.foreach(_.foreach(println(_)))
    Source
      .fromFile(file)
      .getLines()
      .drop(headerMatch())      
      .map { line =>
        val split = line.split(delimiter)
        var result = (split.zip(parameterTypes)).map { case (s, p) => setTypeField(s, p)}
        result.toList
      }
    .toList
  }

  private def headerMatch(): Int = if (header) 1 else 0

  /**
  * Returns field of csv line in the correct type
  */
  def generateOutputMap(parameterTypes: Array[String], row: Array[String]) = { 
    val typeAndField = parameterTypes.zip(row).toMap
    typeAndField.transform((key, value) => setTypeField(value, key))
  }

  /**
  * Gets name and type of case class parameters44
  */
  def getClassParameters(instance: CaseClass) = {
    val parameterNames = getNames(instance)
    val parameterTypes = getTypes(instance)
    parameterNames.zip(parameterTypes).toMap
  }

  /**
  * Gets type of the case class parameters
  */
  def getTypes(instance: CaseClass): Array[String] = 
    instance.getClass().getDeclaredFields().map(_.getName)
  
    /**
  * Gets name of the case class parameters
  */
  def getNames(instance: CaseClass): Array[String] = 
    instance.getClass().getDeclaredFields().map(_.getName)

  def setTypeField(cell: String, finalType: String): Any = {
    finalType match {
      case "Int"     => cell.toInt
      case "Double"  => cell.toDouble
      case "Boolean" => cell.toBoolean
      case "Float"   => cell.toFloat
      case "String"  => s""" "$cell" """.trim()
      case _         => cell
    }
  }
}
