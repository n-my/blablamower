package com.blablamower.domain

import org.scalatest.FunSuite

class InstructionTest extends FunSuite {
  test("Valid instructions by name") {
    assertResult(Instruction.withName("R"))(Instruction.ROTATE_RIGHT)
    assertResult(Instruction.withName("L"))(Instruction.ROTATE_LEFT)
    assertResult(Instruction.withName("F"))(Instruction.MOVE_FORWARD)
  }
  test("Invalid instruction by name") {
    assertThrows[java.util.NoSuchElementException] {
      Instruction.withName("BACK_FLIP")
    }
  }
  test("Run an instruction") {
    val result = Instruction.run(Instruction.ROTATE_RIGHT, Mower(3, 5, Orientation.SOUTH))
    val expected = Mower(3, 5, Orientation.WEST)
    assertResult(expected)(result)
  }
}
