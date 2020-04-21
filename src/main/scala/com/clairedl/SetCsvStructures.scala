package com.clairedl.scala

import scala.io._

case class User(id: Int, name: String, age: Int, employed: Boolean) {
  override def toString(): String = s"""$id, "$name", $age, $employed"""
}

case class Name2(phyllum: String, subphyllum: String)
case class PlantPreDef(name: Name2, value: Double, alive: Boolean)