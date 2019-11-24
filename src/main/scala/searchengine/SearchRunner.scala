package test

import java.io.File
import scala.util.Try
import java.io.FileInputStream
import scala.math.Ordering.Double.TotalOrdering

object SimpleSearch extends App {
  Program
    .readFile(args)
    .fold(
      println,
      file => Program.iterate(Program.index(file))
    )
}

object Program {
  import scala.io.StdIn.readLine

  case class Index(m: Map[String, Array[String]]) {
    def rank(searchString: String): Map[String, Double] = {
    val tmp = searchString.toLowerCase.split("(?U)\\W")
    for ((k,v) <- m) yield (k, tmp.intersect(v).length.toDouble / tmp.size )
    }
    override def toString: String = m.mkString(" ")
    def rankToString(searchString: String): String = {
      val tmp = rank(searchString)
        .toSeq.sortBy(_._2)(ord = TotalOrdering.reverse)
        .take(10)
      val res =
        (for {
          (k, v) <- tmp
          if (v != 0)
        } yield (f"${k} : ${v * 100}%.0f%% "))
      if (res.isEmpty) "no matches found "
      else res.mkString(" ")
    }
  }

  sealed trait ReadFileError

  case object MissingPathArg extends ReadFileError

  case class NotDirectory(error: String) extends ReadFileError

  case class FileNotFound(t: Throwable) extends ReadFileError

  def readFile(args: Array[String]): Either[ReadFileError, File] = {
    for {
      path <- args.headOption.toRight(MissingPathArg)
      file <- Try(new File(path))
      .fold(
        throwable => Left(FileNotFound(throwable)),
        file  =>
         if (file.isDirectory) Right(file)
         else Left(NotDirectory(s"Path [$path] is not a directory"))
      )
    } yield file
  }

  def index(file: File): Index = {
      val files = file.listFiles(_.getName.takeRight(4).contains(".txt"))
      println(f"${files.length} files read in directory ${file.getName}")

      @scala.annotation.tailrec
      def iter(files: Array[File], acc: Map[String, Array[String]]): Map[String, Array[String]] = {
        if (files.isEmpty) acc
        else {
          val f = files.head
          val inputStream = new FileInputStream(f)
          val tmp = scala.io.Source.fromInputStream(inputStream).getLines
            .flatMap(_.toLowerCase.split("(?U)\\W")).toArray
            .distinct
            .filterNot(_.isEmpty)
          inputStream.close()
          iter(files.tail, acc + (f.getName -> tmp) )
        }
      }
    Index(iter(files, Map()))
  }

  def iterate(indexedFiles: Index): Unit = {
    print(s"search> ")
    val searchString = readLine()
    if (searchString == ":quit") println("Bye!")
    else {
      if (!searchString.isEmpty)
        print(indexedFiles.rankToString(searchString))
      println()
      iterate(indexedFiles)
    }
  }
}
