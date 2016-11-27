logLevel := Level.Warn

scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8")

// https://mvnrepository.com/artifact/org.postgresql/postgresql
libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1212"

//Finds work in progress tags
addSbtPlugin("com.github.fedragon" % "sbt-todolist" % "0.6")

//an sbt plugin to help visualise inter-project dependencies.
addSbtPlugin("com.dwijnand.sbtprojectgraph" % "sbt-project-graph" % "0.1.0")

//Visualize your project's dependencies.
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

//sbt plugin to unify scaladoc/javadoc across multiple projects.
addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % "0.3.3")

//sbt-s3 is a simple sbt plugin that can manipulate objects on Amazon S3.
addSbtPlugin("com.typesafe.sbt" % "sbt-s3" % "0.9")

//an sbt plugin adding support for source code formatting using Scalariform.
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")

// sbt上でアプリをスタート・ストップ
addSbtPlugin("io.spray" % "sbt-revolver" % "0.8.0")

//An SBT plugin that add some basic git data to the artefact's MANIFEST.MF file.
addSbtPlugin("uk.gov.hmrc" % "sbt-git-stamp" % "5.3.0")

//for Flyway
addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.0.3")

//fat jarを作る
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")

//BT native packager lets you build application packages in native formats.
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.4")


resolvers += "Flyway" at "https://flywaydb.org/repo"

resolvers += Resolver.typesafeRepo("releases")

resolvers += Resolver.url("hmrc-sbt-plugin-releases",
  url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers ++= Seq(
  "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)
