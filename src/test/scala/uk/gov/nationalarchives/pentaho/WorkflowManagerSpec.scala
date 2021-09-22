package uk.gov.nationalarchives.pentaho

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.Success

class WorkflowManagerSpec extends AnyWordSpec with Matchers {

  "WorkflowManager.runWorkflow" must {
    "execute a workflow" in {
      WorkflowManager.runWorkflow("/home/rkw/Source/GitHub/nationalarchives/pentaho-scalatest-demo/src/test/resources/test.ktr")
      val result = QueryManager.executeQuery(
        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?policy ?label WHERE { ?policy rdfs:label ?label. }",
        "/home/rkw/Source/GitHub/nationalarchives/pentaho-scalatest-demo/result.ttl")
        result mustBe Success(1)
    }
  }


}
