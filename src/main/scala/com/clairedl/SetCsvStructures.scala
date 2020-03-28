package com.clairedl.scala

import scala.io._

case class User(id: Int, name: String, age: Int, employed: Boolean) {
  override def toString(): String = s"""$id, "$name", $age, $employed"""
}

class CaseClass

object CaseClass