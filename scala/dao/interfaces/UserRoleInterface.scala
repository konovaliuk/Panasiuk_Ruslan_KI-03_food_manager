package dao.interfaces

import entities.{Role, UserRole}
trait UserRoleInterface {
  def findAllUserRole(id: Long): Seq[Role]
  def addUserRole(v: UserRole): Boolean
}
