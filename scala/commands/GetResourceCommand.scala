package commands

import java.io.InputStream

class GetResourceCommand(fileName: String) extends Command {
  override def execute(): InputStream = {
    val resourcePath = s"/resources/$fileName"
    getClass.getResourceAsStream(resourcePath)
  }
}
