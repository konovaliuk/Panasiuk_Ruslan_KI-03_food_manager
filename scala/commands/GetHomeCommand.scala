package commands

import utils.CommandUtils
import scala.io.Source
import dao.implementations.MealImpl
import entities.Meal
import javax.servlet.http.HttpSession

class GetHomeCommand(session: HttpSession) extends Command {
  override def execute(): String = {
    val id: Long = CommandUtils.getSessionAttributeAsString(session, "id").toLong
    val mealImpl = new MealImpl()
    val meals: Seq[Meal] = mealImpl.findAllTopical(id)
    val nutrients: Map[String, Int] = Map(
      "caloriesConsumed" -> CommandUtils.safelySum(meals)(_.getCalories),
      "fats" -> CommandUtils.safelySum(meals)(_.getFats),
      "proteins" -> CommandUtils.safelySum(meals)(_.getProteins),
      "carbs" -> CommandUtils.safelySum(meals)(_.getCarbohydrates),
      "fiber" -> CommandUtils.safelySum(meals)(_.getFiber)
    )


    val dailyCalories = CommandUtils.getSessionAttributeAsString(session, "dailyCalories").toInt
    val caloriesRemaining = if (meals != null && meals.nonEmpty) {
      val consumedCalories = nutrients("caloriesConsumed")
      if (dailyCalories < consumedCalories) {
        "0"
      } else {
        (dailyCalories - consumedCalories).toString
      }
    } else {
      dailyCalories.toString
    }

    val placeholders = Map(
      "{{calories_remaining}}" -> caloriesRemaining,
      "{{calories_consumed}}" -> nutrients("caloriesConsumed").toString,
      "{{fats}}" -> nutrients("fats").toString,
      "{{total_carbs}}" -> nutrients("carbs").toString,
      "{{fiber}}" -> nutrients("fiber").toString,
      "{{protein}}" -> nutrients("proteins").toString
    )

    val filePath = "/views/home.html"
    val htmlContent = Option(getClass.getResourceAsStream(filePath))
      .map(inputStream => Source.fromInputStream(inputStream).mkString)
      .getOrElse("File not found")

    val warning = if (caloriesRemaining.toInt < nutrients("caloriesConsumed")) {
      "<div class='warning'>Warning: Consumed more calories than daily limit!</div>"
    } else {
      ""
    }
    placeholders.foldLeft(htmlContent) { case (content, (placeholder, attributeName)) =>
      content.replace(placeholder, attributeName)
    } + warning
  }
}
