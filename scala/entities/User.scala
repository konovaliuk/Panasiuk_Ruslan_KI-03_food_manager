package entities
import java.sql.Date

class User (private val id: Option[Long] = None,
            private val username: String,
            private val password: String,
            private val name: String,
            private val email: String,
            private val weight: Option[Int] = None,
            private val height: Option[Int] = None,
            private val dateOfBirth: Option[Date] = None,
            private val activityLevel: Option[String] = None,
            private val goal: Option[String] = None,
            private val dailyCalories: Option[Int] = None) {

  def getId: Option[Long] = id
  def getUsername: String = username
  def getPassword: String = password
  def getName: String = name
  def getEmail: String = email
  def getWeight: Option[Int] = weight
  def getHeight: Option[Int] = height
  def getDateOfBirth: Option[Date] = dateOfBirth
  def getActivityLevel: Option[String] = activityLevel
  def getGoal: Option[String] = goal
  def getDailyCalories: Option[Int] = dailyCalories
}
