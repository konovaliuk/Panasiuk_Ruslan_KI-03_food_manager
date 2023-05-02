package connection

import com.zaxxer.hikari.{HikariDataSource, HikariConfig}
import java.util.Properties
import java.io.FileInputStream
import java.sql.SQLException

object DatabaseConnection {
  private val config = new HikariConfig()
  private val prop = new Properties()

  prop.load(new FileInputStream("src/main/scala/utils/config.properties"))

  config.setJdbcUrl(prop.getProperty("jdbc.url"))
  config.setUsername(prop.getProperty("jdbc.username"))
  config.setPassword(prop.getProperty("jdbc.password"))
  config.setMaximumPoolSize(prop.getProperty("jdbc.poolsize").toInt)
  config.setConnectionTimeout(prop.getProperty("jdbc.connection.timeout").toLong)

  private val ds = new HikariDataSource(config)

  try {
    ds.getConnection().close()
  } catch {
    case e: SQLException => println(s"Exception during connection test: ${e.getMessage}")
  }

  def getConnection(): java.sql.Connection = {
    ds.getConnection()
  }
}