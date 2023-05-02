package dao.interfaces

import entities.Meal
trait MealInterface {
  def findAllByUserId(id: Long): Seq[Meal]
  def findAllTopical(id: Long): Seq[Meal]
  def deleteMeal(id: Long): Boolean
  def addMeal(v: Meal): Boolean
  def updateMeal(id: Long, amount: Double): Boolean
}
