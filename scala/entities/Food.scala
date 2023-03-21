package entities

class Food(private val id: Long,
           private var name: String,
           private var measurement: String,
           private var caloriesM: Double,
           private var proteinsM: Double,
           private var fatsM: Double,
           private var carbohydratesM: Double,
           private var fibersM: Double,
           ){

  def getId: Long = id
  def getName: String = name
  def getMeasurement: String = measurement
  def getCaloriesM: Double = caloriesM
  def getProteinsM: Double = proteinsM
  def getFatsM: Double = fatsM
  def getCarbohydratesM: Double = carbohydratesM
  def getFibersM: Double = fibersM

  def setName(newName: String): Unit = {
    name = newName
  }

  def setMeasurement(newMeasurement: String): Unit = {
    measurement = newMeasurement
  }

  def setCaloriesM(newCaloriesM: Double): Unit = {
    caloriesM = newCaloriesM
  }

  def setProteinsM(newProteinsM: Double): Unit = {
    proteinsM = newProteinsM
  }

  def setFatsM(newFatsM: Double): Unit = {
    fatsM = newFatsM
  }

  def setCarbohydratesM(newCarbohydratesM: Double): Unit = {
    carbohydratesM = newCarbohydratesM
  }

  def setFibersM(newFibersM: Double): Unit = {
    fibersM = newFibersM
  }

}
