package com.blablamower.domain

import org.scalatest.FunSuite

class OrientationTest extends FunSuite {
  test("Valid orientations by name") {
    assertResult(Orientation.withName("N"))(Orientation.NORTH)
    assertResult(Orientation.withName("W"))(Orientation.WEST)
    assertResult(Orientation.withName("S"))(Orientation.SOUTH)
    assertResult(Orientation.withName("E"))(Orientation.EAST)
  }
  test("Invalid orientation by name") {
    assertThrows[java.util.NoSuchElementException] {
      Orientation.withName("NORTH_POLE")
    }
  }
  test("Rotate right") {
    assertResult(Orientation.rotateRight(Orientation.NORTH))(Orientation.EAST)
    assertResult(Orientation.rotateRight(Orientation.EAST))(Orientation.SOUTH)
    assertResult(Orientation.rotateRight(Orientation.SOUTH))(Orientation.WEST)
    assertResult(Orientation.rotateRight(Orientation.WEST))(Orientation.NORTH)
  }
  test("Rotate left") {
    assertResult(Orientation.rotateLeft(Orientation.NORTH))(Orientation.WEST)
    assertResult(Orientation.rotateLeft(Orientation.WEST))(Orientation.SOUTH)
    assertResult(Orientation.rotateLeft(Orientation.SOUTH))(Orientation.EAST)
    assertResult(Orientation.rotateLeft(Orientation.EAST))(Orientation.NORTH)
  }
}
