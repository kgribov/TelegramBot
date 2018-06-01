package com.kgribov.telegram

import com.kgribov.telegram.bot.dialog.Answer
import com.kgribov.telegram.bot.schema._
import com.kgribov.telegram.service.RegistrationService

import scala.util.Random

case class CreateBotSchema() {

  val botName = "MyNewBot"

  val ASK_AGE_QUESTION = "How old are you?"
  val ASK_GENDER_QUESTION = "What is your gender?"

  def create(): BotSchema = {

    val registrationService = new RegistrationService

    createBotSchema()

      .replyOnCommand("random", _ => Random.nextInt(100).toString)

      .replyOnCommand("id", message => s"Your account id = ${message.from.id}, chatId = ${message.chat.id}")

      .replyOnCommand("users", _ => registrationService.users().map(_.toMessageFormat()).mkString("\n"))

      .startDialogOnCommand("register", DialogSchema(
        questions = Seq(
          askQuestion(ASK_AGE_QUESTION),
          askSelectQuestion(ASK_GENDER_QUESTION, Seq("Male", "Female"))
        ),
        submitAnswers = answers => {
          addUser(answers, registrationService)
          "Thanks, user was added!"
        })
      )
  }

  private def addUser(answers: Map[String, Seq[Answer]], registrationService: RegistrationService): Unit = {
    val gender = answers.get(ASK_GENDER_QUESTION).map(_.head.answer).getOrElse("")
    val age = answers.get(ASK_AGE_QUESTION).map(_.head.answer).getOrElse("")
    val telegramUser = answers.head._2.head.fromUser
    registrationService.registerUser(telegramUser, age, gender)
  }
}
