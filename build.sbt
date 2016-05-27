//
// build.sbt
//

name := "akka-2.4.x-trial"

scalaVersion  := "2.11.8"

scalacOptions ++= Seq( "-unchecked"
                    , "-deprecation"
                    , "-encoding", "utf8"
                  )

scalacOptions ++= Seq( 
    "-feature",
    "-language", "postfixOps"
    )

libraryDependencies ++= {
  val akkaVersion = "2.4.6"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
    //
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "2.2.4" % Test
  )
}