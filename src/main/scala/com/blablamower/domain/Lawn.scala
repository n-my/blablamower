package com.blablamower.domain

import com.typesafe.scalalogging.Logger

/**
 * Case class to define a lawn
 * @param width the width of the lawn
 * @param height the height of the lawn
 * @param mowers the mowers on the lawn
 */
case class Lawn(width: Int, height: Int, mowers: Seq[Mower]) {
  val logger: Logger = Logger(getClass.getName)

  /**
   * Initializes a [[Lawn]] with a collection of [[Mower]]
   * @param mowers the mowers to add to the lawn
   * @throws RuntimeException if one of the mower's coordinates is outside the grid
   * @return a new instance of [[Lawn]], built from adding the mowers to the lawn
   */
  def initialize(mowers: Seq[Mower]): Lawn = {
    mowers.foreach(mower => {
      if (!this.withinGrid(mower.coordinates)) {
        throw new RuntimeException("Cannot initialize a mower outside the grid.")
      }
    })
    this.copy(mowers = (this.mowers ++ mowers))
  }

  /**
   * Executes all the [[Mower]] instructions, one [[Mower]] at a time.
   * When all the instructions of a mower are executed, it prints the final position of this mower.
   * @return a new instance of [[Lawn]]
   */
  def execute(): Lawn = {
    mowers.foldLeft(this)(
      (lawn, mower) => {
        logger.debug(mower.getLocation)
        val (lawnResult, mowerResult) = lawn.executeInstructions(mower)
        mowerResult.printPosition()
        lawnResult
      }
    )
  }

  /**
   * Recursively executes all the instructions of a given [[Mower]]
   * @param mower the mower for which instructions are executed
   * @return a [[Tuple2]] of a new instance of [[Lawn]], and the [[Mower]]
   */
  private def executeInstructions(mower: Mower): (Lawn, Mower) = {
    mower.executeInstruction()
      .map(m => move(mower, m))
      .map{ case (l, m) => {
        logger.debug(s"Mower at position ${m.getLocation}")
        l.executeInstructions(m)
      }}
      .getOrElse((this, mower))
  }

  /**
   * Moves a [[Mower]] from a source to a target
   * This is where the checks are made to make sure the move is valid on this lawn.
   * If the target is outside of the grid, or already occupied by another [[Mower]]
   * then the move is discarded.
   * @param source the [[Mower]] to move
   * @param target the [[Mower]] once the move is made
   * @return a [[Tuple2]] of a new instance of [[Lawn]], and the [[Mower]]
   */
  private def move(source: Mower, target: Mower): (Lawn, Mower) = {
    val mower: Mower = if (withinGrid(target.coordinates) && coordinatesAvailable(source, target.coordinates)) {
      target
    } else {
      source.removeInstruction()
    }
    val sourceIndex = mowers.indexWhere( _ == source )
    val updatedMowers = mowers.updated(sourceIndex, mower)
    (this.copy(mowers = updatedMowers), mower)
  }

  /**
   * Checks if a given [[Coordinates]] is within the grid
   * @param coordinates the coordinates to check
   * @return `true` is available, `false` otherwise
   */
  private def withinGrid(coordinates: Coordinates): Boolean = {
    coordinates.x >= 0 && coordinates.x <= width && coordinates.y >= 0 && coordinates.y <= height
  }
  /**
   * Checks if a given [[Coordinates]] is already occupied by another [[Mower]]
   * @param mower the [[Mower]] aiming to move forward to the coordinates
   * @param coordinates the coordinates to check
   * @return `true` is available, `false` otherwise
   */
  private def coordinatesAvailable(mower: Mower, coordinates: Coordinates): Boolean = {
    !(mowers.toSet - mower)
      .map(_.coordinates)
      .contains(coordinates)
  }
}

/**
 * Companion object of the case class to sugar syntax the creation of a [[Lawn]]
 */
object Lawn {
  def apply(width: Int, height: Int): Lawn = {
    if (width < 0 || height < 0) throw new IllegalArgumentException("Arguments must be >= 0")
    new Lawn(width, height, Seq())
  }
}
