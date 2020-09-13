package com.blablamower.domain

import com.blablamower.domain.Instruction.Instruction
import com.blablamower.domain.Orientation.Orientation
import com.typesafe.scalalogging.Logger

/**
 * Case class to define a mower
 * @param coordinates the [[Coordinates]] of the mower
 * @param orientation the [[Orientation]] of the mower
 * @param instructions the collection of [[Instruction]] to apply to the mower
 */
case class Mower(coordinates: Coordinates, orientation: Orientation, instructions: Seq[Instruction]) {
  val logger: Logger = Logger(getClass.getName)

  /**
   * Updates the mower's collection of instructions
   * @param instruct the collection of [[Instruction]] to append
   * @return a new instance of [[Mower]] with the updated instructions
   */
  def instructions(instruct: Seq[Instruction]): Mower = {
    this.copy(instructions = instructions ++ instruct)
  }

  /**
   * Executes the mower's first instruction, if any
   * @return a new instance of [[Mower]], built from applying the instruction and then removing it
   */
  def executeInstruction(): Option[Mower] = {
    instructions.headOption
      .map(instruct => Instruction.run(instruct, this))
      .map(mower => mower.removeInstruction())
  }

  /**
   * Remove the mower's first instruction
   * @throws java.lang.UnsupportedOperationException if run on a [[Mower]] that has not instructions
   * @return a new instance of [[Mower]], built from removing the mower's first instruction
   */
  def removeInstruction(): Mower = {
    try {
      this.copy(instructions = instructions.tail)
    } catch {
      case e: java.lang.UnsupportedOperationException => {
        logger.error("Trying to remove an instruction from an empty list of instructions.")
        throw e
      }
    }
  }

  /**
   * Rotates to the right the [[Mower]]
   * @return a new instance of [[Mower]]
   */
  def rotateRight(): Mower = {
    this.copy(orientation = Orientation.rotateRight(orientation))
  }
  /**
   * Rotates to the left the [[Mower]]
   * @return a new instance of [[Mower]]
   */
  def rotateLeft(): Mower = {
    this.copy(orientation = Orientation.rotateLeft(orientation))
  }

  /**
   * Move forward the [[Mower]] depending on its [[Orientation]]
   * Please note that there is no concept of "outside of the grid" at the [[Mower]] level
   * @return a new instance of [[Mower]]
   */
  def moveForward(): Mower = {
    val newCoordinates = orientation match {
      case Orientation.NORTH => coordinates.incrementY()
      case Orientation.EAST => coordinates.incrementX()
      case Orientation.SOUTH => coordinates.decrementY()
      case Orientation.WEST => coordinates.decrementX()
      case _ => coordinates
    }
    this.copy(coordinates = newCoordinates)
  }

  /**
   * Print the [[Mower]]'s coordinates and orientation using the class [[logger]]
   * @return the same instance of [[Mower]]
   */
  def printPosition(): Mower = {
    logger.info(s"${coordinates.x} ${coordinates.y} ${orientation}")
    this
  }

  /**
   * Returns the [[Mower]]'s coordinates and orientation
   * @return the location as a [[String]]
   */
  def getLocation: String = s"${coordinates.x} ${coordinates.y} ${orientation}"
}

/**
 * Companion object of the case class to sugar syntax the creation of a [[Mower]]
 */
object Mower {
  def apply(x: Int, y: Int, orientation: Orientation): Mower = {
    new Mower(Coordinates(x, y), orientation, Seq())
  }
}
