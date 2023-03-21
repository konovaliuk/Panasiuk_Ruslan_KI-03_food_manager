package DAO.implementation

import DAO.interface.MealInterface
import entities.Meal
import Connection.DatabaseConnection

import java.sql.SQLException

class MealImpl extends MealInterface{

  override def findMealById(id: Long): Option[Meal] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM food WHERE id = ?")
      stmt.setLong(1, id)
      val res = stmt.executeQuery()
      if (res.next()) {
        val v = new Meal(res.getLong("id"), res.getDouble("amount"), res.getLong("user_id"), res.getLong("food_id"), res.getDouble("calories"), res.getDouble("proteins"),
          res.getDouble("fats"), res.getDouble("carbohydrates"), res.getDouble("fibers"), res.getBoolean("topicality"), res.getDate("date_created"))
        Option(v)
      } else{
        None
      }
    } catch {
      case e: SQLException =>
        println(s"SQL Exception: ${e.getMessage}")
        None
    }
    finally {
      if (conn != null) {
        try {
          conn.close()
        } catch {
          case e: SQLException =>
            e.printStackTrace()
        }
      }
    }
  }

  override def findAllByUserId(id: Long): Seq[Meal] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM food WHERE user_id = ?")
      stmt.setLong(1, id)
      val res = stmt.executeQuery()
      var seq: Seq[Meal] = null
      while (res.next()) {
        val v = new Meal(res.getLong("id"), res.getDouble("amount"), res.getLong("user_id"), res.getLong("food_id"), res.getDouble("calories"), res.getDouble("proteins"),
          res.getDouble("fats"), res.getDouble("carbohydrates"), res.getDouble("fibers"), res.getBoolean("topicality"), res.getDate("date_created"))
        seq = seq :+ v
      }
     seq
    } catch {
      case e: SQLException =>
        println(s"SQL Exception: ${e.getMessage}")
        null
    }
    finally {
      if (conn != null) {
        try {
          conn.close()
        } catch {
          case e: SQLException =>
            e.printStackTrace()
        }
      }
    }
  }

  override def findAllTopical(id: Long): Seq[Meal] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM food WHERE user_id = ? AND topicality = 1")
      stmt.setLong(1, id)
      val res = stmt.executeQuery()
      var seq: Seq[Meal] = null
      while (res.next()) {
        val v = new Meal(res.getLong("id"), res.getDouble("amount"), res.getLong("user_id"), res.getLong("food_id"), res.getDouble("calories"), res.getDouble("proteins"),
          res.getDouble("fats"), res.getDouble("carbohydrates"), res.getDouble("fibers"), res.getBoolean("topicality"), res.getDate("date_created"))
        seq = seq :+ v
      }
      seq
    } catch {
      case e: SQLException =>
        println(s"SQL Exception: ${e.getMessage}")
        null
    }
    finally {
      if (conn != null) {
        try {
          conn.close()
        } catch {
          case e: SQLException =>
            e.printStackTrace()
        }
      }
    }
  }

  override def deleteMeal(id: Long): Boolean = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("DELETE FROM meal WHERE id = ?")
      stmt.setLong(1, id)
      stmt.executeQuery()
      true
    } catch {
      case e: SQLException =>
        println(s"SQL Exception: ${e.getMessage}")
        false
    }
    finally {
      if (conn != null) {
        try {
          conn.close()
        } catch {
          case e: SQLException =>
            e.printStackTrace()
        }
      }
    }
  }

  override def addMeal(v: Meal): Boolean ={
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("INSERT INTO food (amount, user_id, food_id) VALUES (?, ?, ?)")
      stmt.setDouble(1, v.getAmount)
      stmt.setLong(2, v.getUserId)
      stmt.setLong(3, v.getFoodId)
      stmt.executeQuery()
      true
    } catch {
      case e: SQLException =>
        println(s"SQL Exception: ${e.getMessage}")
        false
    }
    finally {
      if (conn != null) {
        try {
          conn.close()
        } catch {
          case e: SQLException =>
            e.printStackTrace()
        }
      }
    }
  }

  override def updateMeal(id: Long, amount: Double): Boolean = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("UPDATE food SET amount = ?, WHERE id = ?")
      stmt.setDouble(1, amount)
      stmt.setLong(2, id)
      stmt.executeQuery()
      true
    } catch {
      case e: SQLException =>
        println(s"SQL Exception: ${e.getMessage}")
        false
    }
    finally {
      if (conn != null) {
        try {
          conn.close()
        } catch {
          case e: SQLException =>
            e.printStackTrace()
        }
      }
    }
  }
}
