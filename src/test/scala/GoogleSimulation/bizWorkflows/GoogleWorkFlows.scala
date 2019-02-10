package GoogleSimulation.bizWorkflows

import GoogleSimulation.apis.GoogleServices
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GoogleWorkFlows {
  def BasicGoogleWorkFlows(repeatCount: Int, thinkTime: Int): ScenarioBuilder = {
    scenario("Get Google Home Page")
      .repeat(repeatCount){
        GoogleServices.getGoogleHomePage
          .pause(thinkTime)
      }
  }

}
