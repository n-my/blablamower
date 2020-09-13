package com.blablamower.domain

/**
 * Case class to define Cartesian coordinates
 * @param x value on the horizontal x-axis
 * @param y value on the vertical y-axis
 */
case class Coordinates(x: Int, y: Int) {
  /**
   * Increments by 1 the x coordinate
   * @return a new incremented [[Coordinates]]
   */
  def incrementX(): Coordinates = {
    Coordinates(x + 1, y)
  }
  /**
   * Decrements by 1 the x coordinate
   * @return a new decremented [[Coordinates]]
   */
  def decrementX(): Coordinates = {
    Coordinates(x - 1, y)
  }
  /**
   * Increments by 1 the y coordinate
   * @return a new incremented [[Coordinates]]
   */
  def incrementY(): Coordinates = {
    Coordinates(x, y + 1)
  }
  /**
   * Decrements by 1 the y coordinate
   * @return a new decremented [[Coordinates]]
   */
  def decrementY(): Coordinates = {
    Coordinates(x, y - 1)
  }
}
