# Scala-Spark-Eclipse-Skeleton

this is a baseline application for getting Scala Spark to work in eclipse 4.7.1 with JDK8.

this does use a MongoDB connection, so be aware and make sure to comment out or edit the connection string as needed.

to run:
bash into your git directory
git clone git@github.com:CallMeHercules/Scala-Spark-Eclipse-Skeleton.git
cd Scala-Spark-Eclipse-Skeleton
sbt run

open eclipse and run the skeleton as a scala application.  the sbt will automatically include the dependancies in eclipse using the plugins.sbt.