package entities

import java.sql.Date

class Meal (private val id:  Option[Long] = None,
            private var amount: Double,
            private val userId: Long,
            private val foodId: Long,
            private var calories: Int = 0,
            private var proteins: Int = 0,
            private var fats: Int = 0,
            private var carbohydrates: Int = 0,
            private var fiber: Int = 0,
            private var topicality: Boolean = false,
            private val dateCreated: Date = new Date(System.currentTimeMillis())) {

  def getId: Option[Long] = id
  def getAmount: Double = amount
  def getCalories: Int = calories
  def getProteins: Int = proteins
  def getFats: Int = fats
  def getCarbohydrates: Int = carbohydrates
  def getFiber: Int = fiber
  def getUserId: Long = userId
  def getFoodId: Long = foodId
  def getTopicality: Boolean = topicality
  def getDateCreated: Date = dateCreated
}
