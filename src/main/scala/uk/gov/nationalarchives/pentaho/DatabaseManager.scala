package uk.gov.nationalarchives.pentaho

import org.slf4j.{Logger, LoggerFactory}

import java.sql.{Connection, DriverManager, ResultSet, Statement}

case class DatabaseManager() {

  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  val PARENT_DIR: String = "./data-dir"
  val DATABASE_NAME: String = "my-h2-db" // it's better if you write db name in small letters
  val DATABASE_DIR: String = s"$PARENT_DIR/$DATABASE_NAME" // FYI, this is string interpolation
  val DATABASE_URL: String = s"jdbc:h2:$DATABASE_DIR"

  def insertData(sql: String) =
    try {
      val con: Connection = DriverManager.getConnection(DATABASE_URL)
      val stm: Statement = con.createStatement
      stm.execute(sql)
      if (con != null) con.close()
      if (stm != null) stm.close()
    } catch {
      case e: Exception => logger.error(e.getMessage)
    }

  def executeQuery(sql: String) = {
    val con: Connection = DriverManager.getConnection(DATABASE_URL)
    val stm: Statement = con.createStatement
    val rs = stm.executeQuery(sql:String)
    while(rs.next){
      rs.getInt("ID")
      rs.getString("NAME")
    }
  }

}
