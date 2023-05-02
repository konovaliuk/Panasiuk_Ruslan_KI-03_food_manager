package entities

class Food(private val id: Long,
           private var name: String,
           private var measurement: String,
           private var caloriesM: Double,
           private var proteinsM: Double,
           private var fatsM: Double,
           private var carbohydratesM: Double,
           private var fiberM: Double,
           ){

  def getId: Long = id
  def getName: String = name
  def getMeasurement: String = measurement
  def getCaloriesM: Double = caloriesM
  def getProteinsM: Double = proteinsM
  def getFatsM: Double = fatsM
  def getCarbohydratesM: Double = carbohydratesM
  def getFiberM: Double = fiberM
}
