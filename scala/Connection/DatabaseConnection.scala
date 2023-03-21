package Connection

import com.zaxxer.hikari.{HikariDataSource, HikariConfig}
import java.util.Properties
import java.io.FileInputStream


object DatabaseConnection {
  private val config = new HikariConfig()
  private val prop = new Properties()

  prop.load(new FileInputStream("config.properties"))

  config.setJdbcUrl(prop.getProperty("jdbc.url"))
  config.setUsername(prop.getProperty("jdbc.username"))
  config.setPassword(prop.getProperty("jdbc.password"))
  config.setMaximumPoolSize(prop.getProperty("jdbc.poolsize").toInt)
  config.setConnectionTimeout(prop.getProperty("jdbc.connection.timeout").toLong)

  private val ds = new HikariDataSource(config)

  def getConnection(): java.sql.Connection = {
    ds.getConnection()
  }
}