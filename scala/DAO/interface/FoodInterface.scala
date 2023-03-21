package DAO.interface
import entities.Food

trait FoodInterface {
  def findFoodByName(name: String): Seq[Food]
  def findById(id: Long): Option[Food]
  def findAllUnique(): Seq[Food]
  def addFood(v: Food): Boolean
}
