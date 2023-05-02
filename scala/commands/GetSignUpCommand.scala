package commands

import scala.io.Source

class GetSignUpCommand extends Command {
  override def execute(): String = {
    val filePath = "/views/signup.html"
    val htmlContent = Option(getClass.getResourceAsStream(filePath)) match {
      case Some(inputStream) => Source.fromInputStream(inputStream).mkString
      case None => "File not found"
    }
    htmlContent
  }
}
