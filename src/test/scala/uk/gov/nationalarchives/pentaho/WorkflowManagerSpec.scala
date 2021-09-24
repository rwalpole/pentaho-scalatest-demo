package uk.gov.nationalarchives.pentaho

import org.pentaho.di.trans.step.StepMetaInterface
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.gov.nationalarchives.pdi.step.jena.model.JenaModelStepMeta
import uk.gov.nationalarchives.pdi.step.jena.serializer.JenaSerializerStepMeta

import scala.util.Success

class WorkflowManagerSpec extends AnyWordSpec with Matchers {

  "WorkflowManager.runWorkflow" must {
    "execute a workflow" in {

      val plugins : List[Class[_ <: StepMetaInterface]] = List(
        classOf[JenaModelStepMeta],
        classOf[JenaSerializerStepMeta]
      )

      val outputFile = "/tmp/result.ttl"

      val parameters = Map("output_file" -> outputFile)

      res(getClass.getResourceAsStream("/test.ktr")) { is =>
        WorkflowManager.runWorkflow(is, plugins, parameters)
        val result = QueryManager.executeQuery(
          "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?policy ?label WHERE { ?policy rdfs:label ?label. }",
          outputFile)
        result mustBe Success(1)
      }
    }
  }

  def res[T <: AutoCloseable, U](opener: => T)(use: T => U): U = {
    try {
        use(opener)
      } finally {
        opener.close()
      }
  }

}
