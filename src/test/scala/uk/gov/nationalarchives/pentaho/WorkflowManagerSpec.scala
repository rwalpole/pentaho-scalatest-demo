package uk.gov.nationalarchives.pentaho

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.Success

class WorkflowManagerSpec extends AnyWordSpec with Matchers {

  private val outputDirectory = "/home/rkw/Downloads"
  private val resultFilename = "out.ttl"

  "WorkflowManager.runWorkflow" must {
    "execute a workflow" in {
      val params = Some(
        Map(
          "output_directory" -> outputDirectory,
          "result_filename"  -> resultFilename
        )
      )
      WorkflowManager.runWorkflow(
        getClass.getResource("/test.ktr").getPath,
        params
      )
      val result = QueryManager.executeQuery(
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?policy ?label WHERE { ?policy rdfs:label ?label. }",
        s"$outputDirectory/$resultFilename"
      )
      result mustBe Success(1)
    }
  }

}
