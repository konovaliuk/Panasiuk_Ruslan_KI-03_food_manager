package commands

import entities.User
import models.UserModel
import utils.CommandUtils

import javax.servlet.http.HttpSession
import java.text.SimpleDateFormat

class GetProfileCommand(session: HttpSession) extends Command{
  override def execute(): UserModel = {
    val filePath = "/views/profile.mustache"
    val u: Option[User] = CommandUtils.getUser(session)
    val dateFormatter = new SimpleDateFormat("yyyy-MM-dd")
    val formattedDateOfBirth = u.get.getDateOfBirth.map(date => dateFormatter.format(date)).orNull
    val userModel = new UserModel(u.get.getId, u.get.getUsername, u.get.getPassword, u.get.getName, u.get.getEmail, u.get.getWeight, u.get.getHeight, Option(formattedDateOfBirth), u.get.getActivityLevel, u.get.getGoal, u.get.getDailyCalories)
    userModel
  }
}
