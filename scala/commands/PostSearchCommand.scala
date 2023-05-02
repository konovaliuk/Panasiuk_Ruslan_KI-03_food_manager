package commands

import javax.servlet.http.HttpSession
import org.json4s.native.JsonMethods.parse

import utils.CommandUtils
import dao.implementations.{FoodImpl, MealImpl}
import entities.{Food, Meal}

class PostSearchCommand (requestBody: String, session: HttpSession) extends Command{
  override def execute(): Boolean = {
    val json = parse(requestBody)
    val userId = CommandUtils.getSessionAttributeAsString(session,"id").toLong
    val id = CommandUtils.extractLongOption(json, "id")
    val amount = CommandUtils.extractDoubleOption(json, "amount")
    val foodImpl = new FoodImpl()
    if (id.isDefined && amount.isDefined) {
      val food: Option[Food] = foodImpl.findById(id.get)
      food match {
        case Some(f) =>
          val meal = new Meal(None, amount.get, userId, id.get)
          val mealImpl = new MealImpl()
          mealImpl.addMeal(meal)
          true
        case None => false
      }
    } else false
  }

}
