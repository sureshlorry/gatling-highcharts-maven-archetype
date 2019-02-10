package GoogleSimulation.simulations

import GoogleSimulation.bizWorkflows.GoogleWorkFlows
import com.typesafe.config.{Config, ConfigFactory}
//import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.core.structure.{PopulationBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
//import io.gatling.http.protocol.HttpProtocol
//import scala.collection.JavaConversions._

class GoogleSimulationsScenarioBuilder extends Simulation{

  var httpconf = http
    .baseURL("https://www.google.com")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")

  var setUpScenario: ScenarioBuilder = null
  val users = ConfigFactory.load("application.conf").getInt("gsConf.repeat")
  val rampUpDuration = ConfigFactory.load("application.conf").getInt("gsConf.rampUpDuration")
  val thinkTime = ConfigFactory.load("application.conf").getInt("gsConf.thinkTime")
  val repeat = ConfigFactory.load("application.conf").getInt("gsConf.repeat")

  var workFlowList = GoogleWorkFlows.BasicGoogleWorkFlows(repeat,thinkTime)

  setUpScenario = scenario("Executing HitGoogleWorkFlow")
      .exec(workFlowList)
      .pause(2)

  setUp(setUpScenario.inject(atOnceUsers(users))
    .protocols(httpconf))
}
