package ComputerDatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Simulation1 extends Simulation{
    var httpconf = http
      .baseURL("http://computer-database.gatling.io")
      .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
      .doNotTrackHeader("1")
      .acceptLanguageHeader("en-US,en;q=0.5")
      .acceptEncodingHeader("gzip, deflate")

    val headers_10 = Map("Content_Type" -> "application/x-www-form-urlencoded")

    val scn = scenario("Suresh_Simulation")
      .exec(http("01-Get-Landing Page").get("/"))
      .pause(7)
      .exec(http("02-macbook-lookup").get("/computers?f=6"))
      .pause(2)
      .exec(http("03-pagination").get("/computers?p=1"))
      .pause(1)
      .exec(http("04-add a new computer").post("/computers/new")
        .headers(headers_10)
        .formParam("Computer Name", "SureshMacbook")
        .formParam("Introduced date", "2016-01-01")
        .formParam("Discontinued date", "2018-01-01")
        .formParam("Company", "Nokia"))
        .pause(2)

    setUp(scn.inject(atOnceUsers(1)).protocols(httpconf))

}
