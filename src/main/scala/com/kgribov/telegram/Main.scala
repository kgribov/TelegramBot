package com.kgribov.telegram

import com.kgribov.telegram.cli.CliParams
import com.typesafe.scalalogging.LazyLogging

object Main extends App with LazyLogging {
  val params = new CliParams(args)

  val botSchema = CreateBotSchema(params.apiKey())

  System.setProperty("log.name", s"bot_${botSchema.botName}")

  botSchema
    .create()
    .startBot()
}

