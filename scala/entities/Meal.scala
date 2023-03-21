package entities

import java.sql.Date

class Meal (private val id: Long,
            private var amount: Double,
            private val userId: Long,
            private val foodId: Long,
            private var calories: Double = 0.0,
            private var proteins: Double = 0.0,
            private var fats: Double = 0.0,
            private var carbohydrates: Double = 0.0,
            private var fibers: Double = 0.0,
            private var topicality: Boolean = false,
            private val dateCreated: Date) {

  def getId: Long = id
  def getAmount: Double = amount
  def getCalories: Double = calories
  def getProteins: Double = proteins
  def getFats: Double = fats
  def getCarbohydrates: Double = carbohydrates
  def getFibers: Double = fibers
  def getUserId: Long = userId
  def getFoodId: Long = foodId
  def getTopicality: Boolean = topicality
  def getDateCreated: Date = dateCreated

  def setAmount(newAmount: Double): Unit = {
    amount = newAmount
  }

  def setTopicality(newTopicality: Boolean): Unit = {
    topicality = newTopicality
  }

  def setCalories(newCalories: Double): Unit = {
    calories = newCalories
  }

  def setProteins(newProteins: Double): Unit = {
    proteins = newProteins
  }

  def setFats(newFats: Double): Unit = {
    fats = newFats
  }

  def setCarbohydrates(newCarbohydrates: Double): Unit = {
    carbohydrates = newCarbohydrates
  }

  def setFibers(newFibers: Double): Unit = {
    fibers = newFibers
  }
}
