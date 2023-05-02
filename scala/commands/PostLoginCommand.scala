package commands

import org.json4s.native.JsonMethods._
import utils.CommandUtils
import dao.implementations.UserImpl
import javax.servlet.http.HttpSession

class PostLoginCommand(requestBody: String,  session: HttpSession) extends Command {
  override def execute(): Boolean = {
    val json = parse(requestBody)
    val username = CommandUtils.extractString(json, "username")
    val password = CommandUtils.extractString(json, "password")
    val userImpl = new UserImpl()
    userImpl.findUser(username) match {
      case Some(v) if v.getUsername == username && v.getPassword == password =>
        CommandUtils.setSessionAttributes(session, v)
        true // authentication successful
      case _ =>
        false // authentication failed
    }
  }
}