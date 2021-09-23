package uk.gov.nationalarchives.pentaho

import scala.sys.process._

object WorkflowManager {

  def runWorkflow(
    filename: String,
    paramMap: Option[Map[String, String]] = None
  ): Unit = {
    val params = getParamList(paramMap)
    val commandSeq =
      Seq("/opt/data-integration/pan.sh", "/file:" + filename) ++ params
    commandSeq.!
  }

  private def getParamList(optionalParams: Option[Map[String, String]]): List[String] =
    optionalParams match {
      case Some(params) =>
        params.map(pair => s"-param:${pair._1}=${pair._2}").toList
      case None => List.empty
    }

}
