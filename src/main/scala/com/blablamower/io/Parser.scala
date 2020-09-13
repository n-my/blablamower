package com.blablamower.io

import com.blablamower.domain.{Instruction, Lawn, Mower, Orientation}
import com.typesafe.scalalogging.Logger

/**
 * * Dirty parser to build a [[Lawn]] from the input file.
 * * Please note that this parser doesn't test for non valid input files
 * * and will break if the input file isn't properly formatted.
 * * TODO: Add a Validator to validate the input file format.
 */
object Parser {
  val logger: Logger = Logger(getClass.getName)
  /**
   * Parses the input file content into a [[Lawn]]
   * @param lines the lines of the input file
   * @return the [[Lawn]] as described in the input file
   */
  def parse(lines: Seq[String]): Lawn = {
    // the file can't be empty and must have an odd number of lines
    if (lines.size <= 0 || lines.size % 2 != 1) {
      throw new RuntimeException("The input file is incomplete.")
    }
    val coordinates = lines.head.split(' ').map(c => c.toInt)
    val lawn = Lawn(coordinates.head, coordinates.last)

    val mowers = lines.drop(1).grouped(2).map {
      case List(position, instructions) =>
        val elements = position.split(' ')
        val x = elements(0).toInt
        val y = elements(1).toInt
        val orientation = Orientation.withName(elements(2))
        val instr = instructions.toList.map(c => Instruction.withName(c.toString))
        Mower(x, y, orientation).instructions(instr)
    }.toSeq

    lawn.initialize(mowers)
  }
}
