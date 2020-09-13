package com.blablamower.domain

import org.scalatest.FunSuite
class CoordinatesTest extends FunSuite {
  test("Increment X") {
    val result = Coordinates(5, 3).incrementX()
    val expected = Coordinates(6, 3)
    assertResult(expected)(result)
  }
  test("Decrement X") {
    val result = Coordinates(5, 3).decrementX()
    val expected = Coordinates(4, 3)
    assertResult(expected)(result)
  }
  test("Increment Y") {
    val result = Coordinates(5, 3).incrementY()
    val expected = Coordinates(5, 4)
    assertResult(expected)(result)
  }
  test("Decrement Y") {
    val result = Coordinates(5, 3).decrementY()
    val expected = Coordinates(5, 2)
    assertResult(expected)(result)
  }
}
