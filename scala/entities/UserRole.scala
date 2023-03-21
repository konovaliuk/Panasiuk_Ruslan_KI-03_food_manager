package entities

class UserRole (private var userId: Long,
                private var roleId: Long){

  def getUserId: Long = userId
  def getRoleId: Long = roleId

  def setUserId(newUserId: Long): Unit = {
    userId = newUserId
  }

  def setRoleId(newRoleId: Long): Unit = {
    roleId = newRoleId
  }
}
