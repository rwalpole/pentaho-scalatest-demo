package uk.gov.nationalarchives.pentaho

import org.pentaho.di.core.KettleEnvironment
import org.pentaho.di.core.plugins.{PluginTypeInterface, StepPluginType}
import org.pentaho.di.trans.step.StepMetaInterface
import org.pentaho.di.trans.{Trans, TransMeta}

import scala.jdk.CollectionConverters._
import java.io.InputStream

object WorkflowManager {

  def runWorkflow(transformation: InputStream, plugins : List[Class[_ <: StepMetaInterface]], parameters: Map[String, String]): Unit = {
    // register custom step plugins
    val stepPluginType = StepPluginType.getInstance()
    val stepRegister = registerStepPlugin(stepPluginType)_
    for (plugin <- plugins) {
      stepRegister(plugin)
    }
    val pluginTypes :List[PluginTypeInterface] = List(stepPluginType)

    // init the environment with plugins
    KettleEnvironment.init(pluginTypes.asJava, true)

    // set the parameters for the transformation
    val trans = new Trans(new TransMeta(transformation, null, false, null, null))
    for (parameter <- parameters) {
      trans.setParameterValue(parameter._1, parameter._2)
    }

    // run the transformation
    trans.execute(null)
    trans.waitUntilFinished()
  }

  def registerStepPlugin(register: StepPluginType)(pluginClass: Class[_ <: StepMetaInterface]): Unit = {
    val stepAnnotation = pluginClass.getAnnotation(classOf[org.pentaho.di.core.annotations.Step])
    register.registerCustom(pluginClass, stepAnnotation.categoryDescription(), stepAnnotation.id(), stepAnnotation.name(), stepAnnotation.description(), stepAnnotation.image())
  }

}
