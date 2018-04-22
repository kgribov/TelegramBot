package com.kgribov.telegram.service

import com.kgribov.telegram.model.User
import com.typesafe.scalalogging.LazyLogging
import scalikejdbc._

class RegistrationService extends LazyLogging {

  DatabaseConnector.connectToDB()
  implicit val session = AutoSession

  sql"""
    create table if not exists users (
    id serial not null primary key,
    firstName varchar(64),
    lastName varchar(64),
    username varchar(64),
    age varchar(64),
    gender varchar(64)
    )
  """.execute.apply()

  def registerUser(user: User, age: String, gender: String): Unit = {
    logger.info(s"New user were added: $user, age: $age, gender: $gender")

    val userId = user.id
    val userFirstName = user.firstName
    val userLastName = user.lastName.getOrElse("")
    val username = user.username.getOrElse("")

    sql"""
    INSERT INTO users (id, firstName, lastName, username, age, gender)
    VALUES ($userId, $userFirstName, $userLastName, $username, $age, $gender)
    ON CONFLICT (id) DO NOTHING
    """.execute().apply()
  }

  def users(): List[BotUser] = {
    sql"""
        SELECT * FROM users
      """
      .map(rs => toUser(rs))
      .list()
      .apply()
  }

  private def toUser(resultSet: WrappedResultSet): BotUser = {
    val telegramUser = User(
      resultSet.int("id"),
      isBot = false,
      resultSet.string("firstName"),
      Some(resultSet.string("lastName")),
      Some(resultSet.string("username"))
    )
    BotUser(telegramUser, resultSet.string("age"), resultSet.string("gender"))
  }

  case class BotUser(user: User, age: String, gender: String) {

    def toMessageFormat(): String = {
      val telegramName = Seq(user.firstName, user.username.getOrElse(""), user.lastName.getOrElse("")).mkString(" ")
      s"$telegramName age: $age gender: $gender"
    }
  }
}
