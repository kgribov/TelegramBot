import sbt._

object Dependencies {

 lazy val cliParser = "org.rogach" %% "scallop" % "3.1.2"

 lazy val telegramApiProject = ProjectRef(uri("https://github.com/kgribov/TelegramBotScalaApi.git"), "telegramBotScalaApi")

 lazy val dependencies = Seq(
  cliParser
 )
}
