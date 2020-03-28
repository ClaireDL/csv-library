package com.clairedl.scala

import scala.io._
import _root_.scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.mutable

case class CsvInterpreter(
    file: String, 
    example: CaseClass, 
    delimiter: String = ",", 
    header: Boolean = true
    ) {
  /**
  * Loads csv files as a list of case class defined by the user
  */
  def load(): List[Any] = {
    Source
      .fromFile(file)
      .getLines()
      .drop(headerMatch())      
      .map { line =>
        val split = line.split(delimiter)
        println(s"This is split: ${split.toList}")
        val output = generateOutputMap(getTypes(example), split)
        output
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

  def setTypeField(cell: String, finalType: Any): Any = {
    val lowerCase = cell.toLowerCase()

    finalType match {
      case finalType: Int     => cell.toInt
      case finalType: Double  => cell.toDouble
      case finalType: Boolean => lowerCase.toBoolean
      case finalType: Float   => cell.toFloat
      case finalType: String  => s""" "$cell" """.trim()
      case finalType: Any     => cell
    }
  }
}
