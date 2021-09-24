package uk.gov.nationalarchives.pentaho

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import java.sql.{ Connection, DriverManager, Statement }
import scala.reflect.io.Directory
import scala.util.{ Failure, Success }

class TestClass extends AnyFunSuite with BeforeAndAfterAll {

  val databaseManager = DatabaseManager()

  test("embedded H2 database create table with insert") {
    //var row1Insertion = false
    //var row2Insertion = false
    //var row3Insertion = false

    //try {
    val sql: String =
      """
        |create table tbl_closuretype(closure_type char(1), cltype_desc varchar(255));
        |insert into tbl_closuretype values ('A','Open on Transfer');
        |create table tbl_item(closure_code int, last_date int, open_date datetime);
        |insert into tbl_item values(0, 19911231, null)
       """.stripMargin
    databaseManager.insertData(sql) match {
      case Success(_)         => println("")
      case Failure(exception) => fail(s"Unable to insert data due to ${exception.getMessage}")
    }
    //stm.execute(sql)

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
