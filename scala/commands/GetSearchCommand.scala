package commands

import javax.servlet.http.HttpSession
import entities.Food
import utils.CommandUtils


class GetSearchCommand(session: HttpSession) extends Command{
  override def execute(): Option[Seq[Food]] = {
    val foodItems: Option[Seq[Food]] = CommandUtils.getFood(session)
    foodItems
  }
}
