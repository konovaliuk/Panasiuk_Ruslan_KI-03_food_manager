package commands

import entities.{Food, Meal}
import models.MealWithFood

import javax.servlet.http.HttpSession
import utils.CommandUtils

class GetMealsCommand(session: HttpSession) extends Command{
  override def execute(): Seq[MealWithFood] = {
    val meals: Option[Seq[Meal]] = CommandUtils.getMeals(session)
    meals match {
      case Some(v) =>
        val food: Seq[Food] = CommandUtils.getFood(v)
        val mealsWithFood = v.zip(food).map { case (meal, food) => MealWithFood(meal, food) }
        mealsWithFood
      case None => Seq.empty[MealWithFood]
    }
  }
}
