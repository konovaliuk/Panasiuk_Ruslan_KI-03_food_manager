package commands

import org.json4s.native.JsonMethods._
import org.scalatra._
import java.sql.Date
import java.text.SimpleDateFormat
import javax.servlet.http.HttpSession

import utils.CommandUtils
import dao.implementations.UserImpl
import models.UserModel
import entities.User

class PostSignUpCommand(requestBody: String, session: HttpSession) extends Command{
  override def execute(): Boolean = {
    val json = parse(requestBody)
    val username = CommandUtils.extractString(json, "username")
    val password = CommandUtils.extractString(json, "password")
    val name = CommandUtils.extractString(json, "name")
    val email = CommandUtils.extractString(json, "email")
    val weight = CommandUtils.extractIntOption(json, "weight")
    val height = CommandUtils.extractIntOption(json, "height")
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val dateString = CommandUtils.extractString(json, "date-of-birth")
    val utilDate = dateFormat.parse(dateString)
    val sqlDate = new Date(utilDate.getTime())
    val dateOfBirth = Option(sqlDate)
    val activityLevel = CommandUtils.extractStringOption(json, "activity-level")
    val goal = CommandUtils.extractStringOption(json, "goal")
    val userImpl = new UserImpl()

    userImpl.findUser(username) match {
      case Some(_) => false
      case None =>
        val userModel = new UserModel(None, "", "", "", "")
        val v: User = CommandUtils.buildUser(username, password, name, email, weight, height, dateOfBirth, activityLevel, goal, userModel)
        if (userImpl.addUser(v)) {
          CommandUtils.setSessionAttributes(session, v)
          true
        }
        else false
    }
  }
}
