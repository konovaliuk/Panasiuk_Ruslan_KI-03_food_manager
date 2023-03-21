package DAO.interface

import entities.User
trait UserInterface {
  def findUserById(id: Long): Option[User]
  def findAllUser(): Seq[User]
  def addUser(user: User): Boolean
  def updateUser(user: User): Boolean
}
