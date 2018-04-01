package com.kgribov.telegram.cli

import org.rogach.scallop._

class CliParams(arguments: Seq[String]) extends ScallopConf(arguments) {
  val apiKey = opt[String](required = true, name = "api-key")
  verify()
}
