name := "pentaho-scalatest-demo"

version := "0.1"

scalaVersion := "2.13.6"

scalacOptions += "-target:jvm-1.8"

val pentahoVersion = "9.1.0.0-324"

resolvers += Resolver.mavenLocal
resolvers +=
  "PentahoNexus" at "https://nexus.pentaho.org/content/groups/omni"
//"PentahoNexus" at "https://repo.orl.eng.hitachivantara.com/artifactory/pnt-mvn/"

libraryDependencies += "pentaho-kettle"               % "kettle-core"           % pentahoVersion
libraryDependencies += "pentaho-kettle"               % "kettle-engine"         % pentahoVersion
libraryDependencies += "org.apache.jena"              % "apache-jena-libs"      % "3.17.0"
libraryDependencies += "org.pentaho.di.plugins"       % "pdi-core-plugins-impl" % pentahoVersion
libraryDependencies += "pentaho-kettle"               % "kettle-engine"         % pentahoVersion    % "test" classifier "tests"
libraryDependencies += "pentaho-kettle"               % "kettle-core"           % pentahoVersion    % "test" classifier "tests"
libraryDependencies += "org.scalatest"                %% "scalatest"            % "3.2.9"           % "test"
libraryDependencies += "uk.gov.nationalarchives.pdi"  % "kettle-jena-plugins"   % "2.2.0-SNAPSHOT"  % "test"



