package commands

import dao.implementations.UserImpl
import entities.User
import models.UserModel

import javax.servlet.http.HttpSession
import utils.CommandUtils
import org.json4s.native.JsonMethods._

import java.text.SimpleDateFormat
import java.sql.Date

class PostProfileCommand(requestBody: String,  session: HttpSession) extends Command{
  override def execute(): Boolean = {
    val json = parse(requestBody)
    val id: Long = CommandUtils.getSessionAttributeAsString(session, "id").toLong
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
    val dob = Option(sqlDate)
    val activityLevel = CommandUtils.extractStringOption(json, "activity-level")
    val goal = CommandUtils.extractStringOption(json, "goal")
    val userModel = new UserModel(None, "", "", "", "")
    val v: User = new User(Option(id), username, password, name, email, weight, height, dob, activityLevel, goal, userModel.calculateDailyCaloricNeeds(dob.get, activityLevel.get, height.get, weight.get, goal.get))
    val userImpl = new UserImpl()
    userImpl.updateUser(v) match {
      case true =>
        CommandUtils.setSessionAttributes(session, v)
        true
      case false => false
    }
  }

}
