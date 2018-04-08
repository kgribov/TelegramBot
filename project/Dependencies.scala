import sbt._

object Dependencies {

 lazy val telegramBotApi = "com.github.kgribov" % "TelegramBotScalaApi" % "master-SNAPSHOT"

 lazy val cliParser = "org.rogach" %% "scallop" % "3.1.2"

 lazy val dependencies = Seq(
  telegramBotApi,
  cliParser
 )
}
