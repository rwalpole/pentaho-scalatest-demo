package uk.gov.nationalarchives.pentaho

import org.pentaho.di.trans.step.StepMetaInterface
import uk.gov.nationalarchives.pdi.step.jena.model.JenaModelStepMeta
import uk.gov.nationalarchives.pdi.step.jena.serializer.JenaSerializerStepMeta
import uk.gov.nationalarchives.pentaho.DatabaseManager._

import java.io.File
import scala.reflect.io.Directory
import scala.util.Success

class TestClass extends TestBase {

  val plugins: List[Class[_ <: StepMetaInterface]] = List(
    classOf[JenaModelStepMeta],
    classOf[JenaSerializerStepMeta]
  )

  private val resultFilename = "result.ttl"

  "The Create ODRL Policies workflow" must {
    "produce valid ODRL policies" in {
      val sql: String = {
        """
          |create table tbl_closuretype(closure_type char(1), cltype_desc varchar(255));
          |insert into tbl_closuretype values ('A','Open on Transfer');
          |create table tbl_item(closure_type char(1), closure_code int, last_date int, open_date datetime);
          |insert into tbl_item values('A', 0, 19911231, null)
       """.stripMargin
      }
      insertData(sql)

      val params =
        Map(
          "JDBC_URL"        -> DATABASE_URL,
          "JDBC_CLASS"      -> DATABASE_DRIVER_CLASS,
          "OUTPUT_FILEPATH" -> "/home/rkw/Downloads",
          "OUTPUT_FILENAME" -> "policies.ttl")

      import java.io.FileInputStream
      val is = new FileInputStream(
        "/home/rkw/Source/GitHub/nationalarchives/tna-cat/Omega/ildb/export/kettle/ildb-create-odrl-policies.ktr")
      WorkflowManager.runWorkflow(is, plugins, params)
      is.close()
      val result = QueryManager.executeQuery(
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?policy ?label WHERE { ?policy rdfs:label ?label. }",
        resultFilename)
      result mustBe Success(3)

      //Using(
      // }
    }
  }

  override def beforeAll(): Unit =
    clearDatabaseDataDir()

//  override def afterAll() {
//    jdbcDisconnect()
//    clearDatabaseDataDir()
//  }

  private def clearDatabaseDataDir(): Unit =
    new Directory(new File(PARENT_DIR)).deleteRecursively()
//
//  private def jdbcDisconnect(): Unit = {
//    if (con != null) con.close()
//    if (stm != null) stm.close()
//  }

}
