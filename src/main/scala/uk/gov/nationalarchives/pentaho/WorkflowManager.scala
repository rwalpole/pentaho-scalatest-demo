package uk.gov.nationalarchives.pentaho

import org.pentaho.di.core.KettleEnvironment
import org.pentaho.di.core.plugins.{PluginFolder, StepPluginType}
import org.pentaho.di.trans.{Trans, TransMeta}

object WorkflowManager {

  def runWorkflow(filename: String): Unit = {
    StepPluginType.getInstance.getPluginFolders.add(new PluginFolder("/opt/data-integration/plugins", false, true))
    //KettleEnvironment.init(stepPluginType.getClass)
    KettleEnvironment.init()
    //EnvUntil.environmentInit()
    val trans = new Trans(new TransMeta(filename))
    trans.execute(null)
    trans.waitUntilFinished()
  }

}
