package main.scala

import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD
import org.apache.log4j.{Level, Logger}
import org.apache.log4j.Logger

import org.apache.log4j.Level


object Skeleton {
  
  def main(args: Array[String]) {
    
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    println("Starting the entry point --------------->")
    
    val spark = SparkSession.builder()
    .appName("Aggregations and Grouping")
    .config("spark.master", "local")
    .config("spark.mongodb.input.uri", "mongodb://127.0.0.1/test_database")
    .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/test_database")
    .getOrCreate()
    
    //now that spark has been initiated, we can import implicits
    import spark.implicits._
    
    // Employee Data
    val empDF = Seq((8, "John" , 1),(64, "Mike", 2), (27, "Garner", 1)).toDF("EmpId", "EmpName" , "DepId")
    empDF.show()
    
    // Department Data
    val depDF = Seq((1,"IT"),(2,"ACCOUNTS")).toDF("DepId" , "DepName")
    depDF.show()
    
    // Joined Data
    val resultant = empDF.join(depDF, "DepId").select($"EmpName", $"DepName")
    resultant.show()
    
    println("Finishing the entry point --------------->") 
    
    println("Starting the word count   --------------->") 
    val file = "Gettysburg-Address.txt"
    val fileRdd: RDD[String] = spark.sparkContext.textFile(file)
    val counts = fileRdd.map(_.replaceAll("[.,]", ""))
                        .map(_.replace("—", " "))
                        .flatMap(line => line.split(" "))
                        .map(word => (word, 1))
                        .reduceByKey(_ + _)
                        .sortBy(_._2)
                        .collect

    println( "------------------------------------------")
    counts.foreach(println)
    println( "------------------------------------------")
    
    println( "mongo connection test---------------------")
    val kinesiologyDF = spark.read
    .format("com.mongodb.spark.sql.DefaultSource")
    .option("uri", "mongodb://127.0.0.1/test_database.Kinesiology")
    .load()
    println( "mongo connected --------------------------")
    kinesiologyDF.show()  
    
    println( "mongo connection end----------------------")
    
  }
}
