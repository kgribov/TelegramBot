package com.kgribov.telegram

import com.kgribov.telegram.dsl._
import com.kgribov.telegram.process.DialogAnswers
import com.kgribov.telegram.service.RegistrationService

import scala.util.Random

case class CreateBotSchema(apiKey: String) {

  val botName = "MyNewBot"

  val ASK_AGE_QUESTION = "How old are you?"
  val ASK_GENDER_QUESTION = "What is your gender?"

  def create(): BotSchema = {

    val registrationService = new RegistrationService

    new BotSchema(apiKey, botName)

      .replyOnCommand("random", _ => Random.nextInt(100).toString)

      .replyOnCommand("id", message => s"Your account id = ${message.from.id}, chatId = ${message.chat.id}")

      .replyOnCommand("users", _ => registrationService.users().map(_.toMessageFormat()).mkString("\n"))

      .startDialogOnCommand("register", Dialog(questions = Seq(
        askQuestion(ASK_AGE_QUESTION),
        askSelectQuestion(ASK_GENDER_QUESTION, Seq("Male", "Female")),
        submitAnswers(answers => {
          addUser(answers, registrationService)
          "Thanks, user was added!"
        })
      )))
  }

  private def addUser(answers: DialogAnswers, registrationService: RegistrationService): Unit = {
    val gender = answers.allAnswersFromOneUser.getOrElse(ASK_GENDER_QUESTION, "")
    val age = answers.allAnswersFromOneUser.getOrElse(ASK_AGE_QUESTION, "")
    val telegramUser = answers.lastAnswersFromUsers.head._1
    registrationService.registerUser(telegramUser, age, gender)
  }
}
