package com.blablamower.io

import java.io.IOException

import scala.util.{Failure, Success, Try}

/**
 * Object to read the content of a file from the local filesystem
 */
object Reader {
  /**
   * Reads a file and returns its content
   * @param filePath the path of the file to read
   * @throws IOException if the file can't be read
   * @return the content of the file as a collection of [[String]], one line per [[String]]
   */
  def readFile(filePath: String): Seq[String] = {
    val inputPath = Reader.readTextFileWithTry (filePath)
    inputPath match {
      case Success (lines) => lines
      case Failure (s) => throw new IOException(s"Failed reading the file ${filePath}. $s")
    }
  }

  /**
   * Tries to read a file from the given file path
   * @param filePath the path of the file to read
   * @return the content of the file wrapped in a [[Success]] if successful,
   *         the error message wrapped in a [[Failure]] otherwise
   */
  private def readTextFileWithTry(filePath: String): Try[List[String]] = {
    Try {
      val lines = using(io.Source.fromFile(filePath)) { source =>
        (for (line <- source.getLines) yield line).toList
      }
      lines
    }
  }

  /**
   * Implements the Loan Pattern so that the resource is deterministically closed
   * when it goes out of scope.
   * See https://alvinalexander.com/scala/how-to-open-read-text-files-in-scala-cookbook-examples/
   */
  private def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
    try {
      f(resource)
    } finally {
      resource.close()
    }
}
