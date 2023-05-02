package commands

import javax.servlet.http.HttpSession
import org.json4s.native.JsonMethods._
import utils.CommandUtils

class PostHomeCommand(requestBody: String,  session: HttpSession) extends Command {
  override def execute(): Boolean = {
    try {
      val json = parse(requestBody)
      val food = CommandUtils.extractString(json, "food")
      session.setAttribute("food", food)
      true
    } catch{
      case e =>
        println(e)
        false
    }
  }
}
