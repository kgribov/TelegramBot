lazy val telegramBot = (project in file("."))
  .enablePlugins(DockerPlugin)
  .settings(
    name := "telegram-bot",
    organization := "com.kgribov",
    version := "0.1",
    scalaVersion := "2.12.4",
    resolvers += "jitpack" at "https://jitpack.io",
    libraryDependencies ++= Dependencies.dependencies,
    mainClass in assembly := Some("com.kgribov.telegram.Main"),
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    },
    dockerfile in docker := {
      // The assembly task generates a fat JAR file
      val artifact: File = assembly.value
      val artifactTargetPath = s"/app/${artifact.name}"
      val javaOpts = "$LS_JAVA_OPTS"
      new Dockerfile {
          from("openjdk:8-jre")
          add(artifact, artifactTargetPath)
          entryPointShell("java", "-jar", javaOpts, artifactTargetPath, "--api-key", "$BOT_API_KEY")
      }
    },
    buildOptions in docker := BuildOptions(cache = false)
  )
