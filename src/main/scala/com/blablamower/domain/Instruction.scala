package com.blablamower.domain

/**
 * Enumeration of the possible instructions
 */
object Instruction extends Enumeration {
  type Instruction = Value
  val ROTATE_RIGHT: Value = Value("R")
  val ROTATE_LEFT: Value = Value("L")
  val MOVE_FORWARD: Value = Value("F")

  /**
   * Runs an instruction on a given [[Mower]]
   * @param instruction the [[Instruction]] to execute
   * @param mower the [[Mower]] on which to run the instruction
   * @return a new [[Mower]] instance, built from applying the instruction to the given [[Mower]]
   */
  def run(instruction: Instruction, mower: Mower): Mower = {
    instruction match {
      case ROTATE_RIGHT => mower.rotateRight()
      case ROTATE_LEFT => mower.rotateLeft()
      case MOVE_FORWARD => mower.moveForward()
      case _ => throw new RuntimeException(s"${instruction} is not a valid instruction")
    }
  }
}
