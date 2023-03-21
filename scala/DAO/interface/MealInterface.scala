package DAO.interface

import entities.Meal
trait MealInterface {
  def findMealById(id: Long): Option[Meal]
  def findAllByUserId(id: Long): Seq[Meal]
  def findAllTopical(id: Long): Seq[Meal]
  def deleteMeal(id: Long): Boolean
  def addMeal(v: Meal): Boolean
  def updateMeal(id: Long, amount: Double): Boolean
}
