package com.clairedl.scala

import scala.util.matching.Regex

object StringConverter {
  def setType2(s: Any) = {
    /**
    * Returns a boolean for strings with true or false 
    * Returns a quoted string otherwise
    */
    def testString(s: Any) = {
      val sLower = s.toString.trim().toLowerCase()
      sLower match {
        case "true" | "false" => sLower.toBoolean
        case _                => s""" "$s" """.trim()
      }
    }
    
    s match {
      case s: Int     => s.toInt
      case s: Double  => s.toDouble
      case s: Float   => s.toFloat
      case s: Char    => s.toChar
      case _          => testString(s)       
    }
  }

  def setType1(s: String) = {
    isInt(s).orElse(isDouble(s)).orElse(isBoolean(s)) match {
      case Some(i)  => i
      case None     => s""" "$s" """.trim
    }
  }

  /**
  * Test if input is an Int
  */
  def isInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case _ : Throwable => None
    }
  }

  /**
  * Tests if input is a Double
  */
  def isDouble(s: String): Option[Double] = {
    try {
      Some(s.toDouble)
    } catch {
      case _ : Throwable => None
    }
  }

  /**
  * Tests if input is a Boolean
  */
  def isBoolean(s: String): Option[Boolean] = {
    try {
      Some(s.toLowerCase.toBoolean)
    } catch {
      case _ : Throwable => None
    }
  }
}