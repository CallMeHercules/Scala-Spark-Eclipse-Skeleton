name := "SparkApp1"
version := "0.1"
scalaVersion := "2.12.12"
mainClass in Compile := Some("main.scala.Skeleton")
unmanagedClasspath in Runtime += file("target/scala-2.12")

libraryDependencies ++= Seq(
       "org.apache.spark" %% "spark-sql" % "3.1.1",
       "org.mongodb.spark" %% "mongo-spark-connector" % "3.0.1"
)

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += Resolver.mavenLocal
