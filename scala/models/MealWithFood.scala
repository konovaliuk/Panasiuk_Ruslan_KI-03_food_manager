package models

import entities.{Meal, Food}

case class MealWithFood(private val meal: Meal,
                        private val food: Food) {

  def getId: Option[Long] = meal.getId
  def getCalories: Int = meal.getCalories
  def getProteins: Int = meal.getProteins
  def getFats: Int = meal.getFats
  def getCarbohydrates: Int = meal.getCarbohydrates
  def getFiber: Int = meal.getFiber
  def getAmount: String = meal.getAmount.toString

  def getName: String = food.getName
  def getMeasurement: String = food.getMeasurement
}

