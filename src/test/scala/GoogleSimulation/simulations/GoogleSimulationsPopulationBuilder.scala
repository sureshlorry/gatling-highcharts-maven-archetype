package GoogleSimulation.simulations

import GoogleSimulation.bizWorkflows.GoogleWorkFlows
import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.structure.{ChainBuilder, PopulationBuilder, ScenarioBuilder}
//import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import io.gatling.http.protocol.HttpProtocol
import scala.concurrent.duration._

class GoogleSimulationsPopulationBuilder extends Simulation{

  var httpconf = http
    .baseURL("https://www.google.com")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")

  //need to refactor to population builder
  var setUpScenario: PopulationBuilder = null

  val users = ConfigFactory.load("application.conf").getInt("gsConf.repeat")
  val rampUpDuration = ConfigFactory.load("application.conf").getInt("gsConf.rampUpDuration")
  val thinkTime = ConfigFactory.load("application.conf").getInt("gsConf.thinkTime")
  val repeat = ConfigFactory.load("application.conf").getInt("gsConf.repeat")


  var workFlowList  = GoogleWorkFlows.BasicGoogleWorkFlows(repeat,thinkTime)
  setUpScenario=scenario("Executing HitGoogleWorkflow")
    .exec(workFlowList)
    .inject(rampUsers(users) over (rampUpDuration seconds))

  /*setUpScenario = scenario("Executing HitGoogleWorkFlow")
      .exec(workFlowList)
      .pause(2)*/

    //setUp(setUpScenario)

  setUp(setUpScenario)
    .protocols(httpconf.baseURL("https://www.google.com"))
    .assertions(global.successfulRequests.percent.is(100),
      global.responseTime.percentile1.lessThan(500),
      global.responseTime.percentile2.lessThan(500),
      global.responseTime.percentile3.lessThan(750),
      global.responseTime.percentile4.lessThan(1000))
}
