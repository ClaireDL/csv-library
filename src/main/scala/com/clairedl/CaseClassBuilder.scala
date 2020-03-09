package com.clairedl.scala

case class User(
  id: Int,
  name: String,
  age: Int,
  employed: Boolean
) {
  override def toString(): String = s"""$id, "$name", $age, $employed"""
}