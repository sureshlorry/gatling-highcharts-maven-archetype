package GoogleSimulation.apis

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GoogleServices {
  def getGoogleHomePage=
    exec(http("Get Google Home Page")
    .get("/")
    .check(status is 200))
}
