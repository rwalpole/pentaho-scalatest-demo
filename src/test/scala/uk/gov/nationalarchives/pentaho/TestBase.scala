package uk.gov.nationalarchives.pentaho

import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{ Failure, Success }

trait TestBase extends AnyWordSpec with BeforeAndAfterAll with Matchers {

  val databaseManager = DatabaseManager()

  def insertData(sql: String) =
    databaseManager.insertData(sql) match {
      case Success(_)         => println("")
      case Failure(exception) => fail(s"Unable to insert data due to ${exception.getMessage}")
    }

}
