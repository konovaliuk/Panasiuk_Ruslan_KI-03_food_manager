package dao.interfaces
import entities.Food

trait FoodInterface {
  def findFoodByName(name: String): Seq[Food]
  def findById(id: Long): Option[Food]
  def addFood(v: Food): Boolean
}
