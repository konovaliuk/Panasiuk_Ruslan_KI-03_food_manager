package dao.implementations

import connection.DatabaseConnection
import dao.interfaces.FoodInterface
import entities.Food

import java.sql.SQLException

class FoodImpl extends FoodInterface{

  override def findFoodByName(name: String): Seq[Food] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM food WHERE name = ?")

      stmt.setString(1, name)
      val res = stmt.executeQuery()
      var seq: Seq[Food] = Seq.empty[Food]
      while (res.next()) {
        val v = new Food(res.getLong("id"), res.getString("name"), res.getString("measurement"), res.getDouble("calories_multiplier"), res.getDouble("proteins_multiplier"),
          res.getDouble("fats_multiplier"), res.getDouble("carbohydrates_multiplier"), res.getDouble("fiber_multiplier"))
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
  override def findById(id: Long): Option[Food] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM food WHERE id = ?")
      stmt.setLong(1, id)
      val res = stmt.executeQuery()
      if (res.next()) {
        val v = new Food(res.getLong("id"), res.getString("name"), res.getString("measurement"), res.getDouble("calories_multiplier"), res.getDouble("proteins_multiplier"),
          res.getDouble("fats_multiplier"), res.getDouble("carbohydrates_multiplier"), res.getDouble("fiber_multiplier"))
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

  override def addFood(v: Food): Boolean = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("INSERT INTO food VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
      stmt.setLong(1, v.getId)
      stmt.setString(2, v.getName)
      stmt.setString(3, v.getMeasurement)
      stmt.setDouble(4, v.getCaloriesM)
      stmt.setDouble(5, v.getProteinsM)
      stmt.setDouble(6, v.getFatsM)
      stmt.setDouble(7, v.getCarbohydratesM)
      stmt.setDouble(8, v.getFiberM)
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
