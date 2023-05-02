package dao.implementations



import connection.DatabaseConnection
import dao.interfaces.UserInterface
import entities.User

import java.sql.SQLException


class UserImpl extends UserInterface {
  override def findAllUser(): Seq[User] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM user")
      val res = stmt.executeQuery()
      var seq: Seq[User] = null
      while(res.next()){
        val v = new User(Option(res.getLong("id")), res.getString("username"), res.getString("password"), res.getString("name"), res.getString("email"),
          Option(res.getInt("weight")), Option(res.getInt("height")), Option(res.getDate("date_of_birth")), Option(res.getString("activity_lvl")))
        seq = seq :+ v
      }
      seq
    } catch{
      case e: SQLException =>
        println(s"SQL Exception: ${e.getMessage}")
        null
    }
    finally{
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

  override def findUser(username: String): Option[User] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ?")
      stmt.setString(1, username)
      val res = stmt.executeQuery()
      if (res.next()) {
        val user = new User(Option(res.getLong("id")), res.getString("username"), res.getString("password"), res.getString("name"), res.getString("email"),
          Option(res.getInt("weight")), Option(res.getInt("height")), Option(res.getDate("date_of_birth")), Option(res.getString("activity_lvl")),
          Option(res.getString("goal")), Option(res.getInt("daily_caloric_amount")))
        Option(user)
      } else {
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

  override def findUserById(id: Long): Option[User] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?")
      stmt.setLong(1, id)
      val res = stmt.executeQuery()
      if (res.next()) {
        val user = new User(Option(res.getLong("id")), res.getString("username"), res.getString("password"), res.getString("name"), res.getString("email"),
          Option(res.getInt("weight")), Option(res.getInt("height")), Option(res.getDate("date_of_birth")), Option(res.getString("activity_lvl")),
          Option(res.getString("goal")), Option(res.getInt("daily_caloric_amount")))
        Option(user)
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

  override def addUser(user: User): Boolean ={
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("CALL insert_user_and_role(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
      stmt.setString(1, user.getUsername)
      stmt.setString(2, user.getPassword)
      stmt.setString(3, user.getName)
      stmt.setString(4, user.getEmail)
      user.getWeight match {
        case Some(weight) => stmt.setInt(5, weight)
        case None => stmt.setNull(5, java.sql.Types.INTEGER)
      }
      user.getHeight match{
        case Some(height) => stmt.setInt(6, height)
        case None => stmt.setNull(6, java.sql.Types.INTEGER)
      }
      user.getDateOfBirth match{
        case Some(date) => stmt.setDate(7, date)
        case None => stmt.setNull(7, java.sql.Types.DATE)
      }
      user.getActivityLevel match{
        case Some(activity) => stmt.setString(8, activity)
        case None => stmt.setNull(8, java.sql.Types.VARCHAR)
      }
      user.getGoal match {
        case Some(goal) => stmt.setString(9, goal)
        case None => stmt.setNull(9, java.sql.Types.VARCHAR)
      }
      user.getDailyCalories match {
        case Some(calories) => stmt.setInt(10, calories)
        case None => stmt.setNull(10, java.sql.Types.INTEGER)
      }
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
  override def updateUser(user: User): Boolean ={
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      findUserById(user.getId.getOrElse(-1)) match{
        case Some(dbUser) =>
        var sql = "UPDATE user SET"
        if (user.getUsername != dbUser.getUsername) {
          sql += s" username = '${user.getUsername}',"
        }

        if (user.getPassword != dbUser.getPassword) {
          sql += s" password = '${user.getPassword}',"
        }

        if (user.getName != dbUser.getName) {
          sql += s" name = '${user.getName}',"
        }

        if (user.getWeight != dbUser.getWeight) {
          sql += s" weight = ${user.getWeight.getOrElse(70)},"
        }

        if (user.getEmail != dbUser.getEmail) {
          sql += s" email = '${user.getEmail}',"
        }

        if (user.getHeight != dbUser.getHeight) {
          sql += s" height = ${user.getHeight.getOrElse(175)},"
        }

        if (user.getDateOfBirth != dbUser.getDateOfBirth) {
          sql += s" date_of_birth = '${user.getDateOfBirth.get}',"
        }

        if (user.getActivityLevel != dbUser.getActivityLevel) {
          sql += s" activity_lvl = '${user.getActivityLevel}',"
        }

        if (user.getDailyCalories != dbUser.getDailyCalories) {
          sql += s" daily_caloric_amount = ${user.getDailyCalories.getOrElse(2000)},"
        }

        if (user.getGoal != dbUser.getGoal) {
          sql += s" goal = '${user.getGoal}',"
        }

        if (sql.endsWith(",")) {
          sql = sql.dropRight(1)
        }

        sql += s" WHERE id = ${user.getId.get}"

        val stmt = conn.createStatement()
        stmt.executeUpdate(sql)
        true
        case _ => false
      }

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