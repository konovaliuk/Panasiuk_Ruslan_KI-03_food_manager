package commands

import dao.implementations.MealImpl

import javax.servlet.http.HttpSession
import utils.CommandUtils
import org.json4s.native.JsonMethods._

class PostMealsCommand(requestBody: String,  session: HttpSession) extends Command{
  override def execute(): Boolean = {
    val json = parse(requestBody)
    val id = CommandUtils.extractLongOption(json, "id")
    val amount = CommandUtils.extractDoubleOption(json, "amount")
    val mealImpl = new MealImpl()
    if (id.isDefined && amount.isDefined) {
      mealImpl.updateMeal(id.get, amount.get) match {
        case true => true
        case false => false
      }
    } else false
  }
}
