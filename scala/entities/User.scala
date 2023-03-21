package entities
import java.sql.Date

class User (private val id: Long,
            private val username: String,
            private var password: String,
            private var name: String,
            private var weight: Option[Double] = None,
            private var height: Option[Double] = None,
            private var dateOfBirth: Option[Date] = None,
            private var activityLevel: Option[String] = None,
            private var banned: Boolean = false) {

  // getters for all private attributes
  def getId: Long = id
  def getUsername: String = username
  def getPassword: String = password
  def getName: String = name
  def getWeight: Option[Double] = weight
  def getHeight: Option[Double] = height
  def getDateOfBirth: Option[Date] = dateOfBirth
  def getActivityLevel: Option[String] = activityLevel
  def isBanned: Boolean = banned

  def setPassword(newPassword: String): Unit = {
    password = newPassword
  }

  def setName(newName: String): Unit = {
    name = newName
  }

  def setWeight(newWeight: Option[Double]): Unit = {
    weight = newWeight
  }

  def setHeight(newHeight: Option[Double]): Unit = {
    height = newHeight
  }

  def setDateOfBirth(newDateOfBirth: Option[Date]): Unit = {
    dateOfBirth = newDateOfBirth
  }

  def setActivityLevel(newActivityLevel: Option[String]): Unit = {
    activityLevel = newActivityLevel
  }

  def setBanned(newBanned: Boolean): Unit = {
    banned = newBanned
  }
}
