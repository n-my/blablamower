package com.blablamower.domain

import org.scalatest.FunSuite

class MowerTest extends FunSuite {
  test("Add instructions") {
    val instructions1 = Seq(Instruction.MOVE_FORWARD, Instruction.MOVE_FORWARD)
    val instructions2 = Seq(Instruction.ROTATE_RIGHT, Instruction.MOVE_FORWARD)
    val mower = Mower(9, 0, Orientation.SOUTH).instructions(instructions1)
    val newMower = mower.instructions(instructions2)
    assertResult(mower.coordinates)(newMower.coordinates)
    assertResult(mower.orientation)(newMower.orientation)
    assertResult(instructions1 ++ instructions2)(newMower.instructions)
  }
  test("Execute the next instruction") {
    val instructions = Seq(Instruction.MOVE_FORWARD, Instruction.ROTATE_LEFT)
    val mower = Mower(0, 4, Orientation.NORTH).instructions(instructions)
    val newMower = mower.executeInstruction().get
    assertResult(mower.coordinates.incrementY())(newMower.coordinates)
    assertResult(mower.orientation)(newMower.orientation)
    assertResult(instructions.drop(1))(newMower.instructions)
  }
  test("Execute the next instruction when no more instructions") {
    val mower = Mower(0, 4, Orientation.NORTH).instructions(Seq())
    val newMower = mower.executeInstruction()
    assert(newMower.isEmpty)
  }
  test("Remove instruction") {
    val instructions = Seq(Instruction.MOVE_FORWARD, Instruction.ROTATE_LEFT)
    val mower = Mower(5, 5, Orientation.SOUTH).instructions(instructions)
    val newMower = mower.removeInstruction()
    assertResult(mower.coordinates)(newMower.coordinates)
    assertResult(mower.orientation)(newMower.orientation)
    assertResult(instructions.drop(1))(newMower.instructions)
  }
  test("Rotate right") {
    val mower = Mower(5, 3, Orientation.WEST)
    val newMower = mower.rotateRight()
    assertResult(mower.coordinates)(newMower.coordinates)
    assertResult(Orientation.NORTH)(newMower.orientation)
    assertResult(mower.instructions)(newMower.instructions)
  }
  test("Rotate left") {
    val mower = Mower(5, 3, Orientation.WEST)
    val newMower = mower.rotateLeft()
    assertResult(mower.coordinates)(newMower.coordinates)
    assertResult(Orientation.SOUTH)(newMower.orientation)
    assertResult(mower.instructions)(newMower.instructions)
  }
  test("Move forward") {
    val mower = Mower(1, 30, Orientation.EAST)
    val newMower = mower.moveForward()
    assertResult(mower.coordinates.incrementX())(newMower.coordinates)
    assertResult(mower.orientation)(newMower.orientation)
    assertResult(mower.instructions)(newMower.instructions)
  }
  test("Get location") {
    val mower = Mower(12, 3, Orientation.SOUTH)
    assertResult("12 3 S")(mower.getLocation)
  }
}
