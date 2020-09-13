package com.blablamower.app

import scopt.OptionParser

/**
 * Case class that holds the configuration of the [[BlaBlaMowerApp]] job.
 */
case class UsageConfig(filePath: String = "")

/**
 * Class that defines and parses the arguments of the [[BlaBlaMowerApp]] job.
 */
class UsageOptionParser
  extends OptionParser[UsageConfig]("BlaBlaMower") {
  head("scopt", "3.x")
  opt[String]('f', "file")
    .required()
    .action((value, arg) => {
      arg.copy(filePath = value)
    })
    .text("The input file path. Required.")
}
