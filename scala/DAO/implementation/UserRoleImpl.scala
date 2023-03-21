package DAO.implementation

import Connection.DatabaseConnection
import entities.{Role, UserRole}
import java.sql.SQLException
import DAO.interface.UserRoleInterface

class UserRoleImpl extends UserRoleInterface{
  override def findAllUserRole(id: Long): Seq[Role] = {
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("SELECT role.* FROM user_role JOIN role ON user_role.role_id = role.id WHERE user_role.user_id = ?")
      stmt.setLong(1, id)
      val res = stmt.executeQuery()
      var seq: Seq[Role] = null
      while (res.next()) {
        val v = new Role(res.getLong("id"), res.getString("name"))
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
  override def addUserRole(v: UserRole): Boolean ={
    var conn: java.sql.Connection = null
    try {
      conn = DatabaseConnection.getConnection()
      val stmt = conn.prepareStatement("INSERT INTO user_role VALUES (?, ?)")
      stmt.setLong(1, v.getRoleId)
      stmt.setLong(2, v.getUserId)
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
