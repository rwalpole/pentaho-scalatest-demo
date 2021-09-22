package uk.gov.nationalarchives.pentaho

import org.apache.jena.query._
import org.apache.jena.riot._

import scala.util.{Try, Using}

object QueryManager {

  def executeQuery(queryString: String, resultFilename: String): Try[Int] = {
    val model = RDFDataMgr.loadModel(resultFilename)
    val query = QueryFactory.create(queryString)
    Using(QueryExecutionFactory.create(query, model)){ queryExec =>
      val results = queryExec.execSelect
      val rs = ResultSetFactory.copyResults(results)
      rs.size()
    }
  }

}
