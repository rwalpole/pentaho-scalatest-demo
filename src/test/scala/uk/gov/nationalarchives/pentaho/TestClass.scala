package uk.gov.nationalarchives.pentaho

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import java.sql.{ Connection, DriverManager, Statement }
import scala.reflect.io.Directory

class TestClass extends AnyFunSuite with BeforeAndAfterAll {

  val PARENT_DIR: String = "./data-dir"
  val DATABASE_NAME: String = "my-h2-db" // it's better if you write db name in small letters
  val DATABASE_DIR: String = s"$PARENT_DIR/$DATABASE_NAME" // FYI, this is string interpolation
  val DATABASE_URL: String = s"jdbc:h2:$DATABASE_DIR"

  val databaseManager = DatabaseManager()

  //val con: Connection = DriverManager.getConnection(DATABASE_URL)
  //val stm: Statement = con.createStatement

//  test("embedded H2 database example") {
//    var count = 0
//    val rs = stm.executeQuery("SELECT 1+1")
//    try {
//      if (rs.next) {
//        count = rs.getInt(1)
//      }
//    } catch {
//      case ex: Exception => ex.printStackTrace()
//
//    } finally {
//      if (rs != null) rs.close()
//    }
//
//    assert(count == 2)
//  }

  test("embedded H2 database create table with insert") {
    var row1Insertion = false
    var row2Insertion = false
    var row3Insertion = false

    //try {
    val sql: String =
      """
        |create table tbl_closuretype(ID INT PRIMARY KEY,NAME VARCHAR(500));
        |insert into test2 values (1,'A');
        |insert into test2 values (2,'B');
        |insert into test2 values (3,'C');""".stripMargin
    databaseManager.insertData(sql)
    //stm.execute(sql)
    databaseManager.executeQuery("""select * from tbl_closuretype""")
//      try {
//        if (rs.next) {
//          row1Insertion = (1 == rs.getInt("ID")) && ("A" == rs.getString("NAME"))
//
//          rs.next
//          row2Insertion = (2 == rs.getInt("ID")) && ("B" == rs.getString("NAME"))
//
//          rs.next
//          row3Insertion = (3 == rs.getInt("ID")) && ("C" == rs.getString("NAME"))
//        }
//      } catch {
//        case ex: Exception => ex.printStackTrace()
//      } finally {
//        if (rs != null) rs.close()
//      }
    /* } catch {
      case e: Exception => println(e.getMessage)
    }*/
    //assert(row1Insertion && row2Insertion && row3Insertion, "Data not inserted")
  }

//  override def afterAll() {
//    jdbcDisconnect()
//    clearDatabaseDataDir()
//  }

//  private def clearDatabaseDataDir(): Unit =
//    new Directory(new File(PARENT_DIR)).deleteRecursively()
//
//  private def jdbcDisconnect(): Unit = {
//    if (con != null) con.close()
//    if (stm != null) stm.close()
//  }

}
