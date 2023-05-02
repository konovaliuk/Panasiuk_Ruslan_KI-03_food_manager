package utils

import javax.servlet.http.HttpSession
import org.json4s._
import entities.{Food, Meal, User}
import org.json4s.native.Serialization

import java.sql.Date
import models.UserModel
import dao.implementations.{FoodImpl, MealImpl, UserImpl}

object CommandUtils {

  implicit val formats: Formats = Serialization.formats(NoTypeHints)


  def extractString(json: JValue, key: String): String = {
    (json \ key).extract[String]
  }

  def extractIntOption(json: JValue, key: String): Option[Int] = {
    (json \ key).extractOpt[String].flatMap(s => scala.util.Try(s.toInt).toOption)
  }

  def extractDoubleOption(json: JValue, key: String): Option[Double] = {
    (json \ key).extractOpt[String].flatMap(s => scala.util.Try(s.toDouble).toOption)
  }

  def extractLongOption(json: JValue, key: String): Option[Long] = {
    (json \ key).extractOpt[String].flatMap(s => scala.util.Try(s.toLong).toOption)
  }

  def extractStringOption(json: JValue, key: String): Option[String] = {
    (json \ key).extractOpt[String]
  }

  def getSessionAttributeAsString(session: HttpSession, attributeName: String): String = {
    Option(session.getAttribute(attributeName)) match {
      case Some(value: AnyRef) => value match {
        case Some(innerValue) => innerValue.toString // handle Option value
        case None => "" // handle None value
        case _ => value.toString // handle other AnyRef value
      }
      case None => "" // handle empty session attribute
    }
  }

  def setSessionAttributes(session: HttpSession, v: User): Unit = {
    session.setAttribute("id", v.getId)
    session.setAttribute("username", v.getUsername)
    session.setAttribute("password", v.getPassword)
    session.setAttribute("name", v.getName)
    session.setAttribute("email", v.getEmail)
    session.setAttribute("weight", v.getWeight)
    session.setAttribute("height", v.getHeight)
    session.setAttribute("dob", v.getDateOfBirth)
    session.setAttribute("activityLvl", v.getActivityLevel)
    session.setAttribute("goal", v.getGoal)
    session.setAttribute("dailyCalories", v.getDailyCalories)
  }

  def safelySum[T](seq: Seq[T])(f: T => Int): Int = {
    Option(seq).map(_.map(f).sum).getOrElse(0)
  }

  def buildUser(username: String, password: String, name: String, email: String, weight: Option[Int], height: Option[Int],
                        dateOfBirth: Option[Date], activityLevel: Option[String], goal: Option[String], userModel: UserModel): User = {
    (for {
      dob <- dateOfBirth
      activity <- activityLevel
      h <- height
      w <- weight
      g <- goal
    } yield new User(
      None,
      username,
      password,
      name,
      email,
      weight,
      height,
      dateOfBirth,
      activityLevel,
      goal,
      userModel.calculateDailyCaloricNeeds(dob, activity, w, h, g)
    )).getOrElse(new User(
      None,
      username,
      password,
      name,
      email,
      weight,
      height,
      dateOfBirth,
      activityLevel,
      goal,
      Some(0)
    ))
  }

  def getFood(session: HttpSession): Option[Seq[Food]] = {
    val food = getSessionAttributeAsString(session,"food")
    val foodImpl = new FoodImpl()
    Option(foodImpl.findFoodByName(food))
  }

  def getUser(session: HttpSession): Option[User] = {
    val id: Long = getSessionAttributeAsString(session,"id").toLong
    val userImpl = new UserImpl()
    userImpl.findUserById(id)
  }

  def getMeals(session: HttpSession): Option[Seq[Meal]] = {
    val mealImpl = new MealImpl()
    Option(mealImpl.findAllTopical(getSessionAttributeAsString(session, "id").toLong))
  }

  def getFood(v: Seq[Meal]): Seq[Food] = {
    val foodIds = v.map(_.getFoodId)
    val foodImpl = new FoodImpl
    val foodSeq: Seq[Option[Food]] = foodIds.map(foodId => foodImpl.findById(foodId))
    val filteredFoodSeq: Seq[Food] = foodSeq.flatten
    filteredFoodSeq
  }

  def deleteMeal(id: Long): Boolean = {
    val mealImpl = new MealImpl
    val v = mealImpl.deleteMeal(id)
    v
  }
}