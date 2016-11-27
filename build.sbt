import uk.gov.hmrc.gitstamp.GitStampPlugin._
import spray.revolver.RevolverPlugin.autoImport._



lazy val commonSettings = Seq(
  organization := "jp.supership.sample",
  environment := "development",
  version := "0.0.1",
  // set the Scala version used for the project
  scalaVersion := "2.12.0"
) ++ gitStampSettings

lazy val startUpDefaultJVMOptions = Map(
  "vertx.hazelcast.config" -> "classpath:cluster.xml",
  "vertx.logger-delegate-factory-class-name" -> "io.vertx.core.logging.SLF4JLogDelegateFactory",
  "applicationConfig" -> "application.conf",
  "logbackConfig" -> "logback-stdout.xml",
  "hostname" -> "localhost",
  "user.timezone" -> "Asia/Tokyo"
)

val vertxVersion= "3.4.0-SNAPSHOT"

lazy val vertxJars = Seq(
  "io.vertx" %% "vertx-lang-scala" % vertxVersion changing(),
  "io.vertx" % "vertx-lang-scala-stack" % vertxVersion changing(),
  "io.vertx" %% "vertx-lang-scala-on-the-fly" % vertxVersion changing(),
  "io.vertx" % "vertx-codegen" % vertxVersion % "provided" changing(),
  "io.vertx" %% "vertx-web-scala" % vertxVersion changing(),
  "io.vertx" % "vertx-service-factory" % vertxVersion changing(),
  "io.vertx" % "vertx-hazelcast" % vertxVersion changing()
)

lazy val logJars = Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "org.slf4j" % "jul-to-slf4j" % "1.7.21"
)

lazy val stuka = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "stuka project",
    scalacOptions += "-deprecation",
    resolvers += Resolver.sonatypeRepo("snapshots"),
    javaOptions += toJVMOptions(startUpDefaultJVMOptions).mkString(" "),
    mainClass := Some("io.vertx.core.Launcher"),
    libraryDependencies ++= vertxJars ++ logJars,
    assemblyMergeStrategy in assembly := {
      case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
      case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
      case m if m.toLowerCase.matches("meta-inf.*\\.rsa$")      => MergeStrategy.discard
      case m if m.toLowerCase.matches("meta-inf.*\\.dsa$")      => MergeStrategy.discard
      case m if m.toLowerCase.endsWith("index.list")      => MergeStrategy.discard
      case _                                                   => MergeStrategy.first
    },
    initialCommands := """|import io.vertx.lang.scala._
                         |import io.vertx.scala.core._
                         |import jp.supership.stuka._
                         |import scala.concurrent.Future
                         |import scala.concurrent.Promise
                         |import scala.util.Success
                         |import scala.util.Failure
                         |val vertx = Vertx.vertx
                         |implicit val executionContext = io.vertx.lang.scala.VertxExecutionContext(vertx.getOrCreateContext)
                         |""".stripMargin,
    Revolver.settings ++ Seq(
      launcherClass := "io.vertx.core.Starter",
      javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxMetaspaceSize=1024M", "-XX:+CMSClassUnloadingEnabled", "-Xss5m"),
      javaOptions ++= toJVMOptions(startUpDefaultJVMOptions),
      mainClass in reStart := Some(launcherClass.value)
    )
  )

val environment = settingKey[String]("Application environment.")
val launcherClass = settingKey[String]("Start launcher class.")

def toJVMOptions(options: Map[String, String]) = options.map { case (key, value) =>
  s"-D${key}=${value}"
}.toSeq