package controllers

import org.scalatra._

import org.scalatra.scalate.ScalateSupport
import commands._
import entities.Food
import models.MealWithFood


class Controller extends ScalatraServlet  with ScalateSupport{

  error {
    case t: Throwable =>
      t.printStackTrace()
      response.setStatus(500)
      "Internal server error"
  }

  get("/*") {
    val url = request.getRequestURL.toString
    val path = url.replace("http://localhost:8080", "")
    val command: Command = path match {
      case "/login" => new GetLoginCommand()
      case "/signup" => new GetSignUpCommand()
      case "/home" => new GetHomeCommand(session)
      case "/search" => new GetSearchCommand(session)
      case "/profile" => new GetProfileCommand(session)
      case "/meals" => new GetMealsCommand(session)
    }
    contentType = "text/html"
    path match {
      case "/login" | "/signup" | "/home" =>
        val htmlContent = command.execute()
        Ok(htmlContent)
      case "/search" =>
        val filePath = "/views/search.mustache"
        val foodItems = command.execute()
        foodItems match {
          case Some(items) => mustache(filePath, "foodItems" -> items)
          case None => mustache(filePath, "foodItems" -> Seq.empty[Food])
        }
      case "/profile" =>
        val filePath = "/views/profile.mustache"
        val userModel = command.execute()
        mustache(filePath, "userProfile" -> userModel)
      case "/meals" =>
        val filePath = "/views/meals.mustache"
        val mealsWithFood = command.execute()
        mealsWithFood match{
          case meal :: Nil => mustache(filePath, "mealsWithFood" -> meal)
          case _=> mustache(filePath, "mealsWithFood" -> Seq.empty[MealWithFood])

        }
    }
  }

  post("/*") {
    val url = request.getRequestURL.toString
    val path = url.replace("http://localhost:8080", "")
    val requestBody = request.body
    val command: Command = path match{
      case "/login"  => new PostLoginCommand(requestBody, session)
      case "/signup" => new PostSignUpCommand(requestBody, session)
      case "/home" => new PostHomeCommand(requestBody, session)
      case "/search" => new PostSearchCommand(requestBody, session)
      case "/profile" => new PostProfileCommand(requestBody, session)
      case "/meals" => new PostMealsCommand(requestBody, session)
    }
    val success: Boolean = command.execute().asInstanceOf[Boolean]
    if (success) {
      Ok()
    } else {
       path match {
         case "/login" => halt(status = 401, body = "Invalid username or password")
         case "/signup" => halt(status = 409, body = "User with the same username already exists")
         case _=> halt(status = 500, body = "Internal Server Error")
       }
    }
  }

  get("/resources/:file") {
    val fileName = params("file")
    val command: Command = new GetResourceCommand(fileName)
    val inputStream = command.execute()
    if (fileName.endsWith(".css"))
      contentType = "text/css"
    else if (fileName.endsWith(".jpg"))
      contentType = "image/jpg"
    Ok(inputStream)
  }
}
