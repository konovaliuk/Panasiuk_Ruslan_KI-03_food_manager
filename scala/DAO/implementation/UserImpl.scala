package DAO.implementation



import Connection.DatabaseConnection
import DAO.interface.UserInterface
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
        val v = new User(res.getLong("id"), res.getString("username"), res.getString("password"), res.getString("name"),
          Option(res.getDouble("weight")), Option(res.getDouble("height")), Option(res.getDate("date_of_birth")), Option(res.getString("activity_lvl")), res.getBoolean("baned"))
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

  override def findUserById(id: Long): Option[User] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?")
      stmt.setLong(1, id)
      val res = stmt.executeQuery()
      if (res.next()) {
        val user = new User(res.getLong("id"), res.getString("username"), res.getString("password"), res.getString("name"),
          Option(res.getDouble("weight")), Option(res.getDouble("height")), Option(res.getDate("date_of_birth")), Option(res.getString("activity_lvl")), res.getBoolean("baned"))
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
      val stmt = conn.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
      stmt.setLong(1, user.getId)
      stmt.setString(2, user.getUsername)
      stmt.setString(3, user.getPassword)
      stmt.setString(4, user.getName)
      user.getWeight match {
        case Some(weight) => stmt.setDouble(5, weight)
        case None => stmt.setNull(5, java.sql.Types.DOUBLE)
      }
      user.getHeight match{
        case Some(height) => stmt.setDouble(6, height)
        case None => stmt.setNull(6, java.sql.Types.DOUBLE)
      }
      user.getDateOfBirth match{
        case Some(date) => stmt.setDate(7, date)
        case None => stmt.setNull(7, java.sql.Types.DATE)
      }
      user.getActivityLevel match{
        case Some(activity) => stmt.setString(8, activity)
        case None => stmt.setNull(8, java.sql.Types.VARCHAR)
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
      val dbUser: Option[User] = findUserById(user.getId)
      var sql = "UPDATE user SET"
      if (user.getUsername != dbUser.get.getUsername) {
        sql += s" username = '${user.getUsername}',"
      }

      if (user.getPassword != dbUser.get.getPassword) {
        sql += s" password = '${user.getPassword}',"
      }

      if (user.getName != dbUser.get.getName) {
        sql += s" name = '${user.getName}',"
      }

      if (user.getWeight != dbUser.get.getWeight) {
        sql += s" weight = ${user.getWeight},"
      }

      if (user.getHeight != dbUser.get.getHeight) {
        sql += s" height = ${user.getHeight},"
      }

      if (user.getDateOfBirth != dbUser.get.getDateOfBirth) {
        sql += s" date_of_birth = '${user.getDateOfBirth}',"
      }

      if (user.getActivityLevel != dbUser.get.getActivityLevel) {
        sql += s" activity_lvl = '${user.getActivityLevel}',"
      }

      if (user.isBanned != dbUser.get.isBanned) {
        sql += s" baned = '${user.isBanned}',"
      }
      if (sql.endsWith(",")) {
        sql = sql.dropRight(1)
      }

      sql += s" WHERE id = ${user.getId}"

      val stmt = conn.createStatement()
      stmt.executeUpdate(sql)
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