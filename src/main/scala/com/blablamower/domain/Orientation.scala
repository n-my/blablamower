package com.blablamower.domain

/**
 * Enumeration of the possible orientations
 */
object Orientation extends Enumeration {
  type Orientation = Value
  val NORTH: Value = Value("N")
  val EAST: Value = Value("E")
  val SOUTH: Value = Value("S")
  val WEST: Value = Value("W")

  /**
   * Rotates the [[Orientation]] 90​° to the right
   * @param orientation the [[Orientation]] to rotate
   * @return the matching [[Orientation]]
   */
  def rotateRight(orientation: Orientation.Value): Orientation = {
    orientation match {
      case NORTH => EAST
      case EAST => SOUTH
      case SOUTH => WEST
      case WEST => NORTH
    }
  }
  /**
   * Rotates the [[Orientation]] 90​° to the left
   * @param orientation the [[Orientation]] to rotate
   * @return the matching [[Orientation]]
   */
  def rotateLeft(orientation: Orientation.Value): Orientation = {
    orientation match {
      case NORTH => WEST
      case EAST => NORTH
      case SOUTH => EAST
      case WEST => SOUTH
    }
  }
}
