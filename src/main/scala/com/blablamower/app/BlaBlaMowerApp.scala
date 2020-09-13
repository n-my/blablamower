package com.blablamower.app

import com.blablamower.io.{Parser, Reader}
import scopt.OptionParser

/**
 * BlaBlaMower is a mower simulations app.
 *
 * The simulation is described in an input files that stores the lawn dimensions,
 * and the mowers respective position and instructions.
 *
 * The app initializes the simulation, runs all the instructions one mower at a time,
 * and prints the mower final positions.
 */
object BlaBlaMowerApp {
  val configurationParser: OptionParser[UsageConfig] = new UsageOptionParser()

  def main(args: Array[String]): Unit = {
    configurationParser.parse(args, UsageConfig()) match {
      case Some(config) => run(config.filePath)
      case None => throw new IllegalArgumentException("Arguments provided are not valid.")
    }
  }

  /**
   * Runs the simulation described in the input file
   * @param filePath path of the input file
   */
  def run(filePath: String) {
    val lines = Reader.readFile(filePath)
    val lawn = Parser.parse(lines)
    lawn.execute()
  }
}
