package com.clairedl.scala

import scala.util.matching.Regex

object StringConverter {
  
  def testType(s: String) = {
    isInt(s).orElse(isDouble(s)).orElse(isBoolean(s)) match {
      case Some(i)  => i
      case None     => s
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