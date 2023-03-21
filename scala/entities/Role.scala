package entities

class Role (private val id: Long,
            private var name: String){

  def getId: Long = id
  def getName: String = name

  def setName(newName: String): Unit = {
    name = newName
  }
}
