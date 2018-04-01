package com.kgribov.telegram

import com.kgribov.telegram.dsl._

import scala.util.Random

case class CreateBotSchema(apiKey: String) {

  val botName = "MyNewBot"

  def create(): BotSchema = {
    new BotSchema(apiKey, botName)

      .replyOnMessage(
        // reply only on non-command messages
        filter = _.command.isEmpty,
        reply = message => s"Your message size is: ${message.text.length}"
      )

      // enter /random command to get random number
      .replyOnCommand("random", _ => Random.nextInt(100).toString)

      // this command will ask you your age and print it after
      .startDialogOnCommand("dialog", Dialog(questions = Seq(
        askQuestion("What is your age?"),
        submitAnswers(answers => s"Your age is ${answers.lastTextAnswer}")
      )))
  }
}
