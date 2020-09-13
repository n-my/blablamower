package com.blablamower.domain

import org.scalatest.FunSuite

class LawnTest extends FunSuite {
  test("Negative X coordinate") {
    assertThrows[java.lang.IllegalArgumentException] {
      Lawn(-1, 3)
    }
  }
  test("Negative Y coordinate") {
    assertThrows[java.lang.IllegalArgumentException] {
      Lawn(30, -1)
    }
  }
  test("Initialize lawn") {
    val lawn = Lawn(15, 16)
    val newLawn = LawnFixtures.initializeLawn(lawn)
    assertResult(lawn.width)(newLawn.width)
    assertResult(lawn.height)(newLawn.height)
    assertResult(LawnFixtures.getMowers)(newLawn.mowers)
  }
  test("Invalid lawn - a mower is initially outside the grid") {
    assertThrows[RuntimeException] {
      LawnFixtures.initializeLawn(Lawn(1, 2))
    }
  }
  test("Execute instructions") {
    val lawn = LawnFixtures.initializeLawn(Lawn(10, 12))
    val newLawn = lawn.execute()
    assertResult(lawn.width)(newLawn.width)
    assertResult(lawn.height)(newLawn.height)
    assertResult(LawnFixtures.getMowers.size)(newLawn.mowers.size)
    assertResult(Coordinates(5, 7))(newLawn.mowers.head.coordinates)
    assertResult(Orientation.EAST)(newLawn.mowers.head.orientation)
  }
  test("Do not move if destination is outside the grid") {
    val lawn = LawnFixtures.initializeLawn(Lawn(4, 10))
    val newLawn = lawn.execute()
    assertResult(Coordinates(4, 7))(newLawn.mowers.head.coordinates)
    assertResult(Orientation.EAST)(newLawn.mowers.head.orientation)
  }
  test("Do not move if destination is not available") {
    val blockingMower = Mower(5, 7, Orientation.NORTH)
    val lawn = LawnFixtures.initializeLawn(Lawn(10, 10)).initialize(Seq(blockingMower))
    val newLawn = lawn.execute()
    assertResult(Coordinates(4, 7))(newLawn.mowers.head.coordinates)
    assertResult(Orientation.EAST)(newLawn.mowers.head.orientation)
  }
}

object LawnFixtures {
  def getMowers: Seq[Mower] = {
    Seq(
      Mower(4, 7, Orientation.SOUTH).instructions(Seq(Instruction.ROTATE_LEFT, Instruction.MOVE_FORWARD)),
      Mower(0, 8, Orientation.NORTH).instructions(Seq(Instruction.MOVE_FORWARD, Instruction.ROTATE_RIGHT))
    )
  }
  def initializeLawn(lawn: Lawn): Lawn = {
    lawn.initialize(getMowers)
  }
}
