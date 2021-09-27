package uk.gov.nationalarchives.pentaho

import org.slf4j.{ Logger, LoggerFactory }
import uk.gov.nationalarchives.pentaho.DatabaseManager.DATABASE_URL

import java.sql.{ Connection, DriverManager, Statement }
import scala.util.{ Try, Using }

case class DatabaseManager() {

  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def insertData(sql: String): Try[Boolean] =
    Using.Manager { _ =>
      val con: Connection = DriverManager.getConnection(DATABASE_URL)
      val stm: Statement = con.createStatement
      stm.execute(sql)
    }

  def executeQuery(sql: String): Try[List[(Int, String)]] =
    Using.Manager { _ =>
      val con: Connection = DriverManager.getConnection(DATABASE_URL)
      val stm: Statement = con.createStatement
      val rs = stm.executeQuery(sql: String)
      ResultSetIterator(rs)
        .map(x => {
          (x.getInt("ID"), x.getString("NAME"))
        })
        .toList
    }

}
object DatabaseManager {
  val PARENT_DIR: String = "/home/rkw/Source/GitHub/nationalarchives/pentaho-scalatest-demo/data-dir"
  val DATABASE_NAME: String = "ildb-test-db" // it's better if you write db name in small letters
  val DATABASE_DIR: String = s"$PARENT_DIR/$DATABASE_NAME" // FYI, this is string interpolation
  val DATABASE_URL: String = s"jdbc:h2:$DATABASE_DIR;database_to_upper=false"
  val DATABASE_DRIVER_CLASS: String = "org.h2.Driver"
}
