package models

import java.time.{LocalDate, ZoneId, ZoneOffset}
import java.sql.Date

class UserModel(private val id: Option[Long] = None,
                private var username: String,
                private var password: String,
                private var name: String,
                private var email: String,
                private var weight: Option[Int] = None,
                private var height: Option[Int] = None,
                private var dateOfBirth: Option[String] = None,
                private var activityLevel: Option[String] = None,
                private var goal: Option[String] = None,
                private var dailyCalories: Option[Int] = None) {

  def getId: Option[Long] = id
  def getUsername: String = username
  def getPassword: String = password
  def getName: String = name
  def getEmail: String = email
  def getWeight: Option[Int] = weight
  def getHeight: Option[Int] = height
  def getDateOfBirth: Option[String] = dateOfBirth
  def getActivityLevel: Option[String] = activityLevel
  def getGoal: Option[String] = goal
  def getDailyCalories: Option[Int] = dailyCalories

  def calculateDailyCaloricNeeds(dateOfBirth: Date, activityLevel: String, height: Int, weight: Int, goal: String): Option[Int] = {
    val age = LocalDate.now().getYear() - LocalDate.ofInstant(dateOfBirth.toLocalDate.atStartOfDay().toInstant(ZoneOffset.UTC), ZoneId.of("UTC")).getYear()
    val bmr = 10 * weight + 6.25 * height - 5 * age
    val activityMultiplier = activityLevel match {
      case "sedentary" => 1.2
      case "lightly_active" => 1.375
      case "moderately_active" => 1.55
      case "very_active" => 1.725
    }
    val goalMultiplier = goal match {
      case "fat_loss" => 0.8
      case "weight_gain" => 1.2
      case "maintenance" => 1.0
    }
    Some((bmr * activityMultiplier * goalMultiplier).toInt)
  }
}
