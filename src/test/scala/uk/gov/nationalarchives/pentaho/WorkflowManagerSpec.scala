package uk.gov.nationalarchives.pentaho

import org.pentaho.di.trans.step.StepMetaInterface
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.gov.nationalarchives.pdi.step.jena.model.JenaModelStepMeta
import uk.gov.nationalarchives.pdi.step.jena.serializer.JenaSerializerStepMeta

import scala.util.{ Success, Using }

class WorkflowManagerSpec extends AnyWordSpec with Matchers {

  private val outputDirectory = "/home/rkw/Downloads"
  private val resultFilename = "out.ttl"

  "WorkflowManager.runWorkflow" must {
//    "execute a workflow" in {
//
//      val plugins: List[Class[_ <: StepMetaInterface]] = List(
//        classOf[JenaModelStepMeta],
//        classOf[JenaSerializerStepMeta]
//      )
//
//      val params = Map(
//        "output_directory" -> outputDirectory,
//        "result_filename"  -> resultFilename
//      )
//
//      Using(getClass.getResourceAsStream("/test.ktr")) { is =>
//        WorkflowManager.runWorkflow(is, plugins, params)
//        val result = QueryManager.executeQuery(
//          "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?policy ?label WHERE { ?policy rdfs:label ?label. }",
//          resultFilename)
//        result mustBe Success(1)
//      }
//
//      val result = QueryManager.executeQuery(
//        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?policy ?label WHERE { ?policy rdfs:label ?label. }",
//        s"$outputDirectory/$resultFilename"
//      )
//      result mustBe Success(1)
//    }
  }

}
