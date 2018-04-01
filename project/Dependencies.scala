import sbt._

object Dependencies {

 lazy val telegramApi = "com.kgribov" %% "telegram-bot-scala-api" % "0.1"
 lazy val cliParser = "org.rogach" %% "scallop" % "3.1.2"

 lazy val dependencies = Seq(
  telegramApi,
  cliParser
 )
}
